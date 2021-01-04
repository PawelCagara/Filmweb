package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.FilmDAO;
import models.Film;

/**
 * Servlet implementation class UpdateFilm
 */
@WebServlet("/updateFilm")
public class UpdateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("year"));
		String director = request.getParameter("director");
		String stars = request.getParameter("stars");
		String review = request.getParameter("review");
		
		
		PrintWriter pw;    
        response.setContentType("text/html");    
        pw=response.getWriter();    
 
        try    
        {    
            Class.forName("com.mysql.jdbc.Driver");    

            String user = "cagarap";
            String password = "yestErla7";            
            String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;
                
            Connection con = DriverManager.getConnection(url, user, password);    
            //String query = "Insert into films(id,title,year,director,stars,review) values (?,?,?,?,?,?);";
           
           // String query = "update films set title =? where id = ?;";
            String query = "Update films set title=?,year=?,director=?,stars=?,review=? where id=?;";
            PreparedStatement pstmt=con.prepareStatement(query);    
                
            pstmt.setString(1, title);
            pstmt.setInt(2,year);    
            pstmt.setString(3, director);    
            pstmt.setString(4, stars);    
            pstmt.setString(5,review);
            pstmt.setInt(6, id);
                
            int checker=pstmt.executeUpdate();    
                
            if(checker==1) {   
            pw.println("Film updated sucessfuly"); 
            pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");
            }
            else {
            	
            	pw.println("Update failed, check if you are using correct ID");
            	pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");
            }
                
        }    
        catch(Exception e)    
        {    
                e.printStackTrace();    
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
