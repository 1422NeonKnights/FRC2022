// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  //define shooter motor controllers
  WPI_TalonSRX CControl;
  WPI_TalonSRX CMain;

  //for toggle algo
  boolean toggleOn = false;
  boolean togglePressed = false;

  /** Creates a new Shooter. */
  public Shooter() {
    configureTalons();
  }

  public void shoot(double mainMotorSpeed, double controlMotorSpeed) {
    //Main motor control
    if(RobotContainer.XboxControl.getLeftTriggerAxis()>0){
      CMain.set(ControlMode.PercentOutput, mainMotorSpeed);
    }else if(RobotContainer.XboxControl.getLeftTriggerAxis()==0){
      //set motor output to 0
      stopCMain();
    }

    //Control motor control
    if(RobotContainer.XboxControl.getRightTriggerAxis()>0){
      CControl.set(ControlMode.PercentOutput, mainMotorSpeed);
    }else if(RobotContainer.XboxControl.getRightTriggerAxis()==0){
      stopCControl();
    }

    //reverse the motors(emergency)
    if(RobotContainer.XboxControl.getRightBumperPressed()){
      reverseMotors(true);
    }else if(RobotContainer.XboxControl.getRightBumperReleased()){
      reverseMotors(false);
    }

  }
  private void stopCMain(){
    CMain.set(0);
  }

  private void stopCControl(){
    CControl.set(0);
  }

  public void stop() {
    CMain.set(0);
    CControl.set(0);
  }

  private void reverseMotors(boolean status){
    CControl.setInverted(status);
    CMain.setInverted(status);
  }

  //configure Talons
  public void configureTalons(){
    CControl = new WPI_TalonSRX(ShooterConstants.C_SHOOTER_CONTROL_TALON); //6
    CMain = new WPI_TalonSRX(ShooterConstants.C_SHOOTER_MAIN_TALON);       //7

    CControl.configFactoryDefault();
    CMain.configFactoryDefault();

    CControl.setNeutralMode(NeutralMode.Brake);

    //PIDF Values
    //CMain
    CMain.config_kP(0, ShooterConstants.C_kP);
    CMain.config_kI(0, ShooterConstants.C_kI);
    CMain.config_kD(0, ShooterConstants.C_kD);
    CMain.config_kF(0, ShooterConstants.C_kF);
    //CControl
    CControl.config_kP(0, ShooterConstants.M_kP);
    CControl.config_kI(0, ShooterConstants.M_kI);
    CControl.config_kD(0, ShooterConstants.M_kD);
    CControl.config_kF(0, ShooterConstants.M_kF);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
