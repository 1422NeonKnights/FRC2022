// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

  public void shoot(double targetVelocity, double M_targetVelocity) {
    //TODO: set correct equation
    double targetVelocityInTicks = targetVelocity * 2048.0 / 600.0;
    double M_targetVelocityInTicks = M_targetVelocity * 2048.0 / 600.0;

    //update booleans
    //updateToggle();

    if(RobotContainer.XboxControl.getAButtonPressed()){
      CMain.set(ControlMode.Velocity, targetVelocityInTicks);
      CControl.set(ControlMode.Velocity, M_targetVelocityInTicks);
    }
    if(RobotContainer.XboxControl.getAButtonReleased()){
      //set motor output to 0
      stop();
    }
  }

  public void stop() {
    CMain.set(0);
    CControl.set(0);
  }

  //toggle algo
  public void updateToggle() {
    if(RobotContainer.XboxControl.getAButton()){
      if(!togglePressed){
        toggleOn = !toggleOn;
        togglePressed = true;
      }
    }else{
        togglePressed = false;
    }
  }

  //configure Talons
  public void configureTalons(){
    CControl = new WPI_TalonSRX(ShooterConstants.C_SHOOTER_CONTROL_TALON); //6
    CMain = new WPI_TalonSRX(ShooterConstants.C_SHOOTER_MAIN_TALON);       //7

    CControl.configFactoryDefault();
    CMain.configFactoryDefault();

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
