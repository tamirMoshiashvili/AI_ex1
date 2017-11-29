import java.util.Objects;

/**
 * point has x and y coordinates.
 */
public class Point implements Descriptable<Point> {
    private int x, y;

    /**
     * constructor.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get the x-coordinate.
     *
     * @return int.
     */
    public int getX() {
        return x;
    }

    /**
     * get the y-coordinate.
     *
     * @return int.
     */
    public int getY() {
        return y;
    }

    /**
     * get the direction needed to go from this point to the given.
     *
     * @param other other point.
     * @return string that represents the direction.
     */
    @Override
    public String getInstructionTo(Point other) {
        if (this.x < other.getX() && this.y < other.getY()) {
            return "RD";
        }
        if (this.x > other.getX() && this.y < other.getY()) {
            return "RU";
        }
        if (this.x > other.getX() && this.y > other.getY()) {
            return "LU";
        }
        if (this.x < other.getX() && this.y > other.getY()) {
            return "LD";
        }
        if (this.x < other.getX()) {
            return "D";
        }
        if (this.x > other.getX()) {
            return "U";
        }
        if (this.y < other.getY()) {
            return "R";
        }
        if (this.y > other.getY()) {
            return "L";
        }
        // this section is unreachable
        return "Error!";
    }

    /**
     * check if this point is equal to the given object.
     *
     * @param other other object.
     * @return true if the object is point and has the same coordinates, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Point) {
            Point otherPoint = (Point) other;
            return this.x == otherPoint.getX() && this.y == otherPoint.getY();
        }
        return false;
    }
}
