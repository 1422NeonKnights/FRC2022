package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.DriveConstants;

public class Drive {
    //motor controllers
    WPI_TalonSRX _leftBack = new WPI_TalonSRX(2);
    WPI_TalonSRX _leftFront = new WPI_TalonSRX(3);
	WPI_TalonSRX _rightFront= new WPI_TalonSRX(4);
    WPI_TalonSRX _rightBack = new WPI_TalonSRX(5);

    //logitech attack
    Joystick controllerLeft, controllerRight;
    XboxController controllerX;

    public Drive(Joystick m_controllerLeft, Joystick m_controllerRight, XboxController xbox_controller){
        this.controllerLeft = m_controllerLeft;
        this.controllerRight = m_controllerRight;
        this.controllerX = xbox_controller;
        
        configureTalons();
    } 
    public void configureTalons(){
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

    //separation of controllers
    public void tankDrive(){
        /* Gamepad processing */
         double forwardLeft = speedValue(controllerLeft.getY());
         double forwardRight = speedValue(controllerRight.getY());
   
         /* Arcade Drive using PercentOutput along with Arbitrary Feed Forward supplied by turn */
         _leftFront.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
         _rightFront.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0);
         _leftBack.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
         _rightBack.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0);
    }

    public void tankDriveLimited(){
        /* Gamepad processing */
        double forwardLeft = speedLimit(speedValue(controllerLeft.getY()), DriveConstants.MAX_SPEED);
        double forwardRight = speedLimit(speedValue(controllerRight.getY()), DriveConstants.MAX_SPEED);
 
        /* Arcade Drive using PercentOutput along with Arbitrary Feed Forward supplied by turn */
        _leftFront.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
        _rightFront.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0);
        _leftBack.set(ControlMode.PercentOutput, forwardLeft, DemandType.ArbitraryFeedForward, 0);
        _rightBack.set(ControlMode.PercentOutput, forwardRight, DemandType.ArbitraryFeedForward, 0); 
    }
    
    public void Arcade(){
        
    }

    //Arcade control with one xbox controller
    public void ArcadeXbox(){
        
    }

    //exponential increase of speed       
    double speedValue(double controllerValue){
       controllerValue *= -1;
       if(controllerValue>=0.05){
           return Math.pow(2, Math.abs(controllerValue)-1);
       }
       if(controllerValue<=-0.05){
           return -(Math.pow(2, Math.abs(controllerValue)-1));
       }
       return 0;
    }

    //limits speed(percentage)
    double speedLimit(double speed, double maxSpeed){
        if(Math.abs(speed)>=maxSpeed){
            return maxSpeed;
        }else{
            return speed;
        }
    }
}
