import becker.robots.*;

/**
 *RobotTask class
 
 *@author Mr. Gmach
 *@version 2025
 */

public class RobotTask
{
	/**
	 *run method

	 *@param none
	 *return void
	 */
	public void run ()
	{
		//construct city
		MazeCity mc = new MazeCity (10,10,1,1); //new random maze

		//new MazeBot reference starting in middle of maze facing SOUTH
		MazeBot mB = new MazeBot (mc, 4,4, Direction.SOUTH, 0);
		
		mB.goHome(); //instructs mB to go back to the origin (0,0)
	}
}
