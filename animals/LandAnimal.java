package animals;

import Olympics.Medal;
import mobility.Point;


public abstract class LandAnimal extends Animal {
    protected int noLegs;

    public LandAnimal() {
        super();
        noLegs = 0;
    }

    public LandAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs) {
        super(new Point(0, 20), name, gender, weight, speed, medals, new Point(0, 20));
        this.noLegs = noLegs;
    }

    public LandAnimal(LandAnimal other) {
        super(other);
        this.noLegs = other.noLegs;
    }

    public String toString() {
        return "TerrestrialAnimal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", noLegs=" + noLegs +
                ", position=" + position +
                '}';
    }
}
