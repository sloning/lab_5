package movie;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    private Location location; //Поле может быть null

    public Person(String name, Double height, Float weight, Location location){
        this.name=name;
        this.height=height;
        this.weight=weight;
        this.location=location;
    }
}