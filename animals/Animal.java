package animals;

import Olympics.Medal;
import mobility.Mobile;
import mobility.Point;

import java.util.Arrays;


public abstract class Animal extends Mobile {
    protected String name;
    public enum Gender{
        MALE,
        FEMALE,
        HERMAPHRODITE

    }
    protected Gender gender;
    protected double weight;
    protected double speed;
    protected Medal[] medals;
    protected Point position;
    public Animal() {
        super(new Point(0, 0));
        this.name = "";
        this.gender = Gender.MALE;
        this.weight = 0.0;
        this.speed = 0.0;
        this.medals = new Medal[0];
        this.position = new Point(0, 0);
    }

    public Animal(Point location,String name,Gender gender,double weight,double speed, Medal[] medals,Point position) {
        super(location);
        this.name=name;
        this.gender = gender;
        this.weight=weight;
        this.speed=speed;
        this.medals = medals;
        this.position = position;
    }

    public Animal(Animal other) {
        super(other.getLocation());
        this.name = other.name;
        this.gender = other.gender;
        this.weight = other.weight;
        this.speed = other.speed;
        this.medals = new Medal[other.medals.length];
        for (int i = 0; i < other.medals.length; i++) {
            this.medals[i] = other.medals[i];
        }
        // Copying the Point object for position
        this.position = new Point((int) other.position.getX(), (int) other.position.getY());
    }

    public abstract void makeSound();

    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + Arrays.toString(medals) +
                ", position=" + position +
                '}';
    }


}
