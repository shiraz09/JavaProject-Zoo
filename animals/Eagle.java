package animals;
import Olympics.Medal;


public class Eagle extends AerialAnimal {
    private double altitudeOfFlight;
    public static final double MAX_ALTITUDE = 1000;
    public Eagle() {
        super();
        this.altitudeOfFlight = 0;
    }
    public Eagle(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan, double altitudeOfFlight) {
        super(name, gender, weight, speed, medals, wingspan);
        if (altitudeOfFlight <= MAX_ALTITUDE) {
            this.altitudeOfFlight = altitudeOfFlight;
        } else {
            throw new IllegalArgumentException("Altitude of flight cannot exceed " + MAX_ALTITUDE);
        }
    }
    public Eagle(Eagle other) {
        super(other);
        this.altitudeOfFlight = other.altitudeOfFlight;
    }
    public String toString() {
        return "Eagle{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", position=" + position +
                ", wingspan=" + wingspan +
                ", altitudeOfFlight=" + altitudeOfFlight +
                '}';
    }
    public void makeSound() {
        System.out.println("Animal " + this.name + "Clack-wack-chack");
    }


}
