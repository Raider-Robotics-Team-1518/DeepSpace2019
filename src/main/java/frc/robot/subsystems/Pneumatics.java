package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem{
	public static final Solenoid one = RobotMap.solenoid0;
	public static final Solenoid two = RobotMap.solenoid1;
	public static final Solenoid three = RobotMap.solenoid2;
	public static final Solenoid four = RobotMap.solenoid3;
	public static final Solenoid five = RobotMap.solenoid4;
	public static final Solenoid six = RobotMap.solenoid5;
	
	public Pneumatics() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		one.set(true);
		two.set(false);
		three.set(true);
		four.set(false);
		five.set(true);
		six.set(false);
	}
}
