/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package mobility;

public class Location extends Point {
    public enum Orientation {
        EAST,
        SOUTH,
        WEST,
        NORTH;
    }

    private Orientation direction;
    public Location(int x, int y) {
        super(x, y);
    }
    public Orientation getDirection() {
        return direction;
    }
    public void setDirection(Orientation direction) {
        this.direction = direction;
    }
    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

    public String toString() {
        return "Location{" + "x=" + getX() + ", y=" + getY() + '}';
    }
}