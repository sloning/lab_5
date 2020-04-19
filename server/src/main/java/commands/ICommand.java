package commands;

import movie.Movie;

import java.io.IOException;

public interface ICommand {
    String Do(String parameter, Movie movie) throws IOException;

    String info();
}

