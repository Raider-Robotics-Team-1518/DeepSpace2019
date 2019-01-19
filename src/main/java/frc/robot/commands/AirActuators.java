package frc.robot.commands;

import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class AirActuators extends InstantCommand{
	boolean liftFront = true;	//initial state for front lift pnumatics
	boolean liftRear = true;	//initial state for rear lift pnumatics
	boolean gearGrabber = true; //initial state for gear grabber open
	int actNumber;

	public AirActuators(int actNum) {
		// TODO Auto-generated constructor stub
		actNumber = actNum;
	}
	
	protected void execute() {
		
		switch (actNumber) {
		
		case 0:
			if(liftFront == true) {
				Pneumatics.one.set(false);
				Pneumatics.two.set(true);
				liftFront = false;
			}
			else {
				Pneumatics.one.set(true);
				Pneumatics.two.set(false);
				liftFront = true;
			}
			break;
		case 1:
			if(liftRear == true){
				Pneumatics.three.set(false);
				Pneumatics.four.set(true);

				liftRear = false;
			}
			else {
				Pneumatics.three.set(true);
				Pneumatics.four.set(false);
				liftRear = true;
			}
			break;
		case 2:
			if(gearGrabber == true) {
				Pneumatics.five.set(true);
				Pneumatics.six.set(false);
				gearGrabber = false;
			}
			else {
				Pneumatics.five.set(false);
				Pneumatics.six.set(true);
				gearGrabber = true;
			}
			break;
		default:
			break;
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
