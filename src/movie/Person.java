package movie;

import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    private Location location; //Поле может быть null

    public Person(String name, Double height, Float weight, Location location){
        if (weight==0) {
            this.weight=null;
        } else {
            this.weight=weight;
        }
        if (height==0) {
            this.height=null;
        } else {
            this.height=height;
        }
        this.name=name;
        this.location=location;
        this.birthday = new Date();
    }

    public String getPerson(){
        return "Name: " + name + "\n Birthday + " + this.birthday + "\n Height: " + height + "\n Weight: " + weight + "\n Location: " + location.getLocation();
    }
}