/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package animals;
import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Location;
import mobility.Orientation;
import mobility.Point;
import java.awt.image.BufferedImage;

/** MarineAnimal is an abstract class that inherits from the Animal class **/
public class MarineAnimal extends Animal {
    private static final double MAX_DIVE= -800;
    protected double diveDepth;

    /** The default constructor **/
    public MarineAnimal() {
        super();
        this.diveDepth = 0;
        this.distance = 0;
    }

    /** The constructor of the MarineAnimal class **/
    public MarineAnimal(String name,String category,String type, Gender gender, double weight, double speed, Medal[] medals, double diveDepth, int size, int id, Location loc, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,String competitionType, int trackNumber) {
        super(new Point(50, 0), name,category,type, gender, weight, speed, medals, new Point(50, 0), size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.diveDepth = diveDepth;
        this.setInitialPosition(competitionType, trackNumber);
        this.distance = 0;
    }

    /** Copy constructor **/
    public MarineAnimal(MarineAnimal other) {
        super(other);
        this.diveDepth = other.diveDepth;
    }

    /** The method allows the marine animal to dive deep **/
    public void dive(double depth) {
        if (depth < MAX_DIVE) {
            this.diveDepth = depth;
        } else {
            System.out.println("Cannot dive deeper than " + MAX_DIVE + " meters.");
        }
    }

    /** Method to String **/
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}