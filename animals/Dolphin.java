package animals;
import Olympics.Medal;

public class Dolphin extends MarineAnimal {

    public enum WaterType {
        SEA,
        SWEET
    }
    private WaterType waterType;
    public Dolphin() {
        super();
        this.waterType = WaterType.SEA; // ערך דיפולטי
    }
    public Dolphin(String name, Gender gender, double weight, double speed, Medal[] medals, double diveDepth, WaterType waterType) {
        super(name, gender, weight, speed, medals, diveDepth);
        this.waterType = waterType;
    }
    public Dolphin(Dolphin other) {
        super(other);
        this.waterType = other.waterType;
    }
    public String toString() {
        return "Dolphin{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", diveDepth=" + diveDepth +
                ", position=" + position +
                ", waterType=" + waterType +
                '}';
    }

    public void makeSound() {
        System.out.println("Animal " + this.name + "Click-click");
    }





}
