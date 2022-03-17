// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  //motorcontrollers
  static WPI_TalonSRX _leftBack;
  static WPI_TalonSRX _leftFront;
  static WPI_TalonSRX _rightFront;
  static WPI_TalonSRX _rightBack;

  MotorControllerGroup leftMotors;
  MotorControllerGroup rightMotors;

  DifferentialDrive differentialDrive;

  public Drivetrain() {
    _leftBack = new WPI_TalonSRX(Constants.DRIVETRAIN_LEFT_BACK_TALON);     //2
    _leftFront = new WPI_TalonSRX(Constants.DRIVETRAIN_LEFT_FRONT_TALON);   //3
    _rightFront = new WPI_TalonSRX(Constants.DRIVETRAIN_RIGHT_FRONT_TALON); //4 
    _rightBack = new WPI_TalonSRX(Constants.DRIVETRAIN_RIGHT_BACK_TALON);   //5

    leftMotors = new MotorControllerGroup(_leftBack, _leftFront);
    rightMotors = new MotorControllerGroup(_rightBack, _rightFront);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public static void configureTalons(){
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

  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }
  
  public void tankDrive(double moveSpeedRight, double moveSpeedLeft){
      /* Gamepad processing */
      double forwardLeft = moveSpeedRight;
      double forwardRight = moveSpeedLeft;

      /* Arcade Drive using PercentOutput along with Arbitrary Feed Forward supplied by turn */
      _leftFront.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
      _rightFront.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0);
      _leftBack.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
      _rightBack.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0); 
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
