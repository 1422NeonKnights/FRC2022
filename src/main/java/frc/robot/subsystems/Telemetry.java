// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Telemetry extends SubsystemBase {
  /** Creates a new Telemetry. */
  public Telemetry() {
  }

  public void update(){
    SmartDashboard.putNumber("Drive Speed", RobotContainer.m_drivetrain.getDriveSpeed());
  }

  @Override
  public void periodic() {
    
  }
}
