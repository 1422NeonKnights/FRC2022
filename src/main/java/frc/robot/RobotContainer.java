// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import java.sql.Driver;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
// import frc.robot.Constants.AutonomousConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDrive;
// import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.IntakeRoll;
import frc.robot.commands.Shoot;
//import frc.robot.commands.TankDrive;
// import frc.robot.commands.AutonomousCommands.DriveForTime;
// import frc.robot.commands.AutonomousCommands.ShootForTime;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  Command command;
  // Subsystems
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Shooter m_shooter = new Shooter();
  public static final Intake m_intake = new Intake();

  //Joysticks
  public static Joystick controllerLeft = new Joystick(ControllerConstants.CONTROLLER_LEFT);   //0
  public static Joystick controllerRight = new Joystick(ControllerConstants.CONTROLLER_RIGHT); //1

  //XBox Controller
  public static XboxController XboxControl = new XboxController(ControllerConstants.XBOX_CONTROLLER); //2

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    //detect which drive train to use depending on connected controllers.
    //TODO: Test this out, not complete
      m_drivetrain.setDefaultCommand(new ArcadeDrive());
    //TODO:if above command does not work, us this code below and delete the above one
    //m_drivetrain.setDefaultCommand(new ArcadeDrive());

    //build shooter commands
    m_shooter.setDefaultCommand(new Shoot());
    //build intake commands
    m_intake.setDefaultCommand(new IntakeRoll());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      //write autonoous code
  
      //TODO: Make a BETTER autonomous
      // new DriveForTime(m_drivetrain, -0.87, 0.6),
      // new DriveForTime(m_drivetrain, AutonomousConstants.DRIVE_SPEED, 1),
      // new ShootForTime(AutonomousConstants.CMAIN_SPEED, AutonomousConstants.CCONTROL_SPEED, IntakeConstants.speedPercent, 1.5),
      // new DriveForTime(m_drivetrain, -0.6, 4)
    );
  }
}
 