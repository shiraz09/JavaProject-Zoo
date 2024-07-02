package animals;

import Olympics.Medal;
import mobility.Point;


public abstract class MarineAnimal extends Animal {
    private static final double MAX_DIVE= -800;
    protected double diveDepth;

    public MarineAnimal() {
        super();
        this.diveDepth = 0.0; // or any default value
    }

    public MarineAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, double diveDepth) {
        super(new Point(50, 0), name, gender, weight, speed, medals, new Point(50, 0));
        this.diveDepth = diveDepth;
    }

    public MarineAnimal(MarineAnimal other) {
        super(other);
        this.diveDepth = other.diveDepth;
    }

    public void dive(double depth) {
        if (depth < MAX_DIVE) {
            this.diveDepth = depth;
        } else {
            System.out.println("Cannot dive deeper than " + MAX_DIVE + " meters.");
        }
    }
    public String toString() {
        return "WaterAnimal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", diveDepth=" + diveDepth +
                ", position=" + position +
                '}';
    }



}
