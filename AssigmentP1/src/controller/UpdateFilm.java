package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		FilmDAO dao = FilmDAO.getSingletonObject();

		String title = request.getParameter("title");

		String director = request.getParameter("director");
		String stars = request.getParameter("stars");
		String review = request.getParameter("review");

		PrintWriter pw;
		response.setContentType("text/html");
		pw = response.getWriter();

		try {
			int year = Integer.parseInt(request.getParameter("year"));
			try {
				int id = Integer.valueOf(request.getParameter("id"));

				try {
					dao.updateFilm(id, title, year, director, stars, review);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NumberFormatException ex) {
				pw.println("Update failed: ID is in numbers format for example '10004'");
				pw.print("<br><a href=\"updateFilm.html\"><button type=\"button\">Update again</button></a>");
			}
		} catch (NumberFormatException ex) {
			pw.println("Update failed: year is in numbers format for example '2005'");
			pw.print("<br><a href=\"updateFilm.html\"><button type=\"button\">Update again</button></a>");
		}

		pw.println("Update failed, check if you are using correct ID");
		pw.print("<br><a href=\"index.html\"><button type=\"button\">Home page</button></a>");

		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
