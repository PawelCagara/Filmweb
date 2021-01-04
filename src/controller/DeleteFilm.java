package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int id = Integer.valueOf(request.getParameter("id"));
		
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
           
            String query = "delete from films where id=?;";
            PreparedStatement pstmt=con.prepareStatement(query);    
                
           
            pstmt.setInt(1, id);
                
            int checker=pstmt.executeUpdate();    
                
            if(checker==1) {   
            pw.println("Film with ID "+id +" deleted sucessfuly");
            pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");
            }
            else {
            	pw.println("Delete failed, check if you are using correct ID");
            	pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");
            }
                
        }    
        catch(Exception e)    
        {    
                e.printStackTrace();    
        }    
            
            
        pw.close();  
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
