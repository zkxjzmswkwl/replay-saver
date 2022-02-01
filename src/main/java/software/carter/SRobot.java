package software.carter;

import java.awt.Robot;
import java.awt.AWTException;

public class SRobot {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
            robot.setAutoDelay(50);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static Robot getRobot() {
        return robot;
    }
}