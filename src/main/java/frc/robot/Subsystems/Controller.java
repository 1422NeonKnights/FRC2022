package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controller extends Joystick{
    private static final int BUTTON_TRIGGER_RIGHT = 1;
    private static final int BUTTON_TRIGGER_LEFT = 2;

    public Controller(int port) {
        super(port);
    }
    
    public JoystickButton getLeftTriggerClick() {
        return new JoystickButton(this, BUTTON_TRIGGER_LEFT);
      }
    
      public JoystickButton getRightTriggerClick() {
        return new JoystickButton(this, BUTTON_TRIGGER_RIGHT);
      }
}

