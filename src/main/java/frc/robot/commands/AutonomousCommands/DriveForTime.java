// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Drivetrain;

//Drives the robot for a set period of time
//Used for autonomous

public class DriveForTime extends CommandBase {
  
  private final Drivetrain drivetrain;
  private final double speed;

  private int counter = 0;
  private int target =0;

  /** Creates a new DriveForTime. */
  public DriveForTime(Drivetrain drivetrain, double speed, double seconds) {
    this.drivetrain = drivetrain;
    this.speed = speed;
    
    //Convert time in seconds to robot cycle(50 cycles/s)
    target = (int)(seconds * 50);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    addRequirements(RobotContainer.m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(counter < target){
      counter++;
    }
    RobotContainer.m_shooter.mainMotorShoot(ShooterConstants.mainMotorSpeed);
    drivetrain.arcadeDrive(speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
