/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public String printGreeting( final String album, final String song, final String artist , final String genre, final String year )
    {
    	String greeting = "";
        try ( Session session = driver.session() )
        {
            greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                	Result test = tx.run( "MATCH (n{Song: \""+song+"\", Artista:\""+artist+"\", Album:\""+album+"\"}) RETURN n");
                	
                	String a = "";
                	
                	LinkedList<String> listResult = new LinkedList<String>();
                    List<Record> registros = test.list();
                    
                    
                    for (int i = 0; i < registros.size(); i++) {
                    	listResult.add(registros.get(i).toString());
                    }
                	
                    
                	if(listResult.isEmpty()) {
                		Result result = tx.run( "CREATE (n{Song: \""+song+"\", Artista:\""+artist+"\", Album:\""+album+"\", Año: "+year+", Género :\""+genre+"\"})");
                    	
                    	Result result2 = tx.run( "MATCH (n),(a:Género)"
                    			+ " WHERE n.Song = \""+song+"\" AND a.Género = \""+genre+"\""
                    			+ " CREATE (a)-[:_RELATED]->(n)");
                    	
                    	Result result3 = tx.run( "MATCH (n),(a:año)"
                    			+ " WHERE n.Song = \""+song+"\" AND a.Año ="+year+""
                    			+ " CREATE (a)-[:_RELATED]->(n)");
                    	
                    	a = "Se ha guardado '"+song+"' por '"+artist+"' en la base de datos";
                	}else {
                		a = "La cancion '"+song+"' por '"+artist+"' ya existe en la base de datos";
                	}
                	return a;
                	
                }
            } );
        }
        return greeting;
    }
    
    public String delete( final String album, final String song, final String artist)
    {
    	String greeting = "";
        try ( Session session = driver.session() )
        {
            greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                	
                	Result result = tx.run( "MATCH (n{Song: \""+song+"\", Artista:\""+artist+"\", Album:\""+album+"\"}) RETURN n");
                	Result result1 = tx.run( "MATCH (n{Song: \""+song+"\", Artista:\""+artist+"\", Album:\""+album+"\"}) DETACH DELETE n");
                	
                	String a = "";
                	
                	LinkedList<String> listResult = new LinkedList<String>();
                    List<Record> registros = result.list();
                    
                    for (int i = 0; i < registros.size(); i++) {
                    	listResult.add(registros.get(i).toString());
                    }
                	
                	if(listResult.isEmpty()) {
                		a = "La cancion '"+song+"' por '"+artist+"' no se encontro en la base de datos";
                	}else {
                		a = "Se ha eliminado '"+song+"' por '"+artist+"' de la base de datos";
                	}
                	return a;
                	
                }
            } );
        }
        return greeting;
    }
    
    public LinkedList<String> getActors(final String genre, final String year)
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                	 int yearfinal = Integer.parseInt(year);
                	 int year1 = yearfinal - 1;
                	 int year2 = yearfinal +1;
                	 Result result = null;
                	 
                	 if(yearfinal<2016 && yearfinal > 2000) {
                		 result = tx.run( "match (n)"
                            		+ " where n.Año >= "+year1+" AND n.Año <= "+year2+" AND n.`Género` = \""+genre+"\""
                            		+ " return n.Song, n.Artista, n.Año  limit 10");
                	 }else {
                		 result = tx.run( "match (n)"
                            		+ " where n.Año >= "+year1+" AND n.Año <= "+year2+" AND n.`Género` = \""+genre+"\" AND rand() < 0.4"
                            		+ " return n.Song, n.Artista, n.Año  limit 10");
                	 }
                	 
                	 
                	
                     
                     LinkedList<String> myactors = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 
                    	 myactors.add(registros.get(i).toString());
                     }
                     
                     return myactors;
                 }
             } );
             
             return actors;
         }
    }

}
