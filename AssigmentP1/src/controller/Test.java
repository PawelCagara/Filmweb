package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dataBase.FilmDAO;
import models.Film;
import models.Filmstore;


public class Test {

	public static void main(String[] args) throws JAXBException, IOException, SQLException {
		// TODO Auto-generated method stub
		//FilmDAO dao = new FilmDAO();
		//ArrayList<Film> allFilms = dao.getAllFilms();
		//ArrayList<Film> filmList = new ArrayList<>();
		//System.out.println(allFilms.get(2).getTitle());
		/*
		String user = "cagarap";
        String password = "yestErla7";            
        String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;             
        Connection con = DriverManager.getConnection(url, user, password); 
       Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select max(id) from films");
        rs.next();
        //searching through DB for max ID then adding 1 to create next ID
        int id = rs.getInt(1)+1;
	        System.out.println("Id ="+id);
	*/
		
		//dao.addFilm(11336, "ss", 1111, "sss", "sss", "sss");
	
	}
}
