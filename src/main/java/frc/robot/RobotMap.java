/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.subsystems.*;


public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  		//Drive Train
	public static WPI_VictorSPX driveTrainLF = new WPI_VictorSPX(1);
	public static WPI_VictorSPX driveTrainRF = new WPI_VictorSPX(2);
	public static WPI_VictorSPX driveTrainRR = new WPI_VictorSPX(3); 
	public static WPI_VictorSPX driveTrainLR = new WPI_VictorSPX(4);
	
		//Aux Controllers
	public static WPI_TalonSRX armVT = new WPI_TalonSRX(5);
	public static WPI_TalonSRX armHz = new WPI_TalonSRX(6);
	public static WPI_TalonSRX armPivot = new WPI_TalonSRX(7);
	public static WPI_TalonSRX frontClimbMotor = new WPI_TalonSRX(8);
	public static WPI_TalonSRX rearClimbMotor = new WPI_TalonSRX(9);
	public static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(10);
	public static VictorSP rearClimbWheel1 = new VictorSP(0);
	public static VictorSP rearClimbWheel2 = new VictorSP(1);
	
		//Speed Controller Groups for Tank Drive
	public static SpeedControllerGroup left_drive = new SpeedControllerGroup(driveTrainLF, driveTrainLR);
	public static SpeedControllerGroup right_drive = new SpeedControllerGroup(driveTrainRF, driveTrainRR);
	public static SpeedControllerGroup climb_drive = new SpeedControllerGroup(frontClimbMotor, rearClimbMotor);			
	
		//Solenoids
	public static Solenoid solenoid0 = new Solenoid(1); //put all positions back where they belong
	public static Solenoid solenoid1 = new Solenoid(2);
	public static Solenoid solenoid2 = new Solenoid(3);
	public static Solenoid solenoid3 = new Solenoid(4);
	
		//Encoders
	public static Encoder encoderLRear = new Encoder(0, 1, true, Encoder.EncodingType.k1X);
	public static Encoder encoderRRear = new Encoder(2, 3, false, Encoder.EncodingType.k1X);
	
		//Digital Outputs
	public static DigitalOutput dio8 = new DigitalOutput(8);
	public static DigitalOutput dio9 = new DigitalOutput(9); 
	
		//Other
	public static Gyro rioGyro = new ADXRS450_Gyro();
	public static BuiltInAccelerometer rioAccel = new BuiltInAccelerometer();
	public static Compressor comp0 = new Compressor(0);
	public static Rangefinder leftHRLV = new Rangefinder(0);
	public static Rangefinder rightHRLV = new Rangefinder(1);
	public static Rangefinder bottomHRLV = new Rangefinder(2);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public final void init() {
	}
}
