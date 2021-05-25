/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Command autoMode;

  public static OI oi = new OI();
  public static RobotMap rm = new RobotMap();
  public static Pneumatics pn;
  
  //Hardware
  public static DifferentialDrive d_drive;
  public static UsbCamera cam0;
  public static AxisCamera cam1;

  //SubSystems
  public static boolean turbo;

  //Setup
  public static boolean isTestBot = false;			//<------------------- Determine Drive Train Here
  public static double feedSpeed;
  public static boolean isReversed = true;
  public static boolean intakeOn;
  public static String alliance = "";
  public static double xDrive;
  public static double pivotPosition = 0;
  public static double hzPosition = 0;
  public static boolean hzUp = false;
  public static boolean vtUp = false;
  public static boolean pivotUp = false;
  public static boolean climbFullExt = false;
  public static boolean clawOpen = true;
  public static boolean grabbing = true;
  public static boolean hasHatch = false;
  public static int airStage = 1 ;

  //Joystick Deadspace Controls
  public static double mainstickX;
  public static double mainstickY;
  public static double mainstickZ;

  // AUX "encoders"
  //public static int climbEnc;
  //public static int LiftEnc;
  public static double testDist;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void robotInit() {
    oi = new OI();
    //rm = new RobotMap();
    oi.init();
    //rm.init();
    pn = new Pneumatics();
    
    RobotMap.comp0.setClosedLoopControl(true);
    /*if (isTestBot == true) {
      m_drive = new MecanumDrive(rm.testdriveTrainFrontLeftWheel, rm.testdriveTrainRearLeftWheel, rm.testdriveTrainFrontRightWheel, rm.testdriveTrainRearRightWheel);
    }
    else {
      m_drive = new MecanumDrive(rm.driveTrainFrontLeftWheel, rm.driveTrainRearLeftWheel, rm.driveTrainFrontRightWheel, rm.driveTrainRearRightWheel);
    } */
    d_drive = new DifferentialDrive(RobotMap.left_drive, RobotMap.right_drive);
    
      RobotMap.dio8.set(true);
      RobotMap.dio9.set(true);
      RobotMap.rioGyro.calibrate();
      RobotMap.encoderLRear.reset();
      RobotMap.encoderRRear.reset();
      turbo=false;
      
      //Camera setup
      cam0 = CameraServer.getInstance().startAutomaticCapture();
      //cam0.setResolution(640, 480);
      cam0.setVideoMode(PixelFormat.kMJPEG, 320, 240, 15);
      //cam0.setFPS(15);
      cam0.setBrightness(15);  
      cam1 = CameraServer.getInstance().addAxisCamera("10.15.18.100");
      cam1.setResolution(320, 240);
      cam1.setBrightness(40);

      //Get Alliance from FMS
      alliance = DriverStation.getInstance().getAlliance().toString();
      SmartDashboard.putString("Alliance", alliance);
      
      // instantiate the command used for the autonomous period
      m_chooser = new SendableChooser();
      m_chooser.setDefaultOption("No Auto", null);
      m_chooser.addOption("Only Cross Line", new CrossLineOnlyAuto());
      m_chooser.addOption("Opposite Switch No Drop", new MiddleSwitchNoDropAuto());
      m_chooser.addOption("Home Switch From Middle", new MiddleSwitchAuto());
      m_chooser.addOption("Robot Left (Switch)", new LeftSwitchAuto());
      m_chooser.addOption("Robot Right (Switch)", new RightSwitchAuto());
      m_chooser.addOption("Robot Left (Scale)", new LeftScaleAuto());;
      m_chooser.addOption("Robot Right (Scale)",  new RightScaleAuto());
      m_chooser.addOption("TestAuto",  new TestAuto());
      SmartDashboard.putData("AutoMode", m_chooser);

  //testDist = SmartDashboard.getNumber("Test Distance", 0);
      
    //SETTING BRAKE MODE ON DRIVE MOTORS
    RobotMap.driveTrainLF.setNeutralMode(NeutralMode.Brake);
    RobotMap.driveTrainRF.setNeutralMode(NeutralMode.Brake);
    RobotMap.driveTrainRR.setNeutralMode(NeutralMode.Brake);
    RobotMap.driveTrainLR.setNeutralMode(NeutralMode.Brake);
  
}

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Distance", RobotMap.leftHRLV.GetRangeInInches());
    SmartDashboard.putNumber("RIght Distance", RobotMap.rightHRLV.GetRangeInInches());

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    RobotMap.dio8.set(true);
    RobotMap.dio9.set(true);
    RobotMap.rioGyro.reset();
    RobotMap.encoderLRear.reset();
    RobotMap.encoderRRear.reset();
    isReversed = true;
    intakeOn = false;
    d_drive.stopMotor();

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
        // schedule the autonomous command (example)
    	//rm.BoxSwitch.reset();
      setLights();
    	d_drive.setSafetyEnabled(true);
    	//SmartDashboard.getData("Test-Distance");
		  //testDist = SmartDashboard.getNumber("Test-Distance", 0);
		  //System.out.println(testDist);
      autoMode = (Command) m_chooser.getSelected();
      if (autoMode != null) autoMode.start();

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autoMode != null) autoMode.cancel();
      RobotMap.rioGyro.reset();
      setLights();
      Pneumatics.one.set(true);
      Pneumatics.two.set(false);
      Pneumatics.three.set(true);
      Pneumatics.four.set(false);
      airStage = 3;
      d_drive.setSafetyEnabled(true);
      clawOpen = true;
      grabbing = true;
      hasHatch = false;
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
        //turbo = true;
        if (OI.turbo.get() == false) {
        	xDrive = 1;  //0.85;				CHANGE FOR MAIN ROBOT
        }
        
        else {
        	xDrive = .5;  //0.65;				CHANGE FOR MAIN ROBOT
        }
        
    	//COMPUTE JOYSTICK VALUES GIVING DEADSPACE
    	if (Math.abs(OI.mainstick.getX()) >= 0.30) {	
    		mainstickX = OI.mainstick.getX(); 
    	}
    	
    	else { 
    		mainstickX = 0; 
    	}
    	
    	if (Math.abs(OI.mainstick.getY()) >= 0.30) {	
    		mainstickY = OI.mainstick.getY(); 
    	}
    	
    	else { 
    		mainstickY = 0; 
    	}
    	
    	if (Math.abs(OI.mainstick.getZ()) >= 0.25) {	
    		mainstickZ = OI.mainstick.getZ(); 
    	}
    	
    	else { 
    		mainstickZ = 0; 
    	}
      
      // Reading the Gyro angle to display on dashboard and adjust driving conditions
    	SmartDashboard.putNumber("Gyro Angle", RobotMap.rioGyro.getAngle());
      SmartDashboard.putNumber("Left Distance", RobotMap.leftHRLV.GetRangeInInches());
      SmartDashboard.putNumber("RIght Distance", RobotMap.rightHRLV.GetRangeInInches());
    
    	//SmartDashboard.putNumber("Left Encoder Count", rm.encoderLRear.get());    // PUT BACK
      //SmartDashboard.putNumber("Right Encoder Count", rm.encoderRRear.get());  //  PUT BACK
      d_drive.arcadeDrive((Math.pow(-mainstickY, 3) * xDrive), (Math.pow(mainstickZ, 3) * .75) + mainstickX);
      
      //Control for the wheels on the lift legs - only available when lift is fully extended
      if(climbFullExt) {
        RobotMap.rearClimbWheel1.set(OI.gp1.getY());
        RobotMap.rearClimbWheel2.set(OI.gp1.getY());
      }
      else {
        RobotMap.rearClimbWheel1.set(0);
        RobotMap.rearClimbWheel2.set(0);
      }
      
      //RobotMap.dio8.pulse(1);
    	//RobotMap.dio9.pulse(0);
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  public void setLights() {
    alliance = DriverStation.getInstance().getAlliance().toString();
    if (alliance == "Red") {
      RobotMap.dio8.set(false);
      RobotMap.dio9.set(true);
    }
    else if (alliance == "Blue"){
    RobotMap.dio8.set(true);
    RobotMap.dio9.set(false);
    }
    else {
      RobotMap.dio8.set(true);
      RobotMap.dio9.set(true);
    } 
  }
}
