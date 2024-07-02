package animals;
import Olympics.Medal;


public class Cat extends LandAnimal {
    private boolean castrated;
    public Cat() {
        super();
        this.castrated = false;
    }
    public Cat(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, boolean castrated) {
        super(name, gender, weight, speed, medals, noLegs);
        this.castrated = castrated;
    }
    public Cat(Cat other) {
        super(other);
        this.castrated = other.castrated;
    }
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", noLegs=" + noLegs +
                ", position=" + position +
                ", castrated=" + castrated +
                '}';
    }
    public void makeSound(){
        System.out.println("Animal " + this.name + "Meow");
    }





}
