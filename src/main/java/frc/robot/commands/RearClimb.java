package frc.robot.commands;

import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class RearClimb extends Command {
	
	//WPI_TalonSRX climbMotor = RobotMap.climb;
	boolean mDir;
	
	public RearClimb(boolean motorDir) {	// Determine direction of the rear climber
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if ((mDir == true)) {
			RobotMap.rearClimbMotor.set(1);		// Rear climber up
		}

		else if ((mDir == false)) {
			RobotMap.rearClimbMotor.set(-1);	// Rear climber down
		}
		else {
		RobotMap.rearClimbMotor.set(0);
		}
	}
	
	protected void end() {
		RobotMap.rearClimbMotor.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
