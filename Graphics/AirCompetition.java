package Graphics;

import Graphics.Competition;
import animals.Animal;
import mobility.Orientation;
import mobility.Point;
import java.util.List;

public class AirCompetition extends Competition {

    /**
     * Constructor for AirCompetition
     * Initializes an air competition with a list of animals and a starting location.
     *
     * @param animals A list of Animal objects participating in the competition.
     * @param startLocation The starting location for all animals in the competition.
     **/
    public AirCompetition(List<Animal> animals, Point startLocation) {
        super(animals, startLocation); // Call the constructor of the superclass Competition
    }

    /**
     * Method to start the competition
     * Sets the initial position and orientation of all participating animals.
     **/
    public void start() {
        for (Animal animal : animals) {
            animal.setPosition(new Point(0, 0)); // Set each animal's position to (0,0)
            animal.setOrientation(Orientation.EAST); // Set each animal's orientation to EAST
        }
    }
}
