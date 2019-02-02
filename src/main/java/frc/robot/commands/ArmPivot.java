package frc.robot.commands;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ArmPivot extends Command {
	boolean mDir;

	public ArmPivot(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			RobotMap.armPivot.set(1);
		}
		else if (mDir == false) {
			RobotMap.armPivot.set(-1);
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