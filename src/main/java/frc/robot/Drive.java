package frc.robot;

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
    WPI_TalonSRX _CMain;
    WPI_TalonSRX _CControl;
    WPI_TalonSRX _intake;
    
    /** Hardware, either Talon could be a Victor */
    public Joystick controllerRight = new Joystick(0);
    public Joystick controllerLeft = new Joystick(1);
    public XboxController shooterCOntroller = new XboxController(2);

    public Drive(WPI_TalonSRX leftFront, 
                    WPI_TalonSRX leftBack, 
                    WPI_TalonSRX rightFront, 
                    WPI_TalonSRX rightBack,
                    WPI_TalonSRX CMain,
                    WPI_TalonSRX CControl,
                    WPI_TalonSRX intake){

        this._leftBack = leftBack;
        this._leftFront = leftFront;
        this._rightBack = rightBack;
        this._rightFront = rightFront;
        this._CMain = CMain;
        this._CControl = CControl;
        this._intake = intake;
    }
    
    public void TankDrive(){
      /* Gamepad processing */
      double forwardLeft =speedLimit(speedValue(controllerLeft.getY()), DriveConstants.MAX_SPEED);
      double forwardRight = speedLimit(speedValue(controllerRight.getY()), DriveConstants.MAX_SPEED);

      _leftFront.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
      _rightFront.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0);
      _leftBack.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
      _rightBack.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0); 
    }

    public void CShooter(){
        boolean CMainShoot = shooterCOntroller.getAButtonPressed();
        boolean CControlShoot = shooterCOntroller.getBButtonPressed();

        if(CMainShoot){
            _CMain.set(ControlMode.PercentOutput, DriveConstants.MAIN_SHOOTER_SPEED);
        }
        if(CControlShoot){
            _CControl.set(ControlMode.PercentOutput, DriveConstants.CONTROL_SHOOTER_SPEED);
        }
        
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
}
