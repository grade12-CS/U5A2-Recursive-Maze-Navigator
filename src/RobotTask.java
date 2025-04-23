import becker.robots.*;

/**
 *RobotTask class
 
 *@author Sarah Yoo 
 *@version 1.0 
 */

public class RobotTask
{
	/**
	 *run method
	 *return void
	 */
	public void run ()
	{
		//construct city
        final int streets = 10;
        final int avenues = 10;
		MazeCity maze = new MazeCity(streets, avenues); //new random maze

		//new MazeBot reference starting in middle of maze 
		MazeBot mB = new MazeBot (maze, 4,4, streets, avenues);
		mB.infiniteSearching();
	}
}
