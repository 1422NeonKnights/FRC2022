package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class DriveSystem extends TimedRobot{
    //talon SRX motor controllers(2 controllers each side)
    WPI_TalonSRX _leftFront = new WPI_TalonSRX(3);
    WPI_TalonSRX _leftBack = new WPI_TalonSRX(2);
	WPI_TalonSRX _rightFront= new WPI_TalonSRX(4);
    WPI_TalonSRX _rightBack = new WPI_TalonSRX(5);

    //joysticks = tank drive system, 1 joysticks control whole left/right motor
	Joystick controllerLeft = new Joystick(1);
    Joystick controllerRight = new Joystick(0);
    
 /** This function is called once when teleop is enabled. */
 @Override
 public void teleopInit() {
    /* Ensure motor output is neutral during init */
    _leftFront.set(ControlMode.PercentOutput, 0);
    _rightFront.set(ControlMode.PercentOutput, 0);
    _leftBack.set(ControlMode.PercentOutput, 0);
    _rightBack.set(ControlMode.PercentOutput, 0);

    /* Factory Default all hardware to prevent unexpected behaviour */
    _leftFront.configFactoryDefault();
    _rightFront.configFactoryDefault();
    _leftBack.configFactoryDefault();
    _rightBack.configFactoryDefault();
       
    /* Set Neutral mode */
    _leftFront.setNeutralMode(NeutralMode.Brake);
    _rightFront.setNeutralMode(NeutralMode.Brake);
    _leftBack.setNeutralMode(NeutralMode.Brake);
    _rightBack.setNeutralMode(NeutralMode.Brake);
       
    /* Configure output direction */
    _leftFront.setInverted(true);
    _rightFront.setInverted(false);
    _leftBack.setInverted(true);
    _rightBack.setInverted(false);
   }
    /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {		
      /* Gamepad processing */
      double forwardLeft = speedValue(controllerLeft.getY());
      double forwardRight = speedValue(controllerRight.getY());

      /* Arcade Drive using PercentOutput along with Arbitrary Feed Forward supplied by turn */
      _leftFront.set(ControlMode.MotionMagic, forwardLeft, DemandType.AuxPID, 0);
      _rightFront.set(ControlMode.MotionMagic, forwardRight, DemandType.AuxPID, 0);
      _leftBack.set(ControlMode.MotionMagic, forwardLeft, DemandType.AuxPID, 0);
      _rightBack.set(ControlMode.MotionMagic, forwardRight, DemandType.AuxPID, 0);
    }

    double speedValue(double controllerValue){
        controllerValue *= -1;
        return Math.pow(2, controllerValue)-1;
    }
}
