/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package animals;
import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Location;
import mobility.Orientation;
import mobility.Point;
import java.awt.image.BufferedImage;

/** LandAnimal is an abstract class that inherits from the Animal class **/
public class LandAnimal extends Animal {
    protected int noLegs;
    public LandAnimal() {
        super();
        noLegs = 0;
        this.distance = 0;
    }

    /** The constructor of the LandAnimal class **/
    public LandAnimal(String name,String category,String type, Gender gender, double weight, double speed, Medal[] medals, int noLegs,int size, int id, Location loc, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,String competitionType, int trackNumber) {
        super(new Point(0, 20), name,category,type, gender, weight, speed, medals, new Point(0, 20), size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.noLegs = noLegs;
        this.setInitialPosition(competitionType, trackNumber);
        this.setCategory("Terrest");
        this.distance = 0;
    }

    /** Copy constructor **/
    public LandAnimal(LandAnimal other) {
        super(other);
        this.noLegs = other.noLegs;
    }

    /** Method to String **/
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void walk() {
        this.distance += 1;
    }
}