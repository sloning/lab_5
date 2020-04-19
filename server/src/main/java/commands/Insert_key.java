package server.src.main.java.commands;

import java.io.IOException;

/**
 * Insert movie by key
 *
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Insert_key() {
        name = "insert";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": добавить новый элемент с заданным ключом";
    }


    /**
     * Replaces movie by id
     *
     * @param parameter1 key to HashMap to insert movie
     */
    @Override
    public void Do(String parameter1) throws IOException {
//        String key = parameter1;
//        if (parameter1 == null) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Введите ключ");
//            System.out.print("$ ");
//            key = scanner.nextLine();
//            if (key.equals("") || key == null) {
//                System.out.println("Ключ не может быть null");
//            } else {
//                Commands commands = new Commands(this.name, key);
//            }
//        } else {
//            MovieCollection movieCollection = new MovieCollection();
//            if (!Execute_script.getSignal()) {
//                FabricOfMovies movieCreator = new FabricOfMovies();
//                Movie newMovie = movieCreator.create();
//                movieCollection.putMovie(key, newMovie);
//
//                System.out.println("В коллекцию успешно добавлен элемент " + newMovie.getName());
//            } else {
//                int c = InputOutput.count;
//                FileReader fileReader = new FileReader(Execute_script.getFileName());
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                while (c-- > 0) bufferedReader.readLine();
//                Movie movie = new Movie();
//                movie.setName(bufferedReader.readLine());
//                int cordX = Integer.parseInt(bufferedReader.readLine());
//                String cordYString = bufferedReader.readLine();
//                if (cordYString.isEmpty()) movie.setCoordinates(cordX);
//                else movie.setCoordinates(cordX, Float.parseFloat(cordYString));
//                movie.setOscarsCount(Integer.parseInt(bufferedReader.readLine()));
//                movie.setLength(Integer.parseInt(bufferedReader.readLine()));
//                movie.setGenre(bufferedReader.readLine());
//                movie.setMpaaRating(bufferedReader.readLine());
//                String dirName = bufferedReader.readLine();
//                double dirHeight = Double.parseDouble(bufferedReader.readLine());
//                float dirWeight = Float.parseFloat(bufferedReader.readLine());
//                String locName = bufferedReader.readLine();
//                int locX = Integer.parseInt(bufferedReader.readLine());
//                String locYString = bufferedReader.readLine();
//                int locZ = Integer.parseInt(bufferedReader.readLine());
//                if (locYString.isEmpty())
//                    movie.setDirector(dirName, dirHeight, dirWeight, new Location(locName, locX, locZ));
//                else
//                    movie.setDirector(dirName, dirHeight, dirWeight, new Location(locName, locX, Long.parseLong(locYString), locZ));
//                movieCollection.putMovie(parameter1, movie);
//
//                System.out.println("В коллекцию успешно добавлен элемент " + movie.getName());
//            }
//        }
    }
}
