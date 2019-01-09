package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MiddleSwitchNoDropAuto extends Command {
	Autonomous auto = new Autonomous();
	boolean taskDone = false;
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();

	public MiddleSwitchNoDropAuto() {
		
	}
	
	protected void execute() {
		System.out.println("Starting Test Drive");
		taskDone = false;
		taskDone = false;
		auto.rotateOut();
		Timer.delay(5);
		auto.driveForward(12);
		Timer.delay(.5);
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		if (fmscode.length() > 0) {
			if(fmscode.charAt(0) == 'L') {
				//left side code
				auto.strafeRight(70);
			}
		
			else {
				//right side code
				auto.strafeLeft(70);
			}
		}
		else {
			auto.strafeLeft(70);
		}
	
		Timer.delay(.5);
		auto.driveForward(60);
		Timer.delay(.5);
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
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
	

	@Override
	protected boolean isFinished() {
		System.out.println("Test Drive isFinished");
		return taskDone;
		
	}

}
