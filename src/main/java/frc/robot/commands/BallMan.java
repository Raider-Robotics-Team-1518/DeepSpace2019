package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.Pneumatics;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class BallMan extends Command {
	
	boolean mDir;
	
	public BallMan(boolean motorDir) {	//Determines which direction ball shooter moves
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		Pneumatics.one.set(true);	// Open claw
		Pneumatics.two.set(false);
		Pneumatics.three.set(true);	// Retract hatch grabber
		Pneumatics.four.set(false);
		if (mDir == true) {
			RobotMap.intakeMotor.set(1);	// Intake Ball
		}

		else if (mDir == false) {
			RobotMap.intakeMotor.set(-1);	// Release Ball
		}
		else {
		RobotMap.intakeMotor.set(0);
		}
	}
	protected void end() {
		RobotMap.intakeMotor.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}