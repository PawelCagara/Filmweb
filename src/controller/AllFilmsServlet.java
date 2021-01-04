package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dataBase.FilmDAO;
import models.Film;

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
		
		
		
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> allFilms = filmDAO.getAllFilms();
		String data = "";
		String address = "";
		
		
		Gson gson = new Gson();
		data = gson.toJson(allFilms);
		address = "home";
		
			
			
			
	
		//PrintWriter print = response.getWriter();
		//print.write(filmsJson);
		//print.close();
		response.setContentType("application/json");
		request.setAttribute("jsonAllFilms", data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/"+address+".jsp");
		dispatcher.forward(request, response);
		
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
