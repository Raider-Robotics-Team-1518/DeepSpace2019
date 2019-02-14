package frc.robot.commands;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbWheels extends Command {
	boolean mDir;

	public ClimbWheels(boolean motorDir) {	// Determine direction of climb wheels
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			RobotMap.rearClimbWheel1.set(.5);	// Drive wheels forward
			RobotMap.rearClimbWheel2.set(.5);
		}
		else if (mDir == false) {
			RobotMap.rearClimbWheel1.set(-.5);	// Drive wheels backward
			RobotMap.rearClimbWheel2.set(-.5);
		}
		else {
			RobotMap.rearClimbWheel1.set(0);
			RobotMap.rearClimbWheel2.set(0);
		}	
	}

	protected void end() {
		RobotMap.rearClimbWheel1.set(0);
		RobotMap.rearClimbWheel2.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}