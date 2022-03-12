package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.DriveConstants;

public class Drive {
    WPI_TalonSRX _leftBack;
    WPI_TalonSRX _leftFront;
    WPI_TalonSRX _rightFront;
    WPI_TalonSRX _rightBack;
    
    /** Hardware, either Talon could be a Victor */
    public Joystick controllerLeft = new Joystick(0);
    public Joystick controllerRight = new Joystick(1);
    XboxController xController;
    public Drive(WPI_TalonSRX leftFront, WPI_TalonSRX leftBack, WPI_TalonSRX rightFront, WPI_TalonSRX rightBack){
        this._leftBack = leftBack;
        this._leftFront = leftFront;
        this._rightBack = rightBack;
        this._rightFront = rightFront;
    }
    
    public void TankDrive(){
      /* Gamepad processing */
      double forwardLeft =speedLimit(speedValue(controllerLeft.getY()), DriveConstants.MAX_SPEED);
      double forwardRight = speedLimit(speedValue(controllerRight.getY()), DriveConstants.MAX_SPEED);

      /* Arcade Drive using PercentOutput along with Arbitrary Feed Forward supplied by turn */
      _leftFront.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
      _rightFront.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0);
      _leftBack.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
      _rightBack.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0); 

      speedCheck();
    }

    //speed value algorithm exponential base 2
    private double speedValue(double controllerValue){
        controllerValue *= -1;
        return Math.pow(2, controllerValue)-1;
     }
     //speed limit algorithm
    private double speedLimit(double speed, double maxSpeed){
      if(Math.abs(speed)>=maxSpeed){
          return maxSpeed;
      }else{
          return speed;
      }
    }

    private void speedCheck(){
        System.out.printf("L: %.4f R: %.4f L vel: %f R vel: %f\n", _leftFront.getMotorOutputPercent(), _rightFront.getMotorOutputPercent(),
                                _leftFront.getSelectedSensorVelocity(), _rightFront.getSelectedSensorVelocity());
    }
}
