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

		FilmDAO filmDAO = FilmDAO.getSingletonObject();
		ArrayList<Film> allFilms = filmDAO.getAllFilms();
		String data = "";
		String address = "";
		int id = 0;
		//part for JSON
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
			} else if (searchType.equals("getAllFilms")) {
				data = gson.toJson(allFilms);
				address = "home";
			}
			//Part for dealing with XML
		} else if (format.equals("XML")) {
			PrintWriter pw;
			response.setContentType("text/xml");
			pw = response.getWriter();
			Filmstore bookstore = new Filmstore();

			if (searchType.equals("byID")) {
				try {
					id = Integer.valueOf(searchValue);
					Film film = filmDAO.getFilmByID(id);
					bookstore.setFilmById(film);

					JAXBContext context = JAXBContext.newInstance(Filmstore.class);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

					m.marshal(bookstore, pw);
				} catch (NumberFormatException ex) {
					data = "ID is in numbers format for example '10004'";
					address = "home";
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (searchType.equals("byName")) {
				ArrayList<Film> film = filmDAO.getFilmByName(searchValue);
				bookstore.setFilmsList(film);
				try {
					JAXBContext context = JAXBContext.newInstance(Filmstore.class);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					m.marshal(bookstore, pw);
				} catch (JAXBException e) {
					e.printStackTrace();
				}

			}
			//part dealing with Text format
		} else {
			if (searchType.equals("byID")) {
				try {
					id = Integer.valueOf(searchValue);
					Film film = filmDAO.getFilmByID(id);
					if (film != null) {
						data = film.toString();
						address = "home";
					} else {
						data = "Film with id=" + id + "doesn't exist";
						address = "home";
					}

				} catch (NumberFormatException ex) {
					data = "ID is in numbers format for example '10004'";
					address = "home";
				}
			} else if (searchType.equals("byName")) {
				ArrayList<Film> film = filmDAO.getFilmByName(searchValue);
				data = film.toString();
				address = "home";
			}
		}

		request.setAttribute("data", data);
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
