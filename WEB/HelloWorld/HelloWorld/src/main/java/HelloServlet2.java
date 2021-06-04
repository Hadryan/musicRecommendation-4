

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
			        String genero1 = request.getParameter("genero");
			        
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
			        
			        if(ano == null && genero1 == null) 
			        {
			        	out.println("Por favor ingrese informacion.");
			        	 
			        }else {
			        	try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "OnikaTanya10!" ) )
				        {
			        		out.println("<center>");
			        		out.println("<font face='calibri' size='3' color='#ffffff'>");
			        		out.println("<table border = \"2\" style=\"color:white; border:3px solid #ffffff\">");
					        out.println("<tr style=\"color:white; border:2px solid #ffffff\"><td style=\"color:white; border:2px solid #ffffff\">CANCIÓN</td><td  style=\"color:white; border:2px solid #ffffff\">ARTISTA(S)</td><td style=\"color:white; border:2px solid #ffffff\">AÑO</td></tr>");
					        out.println("</font>");
					        out.println("<font face='calibri' size='3' color='#ffffff'>");
					        
					        int year4 = Integer.parseInt(ano);
					        String genero2 = genero1.toLowerCase();
					        String genero = genero2.substring(0, 1).toUpperCase() + genero2.substring(1);
					        
					        if(genero.equals("Indie") || genero.equals("Blues") || genero.equals("Rock") || genero.equals("Metal") || genero.equals("Pop") || genero.equals("Rap") || genero.equals("Country") || genero.equals("Punk") || genero.equals("Trap")) {
					        	if(year4 > 1999 && year4 < 2022) {
					        		
					        		LinkedList<String> myactors = greeter.getActors(genero, ano);
								 	
								 	int pos1 = 0;
								 	int pos2 = 0;
								 	for (int i = 0; i < myactors.size(); i++) {
								 		String temp = myactors.get(i);
								 		for (int j = 4; j < temp.length(); j++)
								        {
								            String t = temp.substring(j-4, j);
								            if(t.equals("n.Ar"))
								            {
								                pos1 = j-4; 
								            }
								        }
								 		for (int j = 4; j < temp.length(); j++)
								        {
								            String t = temp.substring(j-4, j);
								            if(t.equals("n.Añ"))
								            {
								                pos2 = j-4; 
								            }
								        }
								 		String temp1 = temp.substring(17, pos1-3);
								 		String temp2 = temp.substring(pos1+12, pos2-3);
								 		String temp3 = temp.substring(pos2 + 7, temp.length()-2);
								 		out.println("<tr style=\"color:white; border:2px solid #ffffff\"><td  style=\"color:white; border:2px solid #ffffff\">"+ temp1 + "</td><td  style=\"color:white; border:2px solid #ffffff\">" + temp2+ "</td><td style=\"color:white; border:2px solid #ffffff\">"+ temp3+"</td></tr>");
								 	}
								 	
					        	}else {
					        		out.println("<br>");
								 	out.println("El año ingresado no esta disponible. ");
								 	out.println("<br>");
					        	}
					        	
					        }else {
					        	out.println("<br>");
							 	out.println("El genero ingresado no esta disponible. ");
							 	out.println("<br>");
					        }
					        
						 	
				        	
				        } catch (Exception e) {
							// TODO Auto-generated catch block
				        	out.println("Por favor ingrese informacion correcta o regrese al inicio.");
							e.printStackTrace();
						}				      
			        	out.println("</table>");
			        	out.println("</font>");
			        	out.println("</center>");
			        	out.println("<br>");
			    
			        }

		        	out.println("<br>");
		        	out.println("<br>");
			        out.println("<input type=submit>");
			        out.println("</form>");
			        out.println("<butt>");
		        	out.println("<a href='index.html'>Regresar a Inicio</a>");
		        	out.println("</butt>");
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
