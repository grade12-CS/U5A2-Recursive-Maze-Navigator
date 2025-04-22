import becker.robots.*;

/**
 * a helper class that makes robot movement easier
 */
public class DaveSoftware extends RobotSE {
    /**
     * 
     * @param city the city robot is created on
     * @param y the y-coordinate of robot's starting point 
     * @param x the x-coordinate of robot's starting point
     */
    public DaveSoftware(City city, int y, int x) {
        super(city, y, x, Direction.SOUTH, 0); 
    }

    /**
     * Simpler method to print a string
     * @param s string to print
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * get the x-coordinate of the current point
     * @return x-coordinate
     */
    public int getX() {
        return getAvenue();
    }
    
    /**
     * get the y-coordinate of the current point
     * @return y-coordinate
     */
    public int getY() {
        return getStreet();
    }

    /**
     * get the current point
     * @return current point
     */
    public Point getCurrent() {
        return new Point(getX(), getY());
    }

    /**
     * turn and then move in the defined direction and number of spaces.
     * @param dir direction to move
     * @param n number of spaces to move
     */
    public void move(Direction dir, int n) {
        if (n < 0) return;
        turn(dir);
        super.move(n);
    }

    /**
     * moves robot by certain amounts of units in x and y directions
     * @param x vector x-coordinate to move. moves to east if positive, moves to west if negative
     * @param y vector y-coordinate to move, moves to south if positive, moves to north if negative
     */
    public void move(int x, int y) {
        move(x > 0 ? Direction.EAST : Direction.WEST, Math.abs(x));
        move(y > 0 ? Direction.SOUTH : Direction.NORTH, Math.abs(y));
    }

    /**
     * moves robot by certain amounts of units in x and y directions
     * @param p magnitude of point to move
     */
    public void move(Point p) {
        move(p.x, p.y);
    }

    /**
     * turn to a direction regardless of where robot is currently heading to
     * @param target direction to turn
     */
    public void turn(Direction target) {
        Direction current = getDirection();
        if (current == target) return;
        if (current.right() == target) {
            turnRight();
        } else if (current.left() == target) {
            turnLeft();
        } else {
            turnAround();
        }
    }

    /**
     * goes to a certain point on the map
     * @param x x-coordinate of the goal point
     * @param y y-coordinate of the goal point
     */
    public void goTo(int x, int y) {
        move(x - getX(), y - getY());
    }

    /**
     * goes to a certain point on the map
     * @param p point to go to
     */
    public void goTo(Point p) {
        goTo(p.x, p.y);
    }
}
