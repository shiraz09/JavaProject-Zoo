package mobility;

import java.awt.*;

public abstract class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;

    public Boolean setLocation(Point point) {
        if (point != null && point.getX() >= 0 && point.getY() >= 0) {
            totalDistance += calcDistance(point);
            location = point;
            return true;
        }
        return false;

    }

    public Mobile(Point location) {
        this.location = location;
        this.totalDistance = 0;
    }


    public void addTotalDistance(double distance) {
        if (distance > 0) {
            totalDistance += distance;
        }
    }

    public double calcDistance(Point point) {
        return Math.sqrt(Math.pow(point.getX() - location.getX(), 2) + Math.pow(point.getY() - location.getY(), 2));
    }

    public double move(Point point) {
        if (setLocation(point)) {
            return calcDistance(point);
        }
        return 0;
    }
    public Point getLocation() {
        return location;
    }

}

