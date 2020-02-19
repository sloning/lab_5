package commands;

import movie.Movie;

import java.util.*;

public class Count_by_genre implements ICommand {
    public Count_by_genre(){
        Commands.addNewCommand("count_by_genre", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) {      //Да я хуй знает как это делать
        List<HashMap.Entry<Long, Movie>> entryList = new ArrayList<HashMap.Entry<Long, Movie>>(map.entrySet());

        Collections.sort(
                entryList, new Comparator<Map.Entry<Long, Movie>>() {
                    @Override
                    public int compare(HashMap.Entry<Long, Movie> integerEmployeeEntry,
                                       HashMap.Entry<Long, Movie> integerEmployeeEntry2) {
                        return integerEmployeeEntry.getValue().name
                                .compareTo(integerEmployeeEntry2.getValue().name);
                    }
                }
        );


    }
}
