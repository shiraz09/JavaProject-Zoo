/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package mobility;

import java.awt.*;

/** Mobile is an abstract class that inherits from the ILocatable implements **/
public abstract class Mobile implements ILocatable {

    protected double distance;
    /** private variables **/
    private Point location;
    private double totalDistance;

    /** Boolean method of setLocation **/
    public Boolean setLocation(Point point) {
        if (point != null && point.getX() >= 0 && point.getY() >= 0) {
            totalDistance += calcDistance(point);
            location = point;
            return true;
        }
        return false;
    }

    /** Constructors **/
    public Mobile(Point location) {
        this.location = location;
        this.totalDistance = 0;
    }


    /** Adds the distance provided as a parameter to the total distance the object has traveled **/
    public void addTotalDistance(double distance) {
        if (distance > 0) {
            totalDistance += distance;
        }
    }

    /** Calculates the distance between the given point and the current position of the object **/
    protected double calcDistance(Point point) {
        if (point == null) return 0;
        double dx = point.getX() - this.location.getX();
        double dy = point.getY() - this.location.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * A method for calculating the distance between two points in a plane coordinate system
     **/
    public double move(Point point) {
        if (setLocation(point)) {
            return calcDistance(point);
        }
        return 0;
    }

    /** Returns the current position of the object **/
    public Point getLocation() {
        return location;
    }


}