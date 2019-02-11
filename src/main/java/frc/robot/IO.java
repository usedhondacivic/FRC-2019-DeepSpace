package frc.robot;

import frc.robot.framework.IO.In;
import frc.robot.framework.IO.Out;
import frc.robot.framework.Util.Chassis;
import frc.robot.framework.IO.sensors.ControllerAxisSensor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class IO{
    public static In in;
    public static Out out;
    public static Chassis chassis;
    private static Joystick driver = new Joystick(0);

    public static int DRIVER_LEFT_Y;
    public static int DRIVER_RIGHT_Y;
    public static int DRIVER_SLOW;
    public static int DRIVER_FAST;

    public static Encoder ENCODER_LEFT = new Encoder(Constants.LEFT_ENCODER_1, Constants.LEFT_ENCODER_2, false);
    public static Encoder ENCODER_RIGHT = new Encoder(Constants.RIGHT_ENCODER_1, Constants.RIGHT_ENCODER_2, true);

    public static Gyro GYRO;

    public static int DRIVE_RIGHT = Constants.DRIVE_RIGHT_ID;
    public static int DRIVE_RIGHT_SLAVE = Constants.DRIVE_RIGHT_SLAVE_ID;
    public static int DRIVE_LEFT = Constants.DRIVE_LEFT_ID;
    public static int DRIVE_LEFT_SLAVE = Constants.DRIVE_LEFT_SLAVE_ID;

    public static void Initialize(){
        in = new In();
        out = new Out();
        chassis = new Chassis(DRIVE_RIGHT, DRIVE_RIGHT_SLAVE, DRIVE_LEFT, DRIVE_LEFT_SLAVE);

        out.motors.add(DRIVE_RIGHT, true);
        out.motors.add(DRIVE_RIGHT_SLAVE, true);
        out.motors.add(DRIVE_LEFT, false);
        out.motors.add(DRIVE_LEFT_SLAVE, false);

        DRIVER_LEFT_Y = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_LEFT_AXIS_ID));
        DRIVER_RIGHT_Y = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_RIGHT_AXIS_ID));
        DRIVER_SLOW = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_SLOW_AXIS_ID));
        DRIVER_FAST = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_FAST_AXIS_ID));

        GYRO = new ADXRS450_Gyro();
    }
}