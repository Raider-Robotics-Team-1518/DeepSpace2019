package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class BoxLifter extends Command {
	boolean mDir;

	public BoxLifter(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			RobotMap.lift.set(1);
		}
		else if (mDir == false) {
			RobotMap.lift.set(-1);
			}
		else {
		RobotMap.lift.set(0);
		}
	}

	protected void end() {
		RobotMap.lift.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
