/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package Graphics;
import animals.Animal;
import mobility.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class Competition {
    private String type;
    protected List<Animal> animals;
    private String location;
    private boolean isRunning;
    protected Point startLocation;

    public Competition(String type) {
        this.type = type;
        this.animals = new ArrayList<>();
        this.isRunning = false;
    }
    public Competition(List<Animal> animals, Point startLocation) {
        this.animals = animals;
        this.startLocation = startLocation;
    }


    public String getType() {
        return type;
    }

    public void addParticipant(Animal animal) {
        animals.add(animal);
    }

    public void setLocation(String location) { // Add this method
        this.location = location;
    }

    public List<Animal> getParticipants() {
        return animals;
    }
    public abstract void start();
}