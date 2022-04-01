// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShootForTime extends CommandBase {
  Shooter shooter = RobotContainer.m_shooter;
  Intake intake = RobotContainer.m_intake;

  private final double mainSpeed;
  private final double controlSpeed;
  private final double intakeSpeed;

  private int counter = 0;
  private int target = 0;
  /** Creates a new ShootForTime. */
  public ShootForTime(double mainSpeed, double controlSpeed, double intakeSpeed, double seconds) {
    this.mainSpeed = mainSpeed;
    this.controlSpeed = controlSpeed;
    this.intakeSpeed = intakeSpeed;

    //Convert time in seconds to robot cycle(50 cycles/s)
    target = (int)(seconds * 50);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //shooter runs first
    shooter.shootAutonomous(mainSpeed, controlSpeed);
    if(counter < target){
      counter++;
    }
    //speeds the intake for teh ball to go up
    intake.AutunomousIntake(intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shootAutonomous(0, 0);
    intake.AutunomousIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
