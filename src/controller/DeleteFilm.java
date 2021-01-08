package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.FilmDAO;
import models.Film;

/**
 * Servlet implementation class DeleteFilm
 */
@WebServlet("/deleteFilm")
public class DeleteFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		FilmDAO dao = FilmDAO.getSingletonObject();
		
		
		PrintWriter pw;    
        response.setContentType("text/html");    
        pw=response.getWriter();  
		
		
		try {
		int id = Integer.valueOf(request.getParameter("id"));
		
		  
 
       try {
		dao.deleteFilm(id);
		//checking if film is still in DB
		if(dao.getFilmByID(id)==null) {
			pw.println("Film with ID "+id +" deleted sucessfuly");
            pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");
            }
            else {
            	pw.println("Delete failed");
            	pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");
            }
		
		
			
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		} catch(NumberFormatException ex) {
			pw.println("ID is in numbers format for example '10004'");
        	pw.print("<br><a href=\"deleteFilmFromDB.html\"><button type=\"button\">Delete again</button></a>");
		}
       
		pw.close();
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
