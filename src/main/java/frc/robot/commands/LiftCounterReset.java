package frc.robot.commands;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class LiftCounterReset  extends InstantCommand{
	public static boolean taskDone = false;

	public LiftCounterReset() {
	}

	
	protected void execute() {
		RobotMap.BoxSwitch.reset();
		taskDone = true;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return taskDone;
	}

}
