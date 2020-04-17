package src.main.java.lab.query;

import src.main.java.lab.movie.Movie;

import java.io.Serializable;

public class Shell implements Serializable {
    private String name;
    private String parameter;
    private Movie movie;
    public Shell(String name, String parameter, Movie movie){
        this.name = name;
        this.parameter = parameter;
        this.movie = movie;
    }


}
