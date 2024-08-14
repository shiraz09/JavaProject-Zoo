/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package animals;
import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Location;
import mobility.Orientation;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/** Eagle is an abstract class that inherits from the AerialAnimal class **/
public class Eagle extends AerialAnimal {
    private double altitudeOfFlight;
    private double energyAmount;
    private double Energy_consumption;
    private double distance;
    public static final double MAX_ALTITUDE = 1000;
    public Eagle() {
        super();
        this.altitudeOfFlight = 0;
        this.energyAmount=0;
        this.Energy_consumption=0;
        this.distance=0;
    }

    /** The constructor of the Eagle class **/
    public Eagle(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan, double altitudeOfFlight, int size, int id, Location loc, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,String competitionType, int trackNumber) {
        super(name,"Air","Eagle", gender, weight, speed, medals, wingspan,size,id,loc,orien,maxEnergy,energyPerMeter,pan,img1,img2,img3,img4,competitionType, trackNumber);

        if (altitudeOfFlight <= MAX_ALTITUDE) {
            this.altitudeOfFlight = altitudeOfFlight;
        } else {
            throw new IllegalArgumentException("Altitude of flight cannot exceed " + MAX_ALTITUDE);
        }
    }

    public Eagle(String name,double speed,double energyAmount,double Energy_consumption,double distance){
        this.name=name;
        this.speed=speed;
        this.energyAmount=energyAmount;
        this.Energy_consumption=Energy_consumption;
        this.distance=distance;
    }

    /** Copy constructor **/
    public Eagle(Eagle other) {
        super(other);
        this.altitudeOfFlight = other.altitudeOfFlight;
    }

    /** Method to String **/
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

    /** The makeSound() method prints a message that describes the sound the animal makes. **/
    public void makeSound() {
        System.out.println("Animal " + this.name +" "+ "Clack-wack-chack");
    }
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File("src/PhotosGraphics2/eagle1.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image for " + nm + ": " + e.getMessage());
        }
    }

    public void move() {
        if (!active || currentEnergy <= 0) return;

        // וודא ש-energyPerMeter לא שווה לאפס כדי להימנע מחלוקה באפס
        if (energyPerMeter == 0) {
            System.err.println("Error: energyPerMeter is zero, cannot move the animal.");
            return;
        }

        // Calculate new position based on direction and speed
        int distanceToMove = (int) Math.min(speed, currentEnergy / energyPerMeter);

        switch (orien) {
            case EAST:
                position = new Point(position.getX() + distanceToMove, position.getY());
                break;
            case WEST:
                position = new Point(position.getX() - distanceToMove, position.getY());
                break;
            case NORTH:
                position = new Point(position.getX(), position.getY() - distanceToMove);
                break;
            case SOUTH:
                position = new Point(position.getX(), position.getY() + distanceToMove);
                break;
        }

        currentEnergy -= distanceToMove * energyPerMeter;

        // Check if energy is depleted
        if (currentEnergy <= 0) {
            currentEnergy = 0;
            active = false; // Animal stops moving
        }
    }
    public void drawObject (Graphics g){
        if (!active) {
            return;
        }

        /** if currentImg does not exist **/
        BufferedImage currentImg = null;
        switch (orien) {
            case EAST:
                currentImg = img1;
                break;

        }

        if (currentImg != null) {
            g.drawImage(currentImg, (int) loc.getX(), (int) loc.getY(), 65, 65, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect((int) loc.getX(), (int) loc.getY(), 65, 65);
        }
    }
}