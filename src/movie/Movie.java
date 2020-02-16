package movie;

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

    public Movie(String name){
        this.name = name;
    }
}