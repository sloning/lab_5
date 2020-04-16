package client.sirializator;

import client.commands.ICommand;
import client.movie.Movie;

import java.io.Serializable;

// 3 случая:
// 1) команда
// 2) команда + объект (фильм)
// 3) команда + агрумент (key, file_name, genre, name)
public class SerializedObject implements Serializable {
    private ICommand command;
    private Movie movie = null;
    private String argument = null;

    public SerializedObject(ICommand command) {
        this.command = command;
    }

    public SerializedObject(ICommand command, Movie movie) {
        this.command = command;
        this.movie = movie;
    }

    public SerializedObject(ICommand command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    public ICommand getCommand(){
        return command;
    }

    public Movie getMovie(){
        return movie;
    }

    public String getArgument(){
        return argument;
    }

    @Override
    public String toString() {
        return "Serialized command{" +
                "command=" + command.toString() +
                ", movie=" + movie.toString() +
                ", argument=" + argument;
    }
}
