package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MiddleSwitchAuto extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public MiddleSwitchAuto() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 2");
		taskDone = false;
		auto.rotateOut();
		Timer.delay(5);
		auto.driveForward(12);
		Timer.delay(.5);
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		if (fmscode.length() > 0) {
			if(fmscode.charAt(0) == 'L') {
				//left side code
				auto.strafeLeft(65);
				Timer.delay(.5);
				auto.driveForward(57);
				Timer.delay(1.5);
				auto.openClaw();
				Timer.delay(1);
				auto.driveForward(6);
				end();
			}
		
			else {
				//right side code
				auto.strafeRight(60);
				Timer.delay(.5);
				auto.driveForward(57);
				Timer.delay(1.5);
				auto.openClaw();
				Timer.delay(1);
				auto.driveForward(6);
				end();
			}
		}
		else {
			// no FMS code
			auto.strafeRight(48);
			Timer.delay(.5);
			auto.driveForward(60);
			
			end();
		}
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 2 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 2 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 2 Stopped");
    	Robot.d_drive.arcadeDrive(0, 0);
    	taskDone = true;
    	
    }
    

	@Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 2 isFinished");
		return taskDone;
	}

}
