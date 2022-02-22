// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  	/** Hardware, either Talon could be a Victor */
  private Drive m_drive;
  private Controller lController, rController;
  XboxController xController;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code .
   */
  @Override
  public void robotInit() {
    rController = new Controller(0);
    lController = new Controller(1);
    xController = new XboxController(2);
    
    //init Drive system
    m_drive = new Drive(lController, rController, xController);

    //init USBcamera
    CameraServer.startAutomaticCapture();
    if(RobotBase.isReal()){
      //reduce fps to save network
      CameraServer.startAutomaticCapture().setFPS(8);
    }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

      
   /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //make sure talons are configured
    m_drive.configureTalons();
    //tank drive		
    m_drive.tankDrive();
  }
 
  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
