package commands;

import movie.Movie;

import java.io.IOException;

public interface ICommand {
    String Do(String parameter, Movie movie, String user) throws IOException;

    String info();
}

