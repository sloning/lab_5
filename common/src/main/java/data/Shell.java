package data;

import movie.Movie;

import java.io.Serializable;

public class Shell implements Serializable {
    private String name = null;
    private String parameter = null;
    private Movie movie = null;
    private String user = null;

    public Shell(String name, String parameter, Movie movie) {
        this.name = name;
        this.parameter = parameter;
        this.movie = movie;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName(){
        return name;
    }

    public String getParameter(){
        return parameter;
    }

    public Movie getMovie(){
        return movie;
    }

    public String getUser() {
        return user;
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
