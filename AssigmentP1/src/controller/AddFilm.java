package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.FilmDAO;
import models.Film;
import java.sql.*;
import java.util.ArrayList;

/**
 * Servlet implementation class addFilm
 */
@WebServlet("/addFilm")
public class AddFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String user = "cagarap";
    String password = "yestErla7";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//FilmDAO dao = new FilmDAO();
		FilmDAO dao = FilmDAO.getSingletonObject();
		ArrayList<Film> allFilms = dao.getAllFilms();
		
		
		PrintWriter pw;    
        response.setContentType("text/html");    
        pw=response.getWriter();  
        
        String title = request.getParameter("title");
		
		String director = request.getParameter("director");
		String stars = request.getParameter("stars");
		String review = request.getParameter("review");
		
		try {
			int year = Integer.parseInt(request.getParameter("year"));
		try {
			String user = "cagarap";
			String password = "yestErla7";
			String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select max(id) from films");
			rs.next();
			// searching through DB for max ID then adding 1 to create next ID
			int id = rs.getInt(1) + 1;
			dao.addFilm(id, title, year, director, stars, review);

			pw.println("Film with ID " + id + " added sucessfuly to DB ");
			pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		} catch(NumberFormatException ex) {
			pw.println("Insert failed: year is in numbers format for example '2005'");
			pw.print("<br><a href=\"addFilmToDB.html\"><button type=\"button\">Insert again</button></a>");
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
