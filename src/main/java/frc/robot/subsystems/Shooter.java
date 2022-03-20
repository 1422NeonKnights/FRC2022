// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  //define shooter motor controllers
  WPI_TalonSRX CControl;
  WPI_TalonSRX CMain;
  /** Creates a new Shooter. */
  public Shooter() {
    configureTalons();
  }

  public void shoot(double targetVelocity, double M_targetVelocity, boolean isPressed) {
    //TODO: set correct equation
    double targetVelocityInTicks = targetVelocity * 2048.0 / 600.0;
    double M_targetVelocityInTicks = M_targetVelocity * 2048.0 / 600.0;

    //if button is pressed
    if(isPressed){
      CMain.set(ControlMode.Velocity, targetVelocityInTicks);
      CControl.set(ControlMode.Velocity, M_targetVelocityInTicks);
    }
  }

  public void stop() {
    CMain.set(0);
  }

  //configure Talons
  public void configureTalons(){
    //TODO: Use CAN ids
    CControl = new WPI_TalonSRX(ShooterConstants.C_SHOOTER_CONTROL_TALON); //6
    CMain = new WPI_TalonSRX(ShooterConstants.C_SHOOTER_MAIN_TALON);       //7

    CControl.configFactoryDefault();
    CMain.configFactoryDefault();

    //TODO:check motor inversion
    // CControl.setInverted(true);
    // CMain.setInverted(true);

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
