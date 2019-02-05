package frc.robot.commands;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VtLifter extends Command {
	boolean mDir;

	public VtLifter(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			RobotMap.armVT.set(1);
		}
		else if (mDir == false) {
			RobotMap.armVT.set(-1);
			}
		else {
		RobotMap.armVT.set(0);
		}
	}

	protected void end() {
		RobotMap.armVT.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}