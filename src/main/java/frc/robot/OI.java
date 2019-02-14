/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * Mapping buttons - 
 * 	Shooting - Right Trigger
 * 	Intake - Left Trigger
 *  Climb - Left Thumb Low (2)
 *  Unjam - Right Thumb Low (2)
 *  Servos - Right Thumb High (3)
 *  Reverse Controls - Left Thumb High (3)
 */

 public class OI {
  public static Joystick mainstick;
  public static XboxController gp1;
  public static JoystickButton ballClaw;
  public static JoystickButton hatchClaw;
  public static JoystickButton armPivUp;
  public static JoystickButton armPivDown;
  public static JoystickButton armLeft0;
  public static JoystickButton armRight0;
  public static JoystickButton armLeft1;
  public static JoystickButton armRight1;
  public static JoystickButton armUp;
  public static JoystickButton armDown;
  public static JoystickButton eatBall;
  public static JoystickButton vomitBall;
  public static JoystickButton turbo;
  public static JoystickButton frontClimbWheelsUp;
  public static JoystickButton frontClimbWheelsDown;
  public static JoystickButton rearClimbWheelsUp;
  public static JoystickButton rearClimbWheelsDown;
  public static JoystickButton climbWheelsForward;
  public static JoystickButton climbWheelsBackward;
  
  public OI() {

      // Main Joystick               Assigning buttons to the joystick
    mainstick = new Joystick(0);
    turbo = new JoystickButton(mainstick, 2);
    armPivUp = new JoystickButton(mainstick, 6);
    armPivUp.whileHeld(new ArmPivot(true));	
    armPivDown = new JoystickButton(mainstick, 4);
    armPivDown.whileHeld(new ArmPivot(false));
    armUp = new JoystickButton(mainstick, 5);
    armUp.whileHeld(new VtLifter(true));
    armDown = new JoystickButton(mainstick, 3);
    armDown.whileHeld(new VtLifter(false));
    eatBall = new JoystickButton(mainstick, 8);
    eatBall.whileHeld(new BallMan(true));
    vomitBall = new JoystickButton(mainstick, 7);
    vomitBall.whileHeld(new BallMan(false));
    ballClaw = new JoystickButton(mainstick, 1);
    ballClaw.whenPressed(new AirActuators(true));
    armLeft1 = new JoystickButton(mainstick, 9);
    armLeft1.whileHeld(new HzLinear(true));
    armRight1 = new JoystickButton(mainstick, 10);
    armRight1.whileHeld(new HzLinear(false));

      // Logitech Controller         Assigning buttons to the gamepad
    gp1 = new XboxController(1);
    frontClimbWheelsUp = new JoystickButton(gp1, 5);
    frontClimbWheelsUp.whileHeld(new FrontClimb(true));
    frontClimbWheelsDown = new JoystickButton(gp1, 7);
    frontClimbWheelsDown.whileHeld(new FrontClimb(false));
    rearClimbWheelsUp = new JoystickButton(gp1, 6);
    rearClimbWheelsUp.whileHeld(new RearClimb(true));
    rearClimbWheelsDown = new JoystickButton(gp1, 8);
    rearClimbWheelsDown.whileHeld(new RearClimb(false));
    climbWheelsForward = new JoystickButton(gp1, 3);
    climbWheelsForward.whileHeld(new ClimbWheels(true));
    climbWheelsBackward = new JoystickButton(gp1, 2);
    climbWheelsBackward.whileHeld(new ClimbWheels(false));
    armLeft0 = new JoystickButton(gp1, 9);
    armLeft0.whileHeld(new HzLinear(true));
    armRight0 = new JoystickButton(gp1, 10);
    armRight0.whileHeld(new HzLinear(false));
  }
  public final void init() {
    SmartDashboard.putData("Autonomous Command", new MiddleSwitchNoDropAuto());
    Robot.feedSpeed = mainstick.getThrottle();
  }
}
