package frc.robot.commands;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ArmPivot extends Command {
	boolean mDir;

	public ArmPivot(boolean motorDir) {	// Determine which direction arm is going
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			RobotMap.armPivot.set(1); // Arm pivots out
		}
		else if (mDir == false) {
			RobotMap.armPivot.set(-1);	// Arm pivots in
			}
		else {
		RobotMap.armPivot.set(0);
		}
	}

	protected void end() {
		RobotMap.armPivot.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}