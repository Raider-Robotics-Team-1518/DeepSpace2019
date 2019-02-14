package frc.robot.commands;

import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class AirActuators extends InstantCommand {

	boolean actNum;

	public AirActuators(boolean actNum) {
		// TODO Auto-generated constructor stub
		
	}
	
	protected void execute() {

		if(Pneumatics.one.get() == true) {	// If the claw is open
			if(Pneumatics.three.get() == false) {	// If the hatch grabber is extended
				Pneumatics.three.set(true);	// Retract hatch grabber
				Pneumatics.four.set(false);
			}
			Pneumatics.one.set(false);	// Close hatch catcher
			Pneumatics.two.set(true);
		}
		else {	// Claw is open
			if(Pneumatics.three.get() == true) {	// If hatch grabber is retracted
				Pneumatics.three.set(false);	// Retract hatch grabber
				Pneumatics.four.set(true);
			}
			Pneumatics.one.set(true);	// Open Claw
			Pneumatics.two.set(false);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected void end() {
		
	}
	protected void interrupted(){

	}
}
