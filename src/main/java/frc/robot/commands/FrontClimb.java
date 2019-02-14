package frc.robot.commands;

import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class FrontClimb extends Command {
	
	boolean mDir;
	
	public FrontClimb(boolean motorDir) {	// Determine direction of the front climber
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}
	protected void execute() {
		if ((mDir == true)) {
			RobotMap.frontClimbMotor.set(1);	// Front climber up
		}
		else if ((mDir == false)) {
			RobotMap.frontClimbMotor.set(-1);	// Front climber down
		}
		else {
		RobotMap.frontClimbMotor.set(0);
		}
	}
	protected void end() {
		RobotMap.frontClimbMotor.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
