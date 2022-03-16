// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  //motorcontrollers
  WPI_TalonSRX _leftBack;
  WPI_TalonSRX _leftFront;
  WPI_TalonSRX _rightFront;
  WPI_TalonSRX _rightBack;

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

  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
