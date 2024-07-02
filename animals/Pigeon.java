package animals;
import Olympics.Medal;


public class Pigeon extends AerialAnimal {
    private String family;
    public Pigeon() {
        super();
        this.family = "";
    }
    public Pigeon(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan, String family) {
        super(name, gender, weight, speed, medals, wingspan);
        this.family = family;
    }

    public Pigeon(Pigeon other) {
        super(other);
        this.family = other.family;
    }
    public String toString() {
        return "Pigeon{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", position=" + position +
                ", wingspan=" + wingspan +
                ", family='" + family + '\'' +
                '}';
    }

    public void makeSound() {
        System.out.println("Animal " + this.name + "Arr-rar-rar-rar-raah");
    }



}
