package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RightScaleAuto extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public RightScaleAuto() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 6");
		System.out.println("FMS code " + fmscode);
		taskDone = false;
		auto.rotateOut();
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();	
		if (fmscode.charAt(1) == 'R') {
			System.out.println("FMS code " + fmscode.charAt(1));
			auto.driveAndLift(275,52);
			Timer.delay(.5);
			auto.turnLeft(90);
			Timer.delay(1);
			auto.driveForward(8);
			Timer.delay(1);
			auto.openClaw();
		}
		else {
			
			if (fmscode.charAt(0) == 'R') {
				System.out.println("FMS code " + fmscode.charAt(0));
				auto.driveForward(120);
				Timer.delay(1);
				auto.turnLeft(90);
				Timer.delay(1);
				auto.driveForward(15);
				Timer.delay(1);
				auto.openClaw();
				Timer.delay(.25);
				auto.rotateIn();
				auto.driveBackward(12);
				Timer.delay(1);
			}
			else {
				auto.driveForward(100);
			}
			/*// Steps for cross field scale
			auto.driveForward(204);
			Timer.delay(1);
			auto.turnLeft(90);
			Timer.delay(1);
			auto.driveForward(252);
			Timer.delay(1);
			auto.turnRight(90);
			Timer.delay(1);
			auto.driveForward(60);
			Timer.delay(1);
			auto.turnRight(90);
			Timer.delay(1);
			auto.liftUp(12.9);
			Timer.delay(1);
			auto.driveForward(18);
			auto.openClaw();   */
		}
	
		end();
	}
	protected void end(){
		System.out.println("Auto Mode 6 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 6 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 6 Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
    @Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 6 isFinished");
		return taskDone;
	}

}
