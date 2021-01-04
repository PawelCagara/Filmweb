package controller;

import java.io.IOException;
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
 * Servlet implementation class FilmByID
 */
@WebServlet("/FilmByID")
public class FilmByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		FilmDAO filmDAO = new FilmDAO();
		int id = 10003;
		String name = "LION";
		//Film film = filmDAO.getFilmByID(id);
		ArrayList<Film> film = filmDAO.getFilmByName(name);
		String data = "";
		String address = "";
		
		Gson gson = new Gson();
		data = gson.toJson(film);
		address = "filmByID";
		//response.setContentType("application/json");
		request.setAttribute("film", data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/"+address+".jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
