package mobility;

public class Point {
    private double x;
    private double y;

    public Point(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException("Coordinates must be non-negative integers.");
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        if (x >= 0) {
            this.x = x;
        }
    }

    public void setY(int y) {
        if (y >= 0) {
            this.y = y;
        }
    }
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}

