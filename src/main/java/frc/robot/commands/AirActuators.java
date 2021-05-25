package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class AirActuators extends InstantCommand {

	boolean actNum;

	public AirActuators(boolean actNum) {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void initialize() {
    System.out.println("I'm Dying Dave!!");
/*	if((Robot.clawOpen == true) && (Robot.grabbing == true) && (Robot.hasHatch == false)) {
//		if((Pneumatics.one.get() == true) && (Pneumatics.three.get() == true) && (Robot.grabbing == false)) {	// close claw and extend fingers
		System.out.println("Close Claw & Extend Fingers");
		Pneumatics.one.set(false); //Close claw
		Pneumatics.two.set(true);
		Pneumatics.three.set(false); //Extend fingers
		Pneumatics.four.set(true);
		Robot.clawOpen = false;
		Robot.grabbing = false;
		Robot.hasHatch = true;
	}
	else {
		if((Robot.clawOpen == false) && (Robot.grabbing == false) && (Robot.hasHatch == true)) {
//		else if((Pneumatics.one.get() == false) && (Pneumatics.three.get() == false) && (Robot.grabbing == false)) { //claw closed and fingers extended
			System.out.println("Grab Hatch");
			Pneumatics.one.set(true); //Open claw
			Pneumatics.two.set(false);
			Timer.delay(1);
			Pneumatics.three.set(true); //Pull Fingers
			Pneumatics.four.set(false);
			Robot.clawOpen = true;
			Robot.grabbing = true;
			Robot.hasHatch = true;
		}
		else {
			if((Robot.clawOpen == true) && (Robot.grabbing == true) && (Robot.hasHatch == true)) {
//		else if((Pneumatics.one.get() == true) && (Pneumatics.three.get() == true) && (Robot.grabbing == true)) {	// If claw is closed, extend the fingers
				System.out.println("Release Hatch");
				Pneumatics.three.set(false);  //Extend fingers
				Pneumatics.four.set(true);
				Timer.delay(1);
				Pneumatics.one.set(false);  //Close claw
				Pneumatics.two.set(true);
				Robot.clawOpen = false;
				Robot.grabbing = false;
				Robot.hasHatch = false;
		  	}
			else { 
				if((Robot.clawOpen == false) && (Robot.grabbing == false) && (Robot.hasHatch == false)) {
//		else if((Pneumatics.one.get() == false) && (Pneumatics.three.get() == false) && (Robot.grabbing == true)) {	// If hatch grabber is retracted
					System.out.println("Return to Start");
					Pneumatics.three.set(true);	// Pull fingers
					Pneumatics.four.set(false);
					Pneumatics.one.set(true);	// Open Claw
					Pneumatics.two.set(false);
					Robot.clawOpen = true;
					Robot.grabbing = true;
					Robot.hasHatch = false;
			  	}
		}
	}
	} */
	}
}
