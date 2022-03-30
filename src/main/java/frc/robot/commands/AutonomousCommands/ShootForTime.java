// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootForTime extends CommandBase {
  private final Shooter shooter;
  private final double mainSpeed;
  private final double controlSpeed;

  private int counter = 0;
  private int target = 0;
  /** Creates a new ShootForTime. */
  public ShootForTime(Shooter shooter, double mainSpeed, double controlSpeed, double seconds) {
    this.shooter = shooter;
    this.mainSpeed = mainSpeed;
    this.controlSpeed = controlSpeed;

    //Convert time in seconds to robot cycle(50 cycles/s)
    target = (int)(seconds * 50);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
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

    shooter.shoot(mainSpeed, controlSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shoot(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
