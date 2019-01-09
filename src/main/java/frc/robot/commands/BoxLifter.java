package frc.robot.commands;

import frc.robot.Robot;

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
			if ((mDir == true) && (Robot.rm.BoxSwitch.get() < maxcount)) {
				Robot.rm.testLift.set(1);
			}
			else if ((mDir == false)) {  // && (Robot.rm.BoxSwitch.get() > mincount)) {
				Robot.rm.testLift.set(-1);
			}
			else {
			Robot.rm.testLift.set(0);
			}
		}
		else {
			if ((mDir == true)  && (Robot.rm.BoxSwitch.get() <= maxcount)) {
				Robot.rm.lift.set(1);
			}
			else if ((mDir == false) && (Robot.rm.BoxSwitch.get() > mincount)) {
				Robot.rm.lift.set(-1);
				}
			else {
			Robot.rm.lift.set(0);
			}
		}
	}

	protected void end() {
		Robot.rm.lift.set(0);
		Robot.rm.testLift.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
