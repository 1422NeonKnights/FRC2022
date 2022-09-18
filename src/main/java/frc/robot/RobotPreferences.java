// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

/** Add your docs here. */
public class RobotPreferences {
    public static double driveSpeed(){
        return Preferences.getDouble("driveSpeed", 0.0);
    }
}
