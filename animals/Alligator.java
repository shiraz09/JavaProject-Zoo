/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package animals;
import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Location;
import mobility.Orientation;
import mobility.Point;

import java.awt.*;
import java.awt.image.BufferedImage;


/** Alligator is an abstract class that inherits from the MarineAnimal class **/
public class Alligator extends Animal implements IReptile,IMarineAnimal,ILandAnimal{
    private String AreaOfLiving;
    private double energyAmount;
    private double Energy_consumption;
    private double distance;
    private final MarineAnimal marineAnimal;
    private final LandAnimal landAnimal;

    public Alligator() {
        super();
        marineAnimal=new MarineAnimal();
        landAnimal=new LandAnimal();
        this.AreaOfLiving = "";
        this.energyAmount=0;
        this.Energy_consumption=0;
        this.distance=0;
    }

    /** The constructor of the Alligator class **/
    public Alligator(String name, Gender gender, double weight, double speed, double diveDepth, Medal[] medals, String areaOfLiving, int size, int id, Location loc, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, String competitionType, int trackNumber, int noLegs, String category, String type) {
        super(new Point(0, 0), name, category, type, gender, weight, speed, medals, new Point(0, 0), size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        marineAnimal = new MarineAnimal(name, "Water", "Alligator", gender, weight, speed, medals, diveDepth, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4, competitionType, trackNumber);
        landAnimal = new LandAnimal(name, "Terrestrial", "Alligator", gender, weight, speed, medals, noLegs, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4, competitionType, trackNumber);
        this.AreaOfLiving = areaOfLiving;
    }

    public Alligator(String name,double speed,double energyAmount,double Energy_consumption,double distance){
        marineAnimal=new MarineAnimal();
        landAnimal=new LandAnimal();
        this.name=name;
        this.speed=speed;
        this.energyAmount=energyAmount;
        this.Energy_consumption=Energy_consumption;
        this.distance=distance;
    }

    /** Copy constructor **/
    public Alligator(Alligator other) {
        super(other);
        this.AreaOfLiving = other.AreaOfLiving;
        this.marineAnimal = new MarineAnimal(other.marineAnimal);
        this.landAnimal = new LandAnimal(other.landAnimal);
    }

    /** Method to String **/
    public String toString() {
        return "Alligator{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", diveDepth=" +  marineAnimal.diveDepth +
                ", position=" + position +
                ", areaOfLiving='" + AreaOfLiving + '\'' +
                '}';
    }

    /** The makeSound() method prints a message that describes the sound the animal makes. **/
    public void makeSound() {
        System.out.println("Animal " + this.name +" "+ "Roar");
    }

    public void speedUp(int additionalSpeed) {
        if (additionalSpeed > 0) {
            this.speed += additionalSpeed;
            System.out.println("The speed of " + this.name + " has increased by " + additionalSpeed + " and is now " + this.speed);
        } else {
            System.out.println("Invalid speed increase value: " + additionalSpeed);
        }
    }

    public void dive(double depth) {
        marineAnimal.dive(depth);
    }

    public void walk() {
        landAnimal.walk();
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
            case SOUTH:
                currentImg = img2;
                break;
            case WEST:
                currentImg = img3;
                break;
            case NORTH:
                currentImg = img4;
                break;
        }

        if (currentImg != null) {
            g.drawImage(currentImg, (int) loc.getX(), (int) loc.getY(), 65, 65, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect((int) loc.getX(), (int) loc.getY(), 65, 65);
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
}