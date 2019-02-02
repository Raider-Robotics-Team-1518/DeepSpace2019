/**
 * 
 */
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * HRLV-MaxSonar EZ sensors attached
 * Left = Analog Input 0
 * Rights = Analog Input 1
 *
 * Need to work on subsystem to get the values from the range finders, average them to 
 * reduce the chatter.
 * 
 * Average between the two sensors for relational distance.
 * 
 * Calculate the distances for each sensor, compare, and calculate the angle to the forward facing wall.
 * 
 */
public class RangeFinders extends Subsystem {


    public RangeFinders() {

    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
