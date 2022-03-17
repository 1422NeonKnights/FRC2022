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
public final class Constants {
    //Motorcontroller numbers
    public static final int DRIVETRAIN_LEFT_BACK_TALON = 2;
    public static final int DRIVETRAIN_LEFT_FRONT_TALON = 3;
    public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 4;
    public static final int DRIVETRAIN_RIGHT_BACK_TALON = 5;

    //Joysticks tank dirve
    public static final int CONTROLLER_LEFT = 0;
    public static final int CONTROLLER_RIGHT = 1;
    
    //Joysticks arcade drive
    public static final int DRIVER_CONTROLLER = 0;
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 1;
    public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 2;
}