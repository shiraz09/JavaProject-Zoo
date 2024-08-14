/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package mobility;

/** Description of the variables and the constructor **/
public class Point {
    private int x;
    private int y;
    public Point(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException("Coordinates must be non-negative integers.");
        }
    }

    /** Methods set and get **/
    public int getX() {
        return x;
    }
    public int getY() {
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

    /** Distance calculation method **/
    public int distance(Point other) {
        if (other == null) {
            return 0;
        }
        return (int) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Point point = (Point) obj;

        if (x != point.x) return false;
        return y == point.y;
    }

    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}