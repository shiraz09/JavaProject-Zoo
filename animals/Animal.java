/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package animals;
import Graphics.*;
import Graphics.IDrawable;
import Graphics.IMoveable;
import Olympics.Medal;
import mobility.*;
import mobility.Point;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.image.BufferedImage;

/** Animal is an abstract class that inherits from the Mobile class **/
public abstract class Animal extends Mobile implements ILocatable, IDrawable {
    protected String name;
    protected BufferedImage image;
    private int distanceTraveled;

    public enum Gender {
        MALE,
        FEMALE,
        HERMAPHRODITE
    }

    public enum Direction {
        EAST, WEST, NORTH, SOUTH;
    }

    /** Protected variables **/
    protected Gender gender;
    protected double weight;
    protected double speed;
    protected Medal[] medals;
    protected Point position;
    protected int size;
    private int id;
    protected Location loc;
    protected Orientation orien;
    protected int maxEnergy;
    protected int energyPerMeter;
    protected int currentEnergy;
    protected int energyConsumption;
    protected CompetitionPanel pan;
    protected BufferedImage img1;
    protected BufferedImage img2;
    protected BufferedImage img3;
    protected BufferedImage img4;
    private BufferedImage imgEast, imgWest, imgNorth, imgSouth;
    private String Category;
    private String Type;
    private int energy;
    private boolean isStopped = false;
    private Direction direction;
    private boolean isInLandCompetition;
    protected boolean active;
    private boolean visible;
    private String group;

    /** The default constructor of the Animal class **/
    public Animal() {
        super(new Point(0, 0));
        this.name = "";
        this.gender = Gender.MALE;
        this.weight = 0.0;
        this.speed = 0.0;
        this.medals = new Medal[0];
        this.position = new Point(0, 0);
        this.size = 0;
        this.id = 0;
        this.loc = new Location(0, 0);
        this.orien = Orientation.EAST;
        this.maxEnergy = 0;
        this.energyPerMeter = 0;
        this.pan = null;
        this.img1 = null;
        this.img2 = null;
        this.img3 = null;
        this.img4 = null;
        resetDistance();
        this.distance = 0;
        this.isInLandCompetition = false;
        this.active = true;
        this.visible = true;
    }

    /** The constructor of the Animal class **/
    public Animal(Point location, String name, String category, String type, Gender gender, double weight, double speed, Medal[] medals, Point position, int size, int id, Location loc, Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4) {
        super(location);
        this.name = name;
        this.Category = category;
        this.Type = type;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals;
        this.position = position;
        this.size = size;
        this.id = id;
        this.loc = loc;
        this.orien = orien;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.currentEnergy = maxEnergy;
        this.pan = pan;
        resetDistance();
        loadImages(type);
        this.distance = 0;
        this.isInLandCompetition = false;
        this.active = true;
        System.out.println("Animal created with type: " + type);
    }

    /** Copy constructor **/
    public Animal(Animal other) {
        super(other.getLocation());
        this.name = other.name;
        this.gender = other.gender;
        this.weight = other.weight;
        this.speed = other.speed;
        this.medals = Arrays.copyOf(other.medals, other.medals.length);
        this.position = new Point((int) other.position.getX(), (int) other.position.getY());
        this.size = other.size;
        this.id = other.id;
        this.loc = new Location(other.loc.getX(), other.loc.getY());
        this.orien = other.orien;
        this.maxEnergy = other.maxEnergy;
        this.energyPerMeter = other.energyPerMeter;
        this.currentEnergy = other.currentEnergy;
        this.pan = other.pan;
        this.image = other.image;
        this.isInLandCompetition = other.isInLandCompetition;
        this.active = other.active;
        this.visible = other.visible;
    }


    /** Defining the abstract method **/
    public void makeSound() {
    };

    /** Method to String **/
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + Arrays.toString(medals) +
                ", position=" + position +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orientation getOrien() {
        return orien;
    }

    public void setOrien(Orientation orien) {
        this.orien = orien;
    }

    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getEnergyPerMeter() {
        return energyPerMeter;
    }

    public void setEnergyPerMeter(int energyPerMeter) {
        this.energyPerMeter = energyPerMeter;
    }

    /**
     * Method to get the animal's position
     * Returns the current position of the animal.
     **/
    public Point getPosition() {
        return position;
    }

    /**
     * Method to set the animal's position
     * Sets the position of the animal to a new point.
     **/
    public void setPosition(Point position) {
        this.position = position;
    }
    public void setOrientation(Orientation orien) {
        this.orien = orien;
    }
    public Orientation getOrientation() {
        return orien;
    }


    public final static String PICTURE_PATH = "C:\\Users\\shiraz\\IdeaProjects\\project1_java\\src\\PhotosGraphics2\\";

    public void loadImages(String type) {
        //type = getType();
        if (type == null) {
            throw new IllegalArgumentException("Animal type cannot be null");
        }
        try {
            switch (type.toLowerCase()) {
                case "alligator":
                    imgEast = ImageIO.read(new File("src/PhotosGraphics2/alligator3E.png"));
                    imgWest = ImageIO.read(new File("src/PhotosGraphics2/alligator3W.png"));
                    imgNorth = ImageIO.read(new File("src/PhotosGraphics2/alligator3N.png"));
                    imgSouth = ImageIO.read(new File("src/PhotosGraphics2/alligator3S.png"));
                    break;
                case "cat":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/cat1E.png"));
                    img2 = ImageIO.read(new File("src/PhotosGraphics2/cat1W.png"));
                    img3 = ImageIO.read(new File("src/PhotosGraphics2/cat1N.png"));
                    img4 = ImageIO.read(new File("src/PhotosGraphics2/cat1S.png"));
                    break;
                case "dog":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/dog2E.png"));
                    img2 = ImageIO.read(new File("src/PhotosGraphics2/dog2W.png"));
                    img3 = ImageIO.read(new File("src/PhotosGraphics2/dog2N.png"));
                    img4 = ImageIO.read(new File("src/PhotosGraphics2/dog2S.png"));
                    break;
                case "dolphin":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/dolphin1.png"));
                    break;
                case "eagle":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/eagle1.png"));
                    break;
                case "pigeon":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/pigeon.png"));
                    break;
                case "snake":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/snake2E.png"));
                    img2 = ImageIO.read(new File("src/PhotosGraphics2/snake2W.png"));
                    img3 = ImageIO.read(new File("src/PhotosGraphics2/snake2N.png"));
                    img4 = ImageIO.read(new File("src/PhotosGraphics2/snake2S.png"));
                    break;
                case "whale":
                    img1 = ImageIO.read(new File("src/PhotosGraphics2/whale2.png"));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown animal type: " + type);
            }
        } catch (IOException e) {
            System.out.println("Error loading image for type: " + type);
            e.printStackTrace();
        }
    }
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
    public void changeDirection() {
        switch (orien) {
            case EAST -> orien = Orientation.SOUTH;
            case SOUTH -> orien = Orientation.WEST;
            case WEST -> orien = Orientation.NORTH;
            case NORTH -> orien = Orientation.EAST;
        }
    }

    public void setInitialPosition(String competitionType, int trackNumber) {
        switch (competitionType.toLowerCase()) {
            case "race":
                loc = new Location(0, 0);
                break;
            case "jumping":
                if (trackNumber < 1 || trackNumber > 5) {
                    throw new IllegalArgumentException("Air track must be between 1 and 5.");
                }
                if(trackNumber == 1) {
                    loc = new Location(80, trackNumber * 0);
                    break;
                }
                if(trackNumber == 2) {
                    loc = new Location(80, trackNumber * 85);
                    break;
                }
                if(trackNumber == 3) {
                    loc = new Location(80, trackNumber * 110);
                    break;
                }
                if(trackNumber == 4) {
                    loc = new Location(80, trackNumber * 125);
                    break;
                }
                if(trackNumber == 5) {
                    loc = new Location(80, trackNumber * 130);
                    break;
                }

            case "swimming":
                if (trackNumber < 1 || trackNumber > 4) {
                    throw new IllegalArgumentException("Water track must be between 1 and 4.");
                }
                if(trackNumber == 1) {
                    loc = new Location(80, trackNumber * 100);
                    break;
                }
                if(trackNumber == 2) {
                    loc = new Location(80, trackNumber * 125);
                    break;
                }
                if(trackNumber == 3) {
                    loc = new Location(80, trackNumber * 140);
                    break;
                }
                if(trackNumber == 4) {
                    loc = new Location(80, trackNumber * 145);
                    break;
                }
            default:
                throw new IllegalArgumentException("Unknown competition type: " + competitionType);
        }
        orien = Orientation.EAST;/** Starting movement towards the east **/
        resetDistance();
    }
    public void resetDistance() {
        setDistance(0);
    }

    public String getAnimaleName(){
        return name;
    }
    public String getCategory(){
        return Category;}

    public String getType(){
        String type = this.getClass().getSimpleName();
        return type;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public void setType(String type) {
        this.Type = type;
    }
    public double getSpeed(){
        return speed;
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

    protected void stop() {
        isStopped = true;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public void eat(int amount) {
        currentEnergy = amount;
        this.energyConsumption += amount;
    }
    public boolean canEat() {
        return visible;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
    public void setCurrentEnergy(int energy) {
        this.currentEnergy = energy;
    }

    public void setEnergyConsumption(int consumption) {
        this.energyConsumption = consumption;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public void setSpeed(int speed) { this.speed = speed; }

    public void setDistance(int distance) { this.distanceTraveled = distance; }


    public void changeDirection(Direction newDirection) {
        this.direction = newDirection; /** Set the new direction **/

        /** Determine the appropriate image based on the animal type and direction **/
        String imageName = getImageForDirection(newDirection);
        setImage(imageName);
    }

    private String getImageForDirection(Direction direction) {
        switch (this.getType()) {
            case "Dog":
                return switch (direction) {
                    case EAST -> "dog2E.png";
                    case NORTH -> "dog2N.png";
                    case SOUTH -> "dog2S.png";
                    case WEST -> "dog2W.png";
                };
            case "Cat":
                return switch (direction) {
                    case EAST -> "cat1E.png";
                    case NORTH -> "cat1N.png";
                    case SOUTH -> "cat1S.png";
                    case WEST -> "cat1W.png";
                };
            case "Snake":
                return switch (direction) {
                    case EAST -> "snake2E.png";
                    case NORTH -> "snake2N.png";
                    case SOUTH -> "snake2S.png";
                    case WEST -> "snake2W.png";
                };
            case "Alligator":
                if (isInLandCompetition) {
                    return switch (direction) {
                        case EAST -> "alligator3E.png";
                        case NORTH -> "alligator3N.png";
                        case SOUTH -> "alligator3S.png";
                        case WEST -> "alligator3W.png";
                    };
                } else {
                    return "alligator3E.png"; /** Default image when not in a land-based competition **/
                }

            default:
                System.err.println("Error: Unexpected animal type: " + this.getType());
                return null; /** Fallback image **/
        }
    }

    private void setImage(String imageName) {
        ImageIcon imageIcon = new ImageIcon("src/PhotosGraphics2/" + imageName);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public Point getCurrentPosition() {
        return this.position;
    }

    public void resetEnergy() {
        this.energy = currentEnergy;
    }

    public boolean isActive() {
        return this.active && this.currentEnergy > 0;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void consumeEnergy(int amount) {
        this.energy -= amount;
        if (this.energy <= 0) {
            this.energy = 0;
            this.active = false; /** An animal is inactive when it runs out of energy **/
        }
    }

    public void updateDistance() {
        distance += speed;
    }

    public void depleteEnergy() {
        /** Deplete energy based on consumption **/
        if (energy < 0) {
            energy = 0;
        }
    }
    public boolean isStopped() {
        return isStopped;
    }


    // Remove the resume method
    protected void resume() {
        isStopped = false;
    }
    public void consumeEnergy() {
        if (currentEnergy > 0) {
            currentEnergy--;
        }
        if (currentEnergy <= 0) {
            currentEnergy = 0;
            active = false; // Stop the animal if energy is depleted
        }
    }


}