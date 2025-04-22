import java.util.Random;

/**
 * A Point class to pick out location easily on the map 
 */
public class Point {
    public final int x, y; 
    private static final Random r = new Random();

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    //This is used to calculate h value
    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * Generate random point within specified range
     * @param x_min minimum value of x-coordinate
     * @param x_max maximum value of x-coordinate
     * @param y_min minimum value of y-coordinate
     * @param y_max maximum value of y-coordinate
     * @return
     */
    public static Point createRandomPoint(int x_min, int x_max, int y_min, int y_max) {
        int x = r.nextInt(x_max) + x_min;
        int y = r.nextInt(y_max) + y_min;
        return new Point(x, y);
    }
    
    /**
     * checks if current point is equal to other object or point
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * it is essential to override hashCode() to use Point as a key in java sets
     */
    @Override
    public int hashCode() {
        return 31 * (31 + x) + y;
    }

    /**
     * converts current point in string formatted
     */
    @Override
    public String toString() {
        return "x: " + this.x + " y: "+ this.y;
    }
}
