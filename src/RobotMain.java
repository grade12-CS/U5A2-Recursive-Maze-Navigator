import becker.robots.*;

/**
 * completion date: 4/9/2025
 * title: Maze Navigator
 * description: finds a fastest path to pick up a treasure (Thing) using A* algorithm
 * @author Sarah Yoo
 */
public class RobotMain {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to MazeBotAutomated!");
        final int streets = 10;
        final int avenues = 10;
        City maze = new MazeCity(streets, avenues);
        MazeBot mB = new MazeBot(maze, 3,4, streets, avenues);
        //for (String arg : args) {
            //if (arg.equals("go home")) {
                //mB.shouldGoHome = true;
            //}
        //}
        mB.infiniteSearching();
    }
}