// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  //motorcontrollers
  WPI_TalonSRX _leftFollow;
  WPI_TalonSRX _leftLead;
  WPI_TalonSRX _rightLead;
  WPI_TalonSRX _rightFollow;

  //motorcontroller groups
  MotorControllerGroup leftMotors;
  MotorControllerGroup rightMotors;

  //differenctial drive
  DifferentialDrive differentialDrive;

  public Drivetrain() {
    
    configureMotors();

    //group motors
    leftMotors = new MotorControllerGroup(_leftLead, _leftFollow);
    rightMotors = new MotorControllerGroup(_rightLead, _rightFollow);

    //differenctial drive
    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  //using two controllers
  public void tankDrive(double moveSpeedRight, double moveSpeedLeft){
    differentialDrive.tankDrive(speedCheck(moveSpeedLeft), 
                                speedCheck(moveSpeedRight));
  }

  //using only one controller
  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(speedCheck(moveSpeed), speedCheck(rotateSpeed));
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Preferences
  public double getDriveSpeed(){
    return -RobotContainer.controllerLeft.getY();
  }

  //cap the speed to DriveConstatns.MAX_SPEED
  public double speedCheck(double speed){
    if(speed >= DriveConstants.MAX_SPEED){
      return DriveConstants.MAX_SPEED;
    }else if(speed <= -(DriveConstants.MAX_SPEED)){
      return -DriveConstants.MAX_SPEED;
    }else{
      return speed;
    }
  }
  public void configureMotors(){
     //create motorcontrollers
     _leftFollow = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_LEFT_BACK_TALON);  //2 dont ask me why it isnt 0
     _leftLead = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_LEFT_FRONT_TALON);   //3
     _rightLead = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_RIGHT_FRONT_TALON); //4 
     _rightFollow = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_RIGHT_BACK_TALON);//5
    
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
 
     //configure PIDF values
     _leftLead.config_kP(0, DriveConstants.kP);
     _leftLead.config_kI(0, DriveConstants.kI);
     _leftLead.config_kD(0, DriveConstants.kD);
     _leftLead.config_kF(0, DriveConstants.kF);
     _leftLead.configMaxIntegralAccumulator(0, 8000);
     
     _rightLead.config_kP(0, DriveConstants.kP);
     _rightLead.config_kI(0, DriveConstants.kI);
     _rightLead.config_kD(0, DriveConstants.kD);
     _rightLead.config_kF(0, DriveConstants.kF);
     _rightLead.configMaxIntegralAccumulator(0, 8000);

     _leftFollow.config_kP(0, DriveConstants.kP);
     _leftFollow.config_kI(0, DriveConstants.kI);
     _leftFollow.config_kD(0, DriveConstants.kD);
     _leftFollow.config_kF(0, DriveConstants.kF);
     _leftFollow.configMaxIntegralAccumulator(0, 8000);
     
     _rightFollow.config_kP(0, DriveConstants.kP);
     _rightFollow.config_kI(0, DriveConstants.kI);
     _rightFollow.config_kD(0, DriveConstants.kD);
     _rightFollow.config_kF(0, DriveConstants.kF);
     _rightFollow.configMaxIntegralAccumulator(0, 8000);
  }
}
