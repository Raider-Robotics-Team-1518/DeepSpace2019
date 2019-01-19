package frc.robot.commands;

import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class Climber extends Command {
	
	//WPI_TalonSRX climbMotor = RobotMap.climb;
	boolean mDir;
	
	public Climber(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if ((mDir == true)) {
			RobotMap.climb.set(1);
		}

		else if ((mDir == false)) {
			RobotMap.climb.set(-1);
		}
		else {
		RobotMap.climb.set(0);
		}
	}
	protected void end() {
		RobotMap.climb.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
