package animals;
import Olympics.Medal;

public class Snake extends LandAnimal {
    public enum Poisonous{
        NON_POISONOUS,
        MILDLY_POISONOUS,
        HIGHLY_POISONOUS
    }
    private Poisonous poisonous;
    private double length;
    public Snake() {
        super();
        this.poisonous = Poisonous.NON_POISONOUS;
        this.length = 0;
    }
    public Snake(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, Poisonous poisonous, double length) {
        super(name, gender, weight, speed, medals, noLegs);
        this.poisonous = poisonous;
        this.length = length;
    }
    public Snake(Snake other) {
        super(other);
        this.poisonous = other.poisonous;
        this.length = other.length;
    }
    public String toString() {
        return "Snake{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", noLegs=" + noLegs +
                ", position=" + position +
                ", poisonous=" + poisonous +
                ", length=" + length +
                '}';
    }
    public void makeSound() {
        System.out.println("Animal " + this.name + "ssssssss");
    }




}
