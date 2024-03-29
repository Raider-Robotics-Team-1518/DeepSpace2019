package frc.robot.commands;

import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class HzLinear extends Command {

	boolean mDir;
	
	public HzLinear(boolean motorDir) {	// Determine direction of horizontal arm
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			RobotMap.armHz.set(1);	// Arm moves to the left
		}

		else if (mDir == false) {
			RobotMap.armHz.set(-1);	// Arm moves to the right
		}
		else {
		RobotMap.armHz.set(0);
		}
	}
	protected void end() {
		RobotMap.armHz.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
