// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sim.PhysicsSim;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  	/** Hardware, either Talon could be a Victor */
	WPI_TalonSRX _leftFront = new WPI_TalonSRX(3);
	WPI_TalonSRX _rightFront= new WPI_TalonSRX(4);
  WPI_TalonSRX _rightBack = new WPI_TalonSRX(5);
  WPI_TalonSRX _leftBack = new WPI_TalonSRX(2);

	Joystick controllerLeft = new Joystick(1);
  Joystick controllerRight = new Joystick(0);

  
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  @Override
	public void simulationInit() {
		PhysicsSim.getInstance().addTalonSRX(_leftFront, 0.75, 4000);
		PhysicsSim.getInstance().addTalonSRX(_rightFront, 0.75, 4000);
		PhysicsSim.getInstance().addTalonSRX(_leftBack, 0.75, 4000);
		PhysicsSim.getInstance().addTalonSRX(_rightBack, 0.75, 4000);
	}

	@Override
	public void simulationPeriodic() {
		PhysicsSim.getInstance().run();
  }
 
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code .
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    CameraServer.startAutomaticCapture();
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
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

      
   /** This function is called once when teleop is enabled. */
  @Override
   public void teleopInit() {
      /* Ensure motor output is neutral during init */
      _leftFront.set(ControlMode.PercentOutput, 0);
      _rightFront.set(ControlMode.PercentOutput, 0);
      _leftBack.set(ControlMode.PercentOutput, 0);
      _rightBack.set(ControlMode.PercentOutput, 0);
  
      /* Factory Default all hardware to prevent unexpected behaviour */
      _leftFront.configFactoryDefault();
      _rightFront.configFactoryDefault();
      _leftBack.configFactoryDefault();
      _rightBack.configFactoryDefault();
         
      /* Set Neutral mode */
      _leftFront.setNeutralMode(NeutralMode.Brake);
      _rightFront.setNeutralMode(NeutralMode.Brake);
      _leftBack.setNeutralMode(NeutralMode.Brake);
      _rightBack.setNeutralMode(NeutralMode.Brake);
         
      /* Configure output direction */
      _leftFront.setInverted(true);
      _rightFront.setInverted(false);
      _leftBack.setInverted(true);
      _rightBack.setInverted(false);
     }
      /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {		
        /* Gamepad processing */
        double forwardLeft = speedValue(controllerLeft.getY());
        double forwardRight = speedValue(controllerRight.getY());
  
        /* Arcade Drive using PercentOutput along with Arbitrary Feed Forward supplied by turn */
        _leftFront.set(ControlMode.MotionMagic, forwardLeft, DemandType.AuxPID, 0);
        _rightFront.set(ControlMode.MotionMagic, forwardRight, DemandType.AuxPID, 0);
        _leftBack.set(ControlMode.MotionMagic, forwardLeft, DemandType.AuxPID, 0);
        _rightBack.set(ControlMode.MotionMagic, forwardRight, DemandType.AuxPID, 0);
      }
  
      double speedValue(double controllerValue){
          controllerValue *= -1;
          return Math.pow(2, controllerValue)-1;
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
