package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CrossLineOnlyAuto extends Command {
	Autonomous auto = new Autonomous();
	boolean taskDone = false;
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();

	public CrossLineOnlyAuto() {
		
	}
	
	protected void execute() {
		System.out.println("Starting Test Drive");
		taskDone = false;
		taskDone = false;
		Timer.delay(2.5);
		auto.rotateOut();
		auto.driveForward(48);
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		Timer.delay(1.5);
		end();
		
	}
	
	protected void end(){
		System.out.println("Test Drive Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Test Drive Interrupted");
	}

    public void stop() {
		System.out.println("Test Drive Stopped");
    	Robot.d_drive.arcadeDrive(0, 0);
    	taskDone = true;
    	
    }
    
	

	@Override
	protected boolean isFinished() {
		System.out.println("Test Drive isFinished");
		return taskDone;
		
	}

}
