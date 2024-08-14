package Graphics;

import animals.Animal;
import mobility.Orientation;
import mobility.Point;
import java.util.List;

public class WaterCompetition extends Competition {

    /**
     * Constructor for WaterCompetition
     * Initializes a water competition with a list of animals and a starting location.
     *
     * @param animals A list of Animal objects participating in the competition.
     * @param startLocation The starting location for all animals in the competition.
     **/
    public WaterCompetition(List<Animal> animals, Point startLocation) {
        super(animals, startLocation);
    }

    /**
     * Method to start the competition
     * Sets the initial position and orientation of all participating animals to simulate the beginning of the race.
     **/
    public void start() {
        for (Animal animal : animals) {
            animal.setPosition(new Point(0, 0));
            animal.setOrientation(Orientation.EAST);
        }
    }
}