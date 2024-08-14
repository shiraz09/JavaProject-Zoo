/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package animals;
import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Location;
import mobility.Orientation;
import mobility.Point;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/** AerialAnimal is an abstract class that inherits from the Animal class **/
public class AerialAnimal extends Animal{
    protected double wingspan;
    public AerialAnimal() {
        super();
        this.wingspan = 0;
        this.distance = 0;
    }

    /** The constructor of the AerialAnimal class **/
    public AerialAnimal(String name,String category,String type, Gender gender, double weight, double speed, Medal[] medals, double wingspan, int size, int id, Location loc, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,String competitionType, int trackNumber){
        super(new Point(0,100),name,category,type,gender,weight,speed,medals, new Point(0,100), size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.wingspan=wingspan;
        this.setInitialPosition(competitionType, trackNumber);
        this.distance = 0;
    }

    /** Copy constructor **/
    public AerialAnimal(AerialAnimal other) {
        super(other);
        this.wingspan = other.wingspan;
    }

    /** Method to String **/
    public String toString() {
        return "AerialAnimal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + Arrays.toString(medals) +
                ", wingspan=" + wingspan +
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