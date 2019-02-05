/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
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
  public static JoystickButton soleButton1;
  public static JoystickButton soleButton2;
  public static JoystickButton armPivUp;
  public static JoystickButton armPivDown;
  public static JoystickButton armLeft;
  public static JoystickButton armRight;
  public static JoystickButton armUp;
  public static JoystickButton armDown;
  public static JoystickButton eatBall;
  public static JoystickButton vomitBall;
  public static JoystickButton turbo;
  public static XboxController gp1;
  public static JoystickButton gp1ButtonA;
  public static JoystickButton gp1ButtonY;
  public static JoystickButton reset;

  public OI() {

    mainstick = new Joystick(0);
    armPivUp = new JoystickButton(mainstick, 5);
    armPivUp.whileHeld(new ArmPivot(true));	
    armPivDown = new JoystickButton(mainstick, 3);
    armPivDown.whileHeld(new ArmPivot(false));
    armLeft = new JoystickButton(mainstick, 6);
    armLeft.whileHeld(new HzLinear(true));
    armRight = new JoystickButton(mainstick, 4);
    armRight.whileHeld(new HzLinear(false));
    armUp = new JoystickButton(mainstick, 10);
    armUp.whileHeld(new VtLifter(true));
    armDown = new JoystickButton(mainstick, 12);
    armDown.whileHeld(new VtLifter(false));
    eatBall = new JoystickButton(mainstick, 7);
    eatBall.whileHeld(new WheelMan(true));
    vomitBall = new JoystickButton(mainstick, 8);
    vomitBall.whileHeld(new WheelMan(false));
    turbo = new JoystickButton(mainstick, 2);
    soleButton1 = new JoystickButton(mainstick, 9);
    soleButton1.whenPressed(new AirActuators(1));
    soleButton2 = new JoystickButton(mainstick, 11);
    soleButton2.whenPressed(new AirActuators(2));
/*    gp1 = new XboxController(1);
    gp1ButtonA = new JoystickButton(gp1, 1);
    gp1ButtonY = new JoystickButton(gp1, 4);
    gp1ButtonA.whileHeld(new HzLinear(false));
    gp1ButtonY.whileHeld(new HzLinear(true));
    */
  }
  
  public final void init() {
    SmartDashboard.putData("Autonomous Command", new MiddleSwitchNoDropAuto());
      Robot.feedSpeed = mainstick.getThrottle();
  }

}
