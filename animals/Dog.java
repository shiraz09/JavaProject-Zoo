package animals;
import Olympics.Medal;


public class Dog extends LandAnimal {
    private String breed;
    public Dog() {
        super();
        this.breed = "";
    }
    public Dog(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, String breed) {
        super(name, gender, weight, speed, medals, noLegs);
        this.breed = breed;
    }
    public Dog(Dog other) {
        super(other);
        this.breed = other.breed;
    }
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", noLegs=" + noLegs +
                ", position=" + position +
                ", breed='" + breed + '\'' +
                '}';
    }
    public void makeSound(){
        System.out.println("Animal " + this.name + " Woof Woof");
    }

}
