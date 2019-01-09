package frc.robot.commands;

import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class AirActuators extends InstantCommand{
	boolean actuator0 = false;	//initial state for jaws up
	boolean actuator1 = false;  //initial state for open jaw
	boolean actuator2 = false;  //initial state for buddy bar
	int actNumber;

	public AirActuators(int actNum) {
		// TODO Auto-generated constructor stub
		actNumber = actNum;
	}
	
	protected void execute() {
		
		switch (actNumber) {
		
		case 0:
			if(actuator0 == true) {
				Pneumatics.one.set(true);
				Pneumatics.two.set(false);
				actuator0 = false;
			}
			else {
				Pneumatics.one.set(false);
				Pneumatics.two.set(true);
				actuator0 = true;
			}
			break;
		case 1:
			if(actuator1 == true){
				Pneumatics.three.set(false);
				Pneumatics.four.set(true);
				actuator1 = false;
			}
			else {
				Pneumatics.three.set(true);
				Pneumatics.four.set(false);
				actuator1 = true;
			}
			break;
		case 2:
			if(actuator2 == true) {
				Pneumatics.five.set(true);
				Pneumatics.six.set(false);
				actuator2 = false;
			}
			else {
				Pneumatics.five.set(false);
				Pneumatics.six.set(true);
				actuator2 = true;
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
