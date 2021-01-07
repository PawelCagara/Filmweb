package dataBase;

import java.util.ArrayList;

import models.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "cagarap";
    String password = "yestErla7";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;

    private static FilmDAO singletonFilmDAO;
    
	private FilmDAO() {}
	
	public static synchronized FilmDAO getSingletonObject() {
		if (singletonFilmDAO == null) {
			singletonFilmDAO = new FilmDAO();
		}
		return singletonFilmDAO;
	}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   public ArrayList<Film> getFilmByName(String name){
	   ArrayList<Film> filmsByName = new ArrayList<Film>();
	   openConnection();
	   
	   try{
		    String selectSQL = "select * from films where title like '%"+name+"%'";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	filmsByName.add(oneFilm);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   
	   return filmsByName;
   }
   
  public void deleteFilm(int id) throws SQLException {
	  openConnection(); 
	  String query = "delete from films where id="+id;         
	  stmt.executeUpdate(query);
	     stmt.close();     
	  closeConnection();          
  }
  
  public void updateFilm(int id, String title, int year, String director, String stars,
			String review) throws SQLException {
	  openConnection();
	  String query = "Update films set title= '" + title + "',year= '" + year + "',director= '" + director + "'"
	  		+ ",stars= '" + stars + "', review= '" + review + "' where id="+id;
	  stmt.executeUpdate(query);
	     stmt.close();     
	  closeConnection();
  }
  
  public void addFilm(int id, String title, int year, String director, String stars,
			String review) throws SQLException {
	  openConnection(); 
	  String query = "insert into films values ('"+id+"', '"+title+"', '"+year+"', '"+director+"', '"+stars+"', '"+review+"')";
      stmt.executeUpdate(query);
	     stmt.close();     
	  closeConnection();	  
  }
  

   
  
   
   
   
   
}
