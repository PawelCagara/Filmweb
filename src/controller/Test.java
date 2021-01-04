package controller;

import java.util.ArrayList;

import dataBase.FilmDAO;
import models.Film;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		System.out.println(allFilms.get(2).getTitle());
		
		int id = 10001+allFilms.size();
		System.out.println(id);
		
	}

}
