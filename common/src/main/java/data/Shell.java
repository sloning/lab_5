package data;

import movie.Movie;

import java.io.Serializable;

public class Shell implements Serializable {
    private String name = null;
    private String parameter = null;
    private Movie movie = null;
    public Shell(String name, String parameter, Movie movie){
        this.name = name;
        this.parameter = parameter;
        this.movie = movie;
    }

    @Override
    public String toString() {
        if ((parameter.equals(null) && (movie.equals(null)))) {
            return name;
        } else if (parameter.equals(null)) {
            return name + " " + parameter;
        } else return name + " " + parameter + " (movie: " + movie.getName() + " )";
    }
}
