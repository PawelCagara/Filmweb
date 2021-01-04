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
@WebServlet("/home")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String format;
	String searchType = "";
	String searchValue;
	String getAllFilms;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> allFilms = filmDAO.getAllFilms();
		String data = "";
		String address = "";
		int id = 0;
		String filmName = "";

		if (format.equals("Json")) {
			Gson gson = new Gson();

			if (searchType.equals("byID")) {
				try {
					id = Integer.valueOf(searchValue);
					Film film = filmDAO.getFilmByID(id);
					data = gson.toJson(film);
					address = "home";
				} catch (NumberFormatException ex) {
					data = "ID is in numbers format for example '10004'";
					address = "home";
				}

			}

			else if (searchType.equals("byName")) {
				// filmName = searchValue;
				ArrayList<Film> film = filmDAO.getFilmByName(searchValue);
				data = gson.toJson(film);
				address = "home";
			} else if (getAllFilms.equals("getAllFilms")) {
				data = gson.toJson(allFilms);
				address = "home";
			}

			

		} else if (format.equals("XML")) {

			data = "XML FORMAT";
			address = "home";
		} else {
			if(searchType.equals("byID")){
				try {
					id = Integer.valueOf(searchValue);
					Film film = filmDAO.getFilmByID(id);
					data = film.toString();
					address = "home";
				} catch (NumberFormatException ex) {
					data = "ID is in numbers format for example '10004'";
					address = "home";
				}
			}
			else if(searchType.equals("byName")) {
				ArrayList<Film> film = filmDAO.getFilmByName(searchValue);
				data = film.toString();
				address = "home";
			}
		}
		// PrintWriter print = response.getWriter();
		// print.write(filmsJson);
		// print.close();
		//response.setContentType("application/json");
		request.setAttribute("jsonAllFilms", data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/" + address + ".jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		format = request.getParameter("data-format");
		searchType = request.getParameter("searchType");
		searchValue = request.getParameter("searchValue");
		getAllFilms = request.getParameter("getAllFilms");

		doGet(request, response);
	}

}
