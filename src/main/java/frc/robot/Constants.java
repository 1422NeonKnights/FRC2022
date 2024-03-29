// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */


 //all speed should be in percent
public final class Constants {
    public class DriveConstants {
        //Drive Motors
        public static final int DRIVETRAIN_LEFT_BACK_TALON = 2;  //2
        public static final int DRIVETRAIN_LEFT_FRONT_TALON = 3; //3
        public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 4; //4
        public static final int DRIVETRAIN_RIGHT_BACK_TALON = 5; //5

        //Speed in percentage
        public static final double MAX_SPEED = 0.87;
        public static final double TILT_CAP = MAX_SPEED-0.07
        ;
        
        //Drive PIDF
        public static final double kP = 0.175;
        public static final double kI = 0.0001;
        public static final double kD = 0.00;
        public static final double kF = 12/6052 * 4000;
    }

    public class ShooterConstants{
        //CShooter Motors
        public static final int C_SHOOTER_CONTROL_TALON = 7;
        public static final int C_SHOOTER_MAIN_TALON = 8;

        //CMain PIDF
        public static final double mainMotorSpeed = 1;
        public static final double C_kP = 0.155;
        public static final double C_kI = 0.00;
        public static final double C_kD = 0;
        public static final double C_kF = 11/6000 * 4000;

        //CControl PIDF
        public static final double controlMotorSpeed = 4000; //TBD
        public static final double M_kP = 0.155;
        public static final double M_kI = 0.00;
        public static final double M_kD = 0;
        public static final double M_kF = 11/6000 * 4000;
    }

    public class IntakeConstants{
        //Talon id
        public static final int INTAKEMAIN = 11;

        //speed
        public static final double speedPercent = 0.76;

        //PIDF
        public static final double C_kP = 0.175;
        public static final double C_kI = 0.00;
        public static final double C_kD = 0;
        public static final double C_kF = 0.135;
    }

    public class ControllerConstants{
        //MAKE SURE THIS IS CORRECT IN THE DRIVER STATION
        //Joysticks drive
        public static final int CONTROLLER_LEFT = 0;
        public static final int CONTROLLER_RIGHT = 1;

        //Xboxcontroller
        public static final int XBOX_CONTROLLER = 2;
    }

    public class AutonomousConstants{
        public static final double DRIVE_SPEED = 0.67;
        public static final double CMAIN_SPEED = 1;
        public static final double CCONTROL_SPEED = 1;
        
    }
    public class UtilConstants{
        public static final int CAMERA_FPS = 8;
    }

}
