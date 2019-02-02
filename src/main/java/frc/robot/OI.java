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
  public static JoystickButton soleButton0;
  public static JoystickButton soleButton1;
  public static JoystickButton soleButton2;
  public static JoystickButton liftUp;
  public static JoystickButton liftDown;
  public static JoystickButton liftUp2;
  public static JoystickButton liftDown2;
  //public static JoystickButton fullLiftDown;
  public static JoystickButton climbUp;
  public static JoystickButton climbDown;
  public static JoystickButton turbo;
  public static XboxController gp1;
  public static JoystickButton gp1ButtonA;
  public static JoystickButton gp1ButtonY;
  public static JoystickButton reset;

  public OI() {

    mainstick = new Joystick(0);
    liftUp = new JoystickButton(mainstick, 5);
    liftUp.whileHeld(new ArmPivot(true));	
    liftDown = new JoystickButton(mainstick, 3);
    liftDown.whileHeld(new ArmPivot(false));
   /* liftUp2 = new JoystickButton(mainstick, 9);
    liftUp2.whileHeld(new BoxLifter(true));	
    liftDown2 = new JoystickButton(mainstick, 11);
    liftDown2.whileHeld(new BoxLifter(false));*/
    //fullLiftDown = new JoystickButton(mainstick, 10);
    //fullLiftDown.whenPressed(new BoxLifter(false));
    climbUp = new JoystickButton(mainstick, 6);
    climbUp.whileHeld(new HzLinear(true));
    climbDown = new JoystickButton(mainstick, 4);
    climbDown.whileHeld(new HzLinear(false));
    turbo = new JoystickButton(mainstick, 2);
    soleButton0 = new JoystickButton(mainstick, 7);
    soleButton0.whenPressed(new AirActuators(0));
    soleButton1 = new JoystickButton(mainstick, 9);
    soleButton1.whenPressed(new AirActuators(1));
    soleButton2 = new JoystickButton(mainstick, 11);
    soleButton2.whenPressed(new AirActuators(2));
    gp1 = new XboxController(1);
    gp1ButtonA = new JoystickButton(gp1, 1);
    gp1ButtonY = new JoystickButton(gp1, 4);
    gp1ButtonA.whileHeld(new HzLinear(false));
    gp1ButtonY.whileHeld(new HzLinear(true));
    
  }
  
  public final void init() {
    SmartDashboard.putData("Autonomous Command", new MiddleSwitchNoDropAuto());
      Robot.feedSpeed = mainstick.getThrottle();
  }

}
