/**
 * 
 */
package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author USX27182
 *
 */
public class Autonomous extends Subsystem {

	/**
	 * 
	 */
	double circumferenceInInches = 25.875;
	int pulsesPerRotation = 1024;
	//int pulsesPerRotation = 315;
	double liftInPerSec = 6;    // 8 in/sec didn't lift high enough
	//public static RobotDrive drive;
	double distanceToTravel = 0;
	double startPosition = 0;
	double currentAngle = 0;
	double currentPosition = 0;
	double targetPulseCount = 0;
	double targetPosition = 0;
	double drivePower = 0;
	double AUTO_DRIVE_POWER = 0.5;

	
	public Autonomous() {
		// TODO Auto-generated constructor stub
	}

	
    protected boolean hasDrivenFarEnough(double startPos, double distance) {
		//currentPosition = ((RobotMap.lift.getSensorCollection().getQuadraturePosition() + RobotMap.climb.getSensorCollection().getQuadraturePosition()) / 2) ;
		currentPosition = ((RobotMap.encoderLRear.get() + RobotMap.encoderRRear.get()) / 2) ;
		targetPulseCount = (distance / circumferenceInInches) * pulsesPerRotation ;
		targetPosition = startPos + targetPulseCount;
		//System.out.println("Current Position: " + String.valueOf(currentPosition));
		//System.out.println("Target Position: " + String.valueOf(targetPulseCount));
		if (RobotState.isAutonomous() == true) {
			if (distance > 0) { // Driving FORWARD
				if (currentPosition >= targetPosition) {
					return true;
				}
				else {
					return false;
				}
			}
			else { // Driving REVERSE
				if (currentPosition <= targetPosition) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			return true;
		}
	}

   
    protected boolean strafeFarEnough(double startPos, double distance) {
    	//currentPosition = ((RobotMap.lift.getSensorCollection().getQuadraturePosition() + RobotMap.climb.getSensorCollection().getQuadraturePosition()) / 2) ;
    	currentPosition = ((Math.abs(RobotMap.encoderLRear.get()) + Math.abs(RobotMap.encoderRRear.get())) / 2);
		targetPulseCount = distance / circumferenceInInches * pulsesPerRotation *  1.34;		targetPosition = startPos + targetPulseCount;
		//System.out.println("Current Position: " + String.valueOf(currentPosition));
		//System.out.println("Target Position: " + String.valueOf(targetPulseCount));
		if (distance > 0) { // Driving RIGHT
			//currentPosition = ((Math.abs(RobotMap.lift.getSensorCollection().getQuadraturePosition() ) + Math.abs(RobotMap.climb.getSensorCollection().getQuadraturePosition() )) / 2);
			currentPosition = ((Math.abs(RobotMap.encoderLRear.get()) + Math.abs(RobotMap.encoderRRear.get())) / 2);
			if (currentPosition >= targetPosition) {
				return true;
			}
			else{
				return false;
			}
		}
		else { // Driving LEFT
			//currentPosition = -((Math.abs(RobotMap.lift.getSensorCollection().getQuadraturePosition() ) + Math.abs(RobotMap.climb.getSensorCollection().getQuadraturePosition() )) / 2);
			currentPosition = - ((Math.abs(RobotMap.encoderLRear.get()) + Math.abs(RobotMap.encoderRRear.get())) / 2);
			if (currentPosition <= targetPosition) {
				return true;
			}
			else {
				return false;
			}
		}
	}    

    protected boolean gyroTurn(double targetAngle) {
		RobotMap.rioGyro.reset();
		while ((RobotState.isAutonomous() == true) && (Math.abs(readGyro()) < Math.abs(targetAngle)) && (Math.abs(calcP(targetAngle)) > 0.25)) {
			Robot.d_drive.arcadeDrive(0, calcP(targetAngle));//(0, calcP(targetAngle));
		}
		stop();	
		return true;
	}
    
	protected boolean gyroDrive(double distance) {
		RobotMap.rioGyro.reset();
		RobotMap.encoderLRear.reset();
		RobotMap.encoderRRear.reset();
		startPosition = ((RobotMap.encoderLRear.get() + RobotMap.encoderRRear.get()) / 2) ;
		// double targetPosition = (distance / circumferenceInInches * pulsesPerRotation);
		while (hasDrivenFarEnough(startPosition, distance) == false) {
			//SmartDashboard.putNumber("Left Encoder Count", RobotMap.encoderLRear.get());
	    	//SmartDashboard.putNumber("Right Encoder Count", RobotMap.encoderRRear.get());
			double drift = readGyro() / 10;
			if (distance > 0) {
				Robot.d_drive.arcadeDrive(AUTO_DRIVE_POWER, -drift);  // FORWARD
			}
			
			else {
				Robot.d_drive.arcadeDrive(-AUTO_DRIVE_POWER, -drift);  // REVERSE
			}
			
			//System.out.println("Gyro Heading: " + drift);
		}
		
		stop();
		return true;
	}
	
	protected boolean strafeDrive(double distance) {
		RobotMap.rioGyro.reset();
		RobotMap.encoderLRear.reset();
		RobotMap.encoderRRear.reset();
		//startPosition = ((RobotMap.lift.getSensorCollection().getQuadraturePosition() + RobotMap.climb.getSensorCollection().getQuadraturePosition()) / 2) ;
		startPosition = ((RobotMap.encoderLRear.get() + RobotMap.encoderRRear.get()) / 2);
		while (strafeFarEnough(startPosition, distance) == false) {
	    	//SmartDashboard.putNumber("Left Encoder Count", RobotMap.encoderLRear.get());
	    	//SmartDashboard.putNumber("Right Encoder Count", RobotMap.encoderRRear.get());
			double drift = readGyro() / 10;
			if (distance > 0) {
				Robot.d_drive.arcadeDrive(0.65, -drift);  // RIGHT
			}
			
			else {
				Robot.d_drive.arcadeDrive(-0.65, -drift);  // LEFT
			}
			
			//System.out.println("Gyro Heading: " + drift);
		}
		
		stop();
		return true;
	}
	
		//Terms For Pneumatics
	public void openClaw() {
		RobotMap.solenoid2.set(false);
		RobotMap.solenoid3.set(true);
	}
	
	public void closeClaw() {
		RobotMap.solenoid2.set(true);
		RobotMap.solenoid2.set(true);
		RobotMap.solenoid3.set(false);
	}
	
	public void rotateIn() {
		RobotMap.solenoid0.set(true);
		RobotMap.solenoid1.set(false);
	}
	
	public void rotateOut() {
		RobotMap.solenoid0.set(false);
		RobotMap.solenoid1.set(true);
	}
	
	public void driveAndLift(int travel, int height) {
		boolean isDone = false;
		boolean highEnough = false;
		boolean farEnough = false;
		
		//set distance to travel and lift
		//travel = 305;
		//height = 52;
		
		//set initial encoder position and destination count
		double currentPosition = ((RobotMap.encoderLRear.get())+ (RobotMap.encoderRRear.get()) /2);
		double targetDrvPosition = currentPosition + (travel / circumferenceInInches * pulsesPerRotation);
		double liftTime = (height/liftInPerSec) + Timer.getFPGATimestamp();
		//turn on drive motors and lift motor
		Robot.d_drive.arcadeDrive(AUTO_DRIVE_POWER, 0);
		RobotMap.lift.set(.75);
		
		while (isDone == false) {
			currentPosition = (RobotMap.encoderLRear.get() + RobotMap.encoderRRear.get()) /2;
			if (currentPosition >= targetDrvPosition) {
				farEnough = true;
				Robot.d_drive.arcadeDrive(0, 0);
			} 
			else {
				Robot.d_drive.arcadeDrive(AUTO_DRIVE_POWER, 0);
			}
			// check lift far enough
			if (Timer.getFPGATimestamp() >= liftTime) {
				highEnough = true;
				RobotMap.lift.set(0);
			}
			isDone = highEnough && farEnough ? true : false;
		}
		
	}
		// Terms for Lift
		// Without encoder on lift assembly, measurement is based on time
		// To measure based on time, a given rate must be known - inches traveled per second
		// Set the rate in liftInPerSec constant at top of class
	public void liftUp(double drumRotations) {
		double startPos = RobotMap.BoxSwitch.get();
		double endPos = startPos + (drumRotations) * 15360;
		// Adding timeout 
		double runTime = Timer.getFPGATimestamp() + 4; 
			while ((RobotMap.BoxSwitch.get() < endPos) && (Timer.getFPGATimestamp() < runTime)) {
				RobotMap.lift.set(1.0);
				Timer.delay(0.050);
			}
			
			RobotMap.lift.set(0);
	}
	
	public void liftDown(double drumRotations) {
	double startPos = RobotMap.BoxSwitch.get();
		double endPos = startPos - (Math.abs(drumRotations) * 15360);
			while (RobotMap.BoxSwitch.get() > endPos) {
				RobotMap.lift.set(-1.0);
				Timer.delay(0.050);
			}
			
			RobotMap.lift.set(0);
	}
	
		//Drive Directions
	public void driveForward(double distance) {
		gyroDrive(distance);
	}
	
	public void driveBackward(double distance) {
		gyroDrive(-Math.abs(distance));
	}
	
	public void strafeLeft(double distance) {
		strafeDrive(-distance);
	}
	
	public void strafeRight(double distance) {
		strafeDrive(distance);
	}
	
	public void turnLeft(double degrees) {
		gyroTurn(-degrees);
	}
	
	public void turnRight(double degrees) {
		gyroTurn(degrees);
	}
	
	//--------------------------------------

	protected double readGyro() {
		double angle = RobotMap.rioGyro.getAngle();
		return angle;
	}
	
	protected double calcP(double tAngle) {
		double p = 1 * ((1-(Math.abs(readGyro()) / Math.abs(tAngle))) - 0.05);	
		if (tAngle > 0) {
			return p;
		}
		
		else {
			return (p * -1);
		}
		
	}
	
	public void stop() {

		Robot.d_drive.arcadeDrive(-.1, 0);
    	//taskDone = true;
    	
    }


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
