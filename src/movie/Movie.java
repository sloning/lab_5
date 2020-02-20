package movie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Movie {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer length; //Поле может быть null, Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person director; //Поле не может быть null

    public Movie(long id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = new Date();
    }

    public void setLength(int length){
        this.length=length;
    }

    public void setGenre(String genre){
        if (genre=="COMEDY"){
            this.genre=MovieGenre.COMEDY;
        } else if (genre=="MUSICAL"){
            this.genre=MovieGenre.MUSICAL;
        } else if (genre=="FANTASY"){
            this.genre=MovieGenre.FANTASY;
        } if (genre=="пропустить"){

        }
    }

    public void setMpaaRating(String rating){
        if (rating=="G"){
            this.mpaaRating=MpaaRating.G;
        } else if (rating=="PG"){
            this.mpaaRating=MpaaRating.PG;
        } else if (rating=="PG_13"){
            this.mpaaRating=MpaaRating.PG_13;
        } if (rating=="пропустить"){

        }
    }

    public void setOscarsCount(int oscarsCount){
        this.oscarsCount=oscarsCount;
    }

    public void setCoordinates(int x, float y){
        coordinates=new Coordinates(x,y);
    }

    public void setDirector(String name, Double height, Float weight, Location location){
        director=new Person(name,height,weight,location);
    }

    public int getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre.toString();
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(this.creationDate);
        return "Movie: " + name + " (ID " + length +") " + date;
    }
}