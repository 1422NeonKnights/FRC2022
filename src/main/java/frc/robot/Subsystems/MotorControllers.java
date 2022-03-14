package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class MotorControllers {
     //motor controllers
    public static WPI_TalonSRX _leftBack = new WPI_TalonSRX(2);
    public static WPI_TalonSRX _leftFront = new WPI_TalonSRX(3);
    public static WPI_TalonSRX _rightFront= new WPI_TalonSRX(4);
    public static WPI_TalonSRX _rightBack = new WPI_TalonSRX(5);
    public static WPI_TalonSRX _CMain = new WPI_TalonSRX(6);
    public static WPI_TalonSRX _CControl = new WPI_TalonSRX(7);

    public static void configureTalons(){
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
}
