// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  //Talon srx
  WPI_TalonSRX intakeMotor;


  /** Creates a new Intake. */
  public Intake() {
    configureTalons();
  }

  public void IntakeRoll(double percent){
    if(RobotContainer.XboxControl.getBButtonPressed()){
      intakeMotor.set(ControlMode.PercentOutput, percent);
    }
    if(RobotContainer.XboxControl.getBButtonReleased()){
      //set motor output to 0
      stop();
    }
  }

  public void stop(){
    intakeMotor.set(0);
  }

  public void configureTalons(){
    intakeMotor = new WPI_TalonSRX(IntakeConstants.INTAKEMAIN);

    intakeMotor.configFactoryDefault();

    intakeMotor.config_kP(0, IntakeConstants.C_kP);
    intakeMotor.config_kI(0, IntakeConstants.C_kI);
    intakeMotor.config_kD(0, IntakeConstants.C_kD);
    intakeMotor.config_kF(0, IntakeConstants.C_kF);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
