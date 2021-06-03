

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessLayer.EmbeddedNeo4j;


//RECOMENDAR CANCIONES



@WebServlet("/HelloServlet2")
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
        PrintWriter out = response.getWriter();
        out.println("<html>");
        	out.println("<head>");
        		out.println("<title>Request Parameters Example</title>");
        		out.println("<meta charset='ISO-8859-1'>");
        		out.println("<meta http-equiv='x-ua-compatible' content='ie=edge'>");
        		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        		out.println("<link rel='stylesheet' href='style.css'>");
        	out.println("</head>");
        	
        	out.println("<body>");
        
        		out.println("<header>");
			        out.println("<h3>SUGERENCIAS</h3>");
			        out.println("<br>");


			        String ano = request.getParameter("ano");
			        String genero = request.getParameter("genero");
			        
			        if(ano == null || genero == null) 
			        {
			        	out.println("Por favor ingrese informacion.");
			        	 
			        }else {
			        	try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "OnikaTanya10!" ) )
				        {
						 	LinkedList<String> myactors = greeter.getActors(genero, ano);
						 	
						 	for (int i = 0; i < myactors.size(); i++) {
						 		 out.println(myactors.get(i));
						 	}
				        	
				        } catch (Exception e) {
							// TODO Auto-generated catch block
				        	out.println("Por favor ingrese informacion correcta o regrese al inicio.");
							e.printStackTrace();
						}
			        	
			        	out.println("<br>");
			        	out.println("<butt>");
			        	out.println("<a href='index.html'>Regresar a Inicio</a>");
			        	out.println("</butt>");
			        	out.println("<br>");
			        	out.println("<br>");
			    
			        }
			        
			        
			        out.println("<P>");
			        out.print("<form action=\"");
			        out.print("\" ");
			        out.println("method=POST>");
			        out.println("<br>");
			        out.println("<hr />");
			        
			      
			        out.println("Año de publicacion:");
			        out.println("<input type=text size=20 name=ano>");
			        out.println("<br>");
			        out.println("<hr />");
			        
			        out.println("Genero musical:");
			        out.println("<input type=text size=20 name=genero>");
			        out.println("<br>");
			        out.println("<hr />");

			        out.println("<br>");
			        out.println("<br>");
			        
			        out.println("<input type=submit>");
			        out.println("</form>");
		        out.println("<header>");
	        out.println("</body>");
        out.println("</html>");
    }
	        
	       
	       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
