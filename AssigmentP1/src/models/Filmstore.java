package models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "XML")

public class Filmstore {

	
	 // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "filmList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "film")
    private ArrayList<Film> filmList;
    @XmlElement(name = "film")
    private Film film;


    public void setFilmsList(ArrayList<Film> filmList) {
        this.filmList = filmList;
    }
    
    public void setFilmById(Film film) {
    	this.film = film;
    }

    public ArrayList<Film> getFilmsList() {
        return filmList;
    }
}
