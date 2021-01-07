package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import dataBase.FilmDAO;
import models.Film;
import models.Filmstore;


/**
 * Servlet implementation class HomePage
 */
@WebServlet("/AllFilmsServlet")
public class AllFilmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 String format;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllFilmsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//FilmDAO filmDAO = new FilmDAO();
		FilmDAO filmDAO = FilmDAO.getSingletonObject();
		ArrayList<Film> allFilms = filmDAO.getAllFilms();
		
		
		
		
		PrintWriter pw;    
        response.setContentType("text/xml");    
        
        pw=response.getWriter(); 
		
		//Gson gson = new Gson();
		//data = gson.toJson(allFilms);
		//data = allFilms.toString();
		 Filmstore filmstore = new Filmstore();
        
	        //bookstore.setFilmsList(allFilms);
		 filmstore.setFilmsList(allFilms);

	        try {
	        
	        JAXBContext context = JAXBContext.newInstance(Filmstore.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        // Write to System.out
	        m.marshal(filmstore, System.out);
	        
	        
	        m.marshal(filmstore, pw);
	     
	       
	        
	        }
	        catch (Exception e) {
	        	
	        }
		//address = "home";
		//request.setAttribute("jsonAllFilms", data);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/" + address + ".jsp");
		//dispatcher.forward(request, response);
			
			
			
	
		//PrintWriter print = response.getWriter();
		//print.write(filmsJson);
		//print.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		format = request.getParameter("data-format");
		System.out.println(format);
		doGet(request, response);
	}

}
