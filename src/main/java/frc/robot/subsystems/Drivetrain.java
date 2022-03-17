// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  //motorcontrollers
  static WPI_TalonSRX _leftFollow;
  static WPI_TalonSRX _leftLead;
  static WPI_TalonSRX _rightLead;
  static WPI_TalonSRX _rightFollow;
  
//TODO: test PID control
  // PID values
  // private double kP = 0.15 * 0.75;
  // private double kI = 0.002 * 0;
  // private double kD = 0.0;
  // private double kF = 0.052;

  //motorcontroller groups
  MotorControllerGroup leftMotors;
  MotorControllerGroup rightMotors;

  //differenctial drive
  DifferentialDrive differentialDrive;

  public Drivetrain() {
    //create motorcontrollers
    _leftFollow = new WPI_TalonSRX(Constants.DRIVETRAIN_LEFT_BACK_TALON);  //2 dont ask me why it isnt 0
    _leftLead = new WPI_TalonSRX(Constants.DRIVETRAIN_LEFT_FRONT_TALON);   //3
    _rightLead = new WPI_TalonSRX(Constants.DRIVETRAIN_RIGHT_FRONT_TALON); //4 
    _rightFollow = new WPI_TalonSRX(Constants.DRIVETRAIN_RIGHT_BACK_TALON);//5

    //group motorcontrollers
    leftMotors = new MotorControllerGroup(_leftLead, _leftFollow);
    rightMotors = new MotorControllerGroup(_rightLead, _rightFollow);

    //differenctial drive
    differentialDrive = new DifferentialDrive(_leftLead, _rightLead);

    //configure motorcontrollers
    configureTalons();
  }

  //using two controllers
  public void tankDrive(double moveSpeedRight, double moveSpeedLeft){
    differentialDrive.tankDrive(moveSpeedLeft, moveSpeedRight);
  }

  //using only one controller
  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void configureTalons(){

    /* Factory Default all hardware to prevent unexpected behaviour */
    _leftLead.configFactoryDefault();
    _rightLead.configFactoryDefault();
    _leftFollow.configFactoryDefault();
    _rightFollow.configFactoryDefault();
                            
    /* Set Neutral mode */
    _leftLead.setNeutralMode(NeutralMode.Brake);
    _rightLead.setNeutralMode(NeutralMode.Brake);
    _leftFollow.setNeutralMode(NeutralMode.Brake);
    _rightFollow.setNeutralMode(NeutralMode.Brake);
                            
    /* Configure output direction */
    _leftLead.setInverted(true);
    _rightLead.setInverted(false);
    _leftFollow.setInverted(true);
    _rightFollow.setInverted(false);

    //follow motors
    _leftFollow.follow(_leftLead);
    _rightFollow.follow(_rightLead);

//TODO: test PID control
    // Add PID constants
    // _leftLead.config_kP(0, kP);
    // _leftLead.config_kI(0, kI);
    // _leftLead.config_kD(0, kD);
    // _leftLead.config_kF(0, kF);
    // _leftLead.configMaxIntegralAccumulator(0, 8000);
    
    // _rightLead.config_kP(0, kP);
    // _rightLead.config_kI(0, kI);
    // _rightLead.config_kD(0, kD);
    // _rightLead.config_kF(0, kF);
    // _rightLead.configMaxIntegralAccumulator(0, 8000);
  }
}
