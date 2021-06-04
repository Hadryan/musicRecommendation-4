

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.Filter;

import dataAccessLayer.EmbeddedNeo4j;


//INGRESAR CANCIONES



@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
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
			        out.println("<h3>SUGERIR NUEVA MUSICA PARA EXPANDIR BASE DE DATOS</h3>");
			        out.println("<br>");
			        
			        HTMLFilter filtro = new HTMLFilter();
			        
			        String nombre1 = request.getParameter("nombre");
			        String cantante1 = request.getParameter("cantante");
			        String ano = request.getParameter("ano");
			        String genero1 = request.getParameter("genero");
			        String album1 = (request.getParameter("album"));
			        
			        
			        if(nombre1 == null || cantante1 == null || ano == null || genero1 == null || album1 == null) {
			        	out.println("Por favor ingrese informacion.");
			        }else {
			        	try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "OnikaTanya10!" ) )
				        {
			        		String nombre2 = nombre1.toLowerCase();
			        		String cantante2 = cantante1.toLowerCase();
			        		String genero2 = genero1.toLowerCase();
			        		String album2 = album1.toLowerCase();
			        		
			        		
					        String nombre = nombre2.substring(0, 1).toUpperCase() + nombre2.substring(1);
					        String cantante = cantante2.substring(0, 1).toUpperCase() + cantante2.substring(1);
					        String genero = genero2.substring(0, 1).toUpperCase() + genero2.substring(1);
					        String album = album2.substring(0, 1).toUpperCase() + album2.substring(1);
					        
					        int year4 = Integer.parseInt(ano);
					        
					        if(genero.equals("Indie") || genero.equals("Blues") || genero.equals("Rock") || genero.equals("Metal") || genero.equals("Pop") || genero.equals("Rap") || genero.equals("Country") || genero.equals("Punk") || genero.equals("Trap")) {
					        	if(year4 > 1999 && year4 < 2022) {
					        		String a = greeter.printGreeting(album,nombre, cantante , genero, ano);
								 	out.println("<br>");
								 	out.println(a);
								 	out.println("<br>");
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
				        	out.println("<br>");
				        	out.println("Por favor ingrese informacion correcta o regrese al inicio.");
				        	out.println("<br>");
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
			        
			        out.println("Nombre de la cancion:");
			        out.println("<input type=text size=20 name=nombre>");
			        out.println("<br>");
			        out.println("<hr />");
			        
			        out.println("Nombre del artista:");
			        out.println("<input type=text size=20 name=cantante>");
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
			        
			        out.println("Nombre del album:");
			        out.println("<input type=text size=20 name=album>");
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
