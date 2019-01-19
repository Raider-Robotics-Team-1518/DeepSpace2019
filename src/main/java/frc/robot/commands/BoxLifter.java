package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class BoxLifter extends Command {
	boolean mDir;
	boolean switchState = false;
	
	double maxheight = 18 * 16;			// Maximum height for box lifter
	int minheight = -64;			// Minimum height for box lifter
	int ticksPerRotation = 1024;		// # of ticks of the encoder per inch	 Total ticks = 271727.0

	double maxcount = (maxheight * ticksPerRotation);
	double mincount = (minheight * ticksPerRotation);
	public BoxLifter(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (Robot.isTestBot == true) {
			if ((mDir == true) && (RobotMap.BoxSwitch.get() < maxcount)) {
				RobotMap.testLift.set(1);
			}
			else if ((mDir == false)) {  // && (RobotMap.BoxSwitch.get() > mincount)) {
				RobotMap.testLift.set(-1);
			}
			else {
			RobotMap.testLift.set(0);
			}
		}
		else {
			if ((mDir == true)  && (RobotMap.BoxSwitch.get() <= maxcount)) {
				RobotMap.lift.set(1);
			}
			else if ((mDir == false) && (RobotMap.BoxSwitch.get() > mincount)) {
				RobotMap.lift.set(-1);
				}
			else {
			RobotMap.lift.set(0);
			}
		}
	}

	protected void end() {
		RobotMap.lift.set(0);
		RobotMap.testLift.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
