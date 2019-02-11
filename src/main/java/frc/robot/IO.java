package frc.robot;

import frc.robot.framework.IO.In;
import frc.robot.framework.IO.Out;
import frc.robot.framework.Util.Chassis;
import frc.robot.framework.IO.sensors.ControllerAxisSensor;
import frc.robot.framework.IO.sensors.PixyBlocksSensor;
import frc.robot.framework.IO.sensors.ControllerButtonSensor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class IO{
    public static In in;
    public static Out out;
    public static Chassis chassis;
    private static Joystick driver = new Joystick(0);
    private static Joystick operator = new Joystick(1);

    public static int DRIVER_LEFT_Y;
    public static int DRIVER_RIGHT_Y;
    public static int DRIVER_SLOW;
    public static int DRIVER_FAST;
    public static int DRIVER_BALL_SEEK;
    public static int OPERATOR_INTAKE_IN;
    public static int OPERATOR_INTAKE_OUT;
    public static int OPERATOR_ARM_HEIGHT;
    public static int OPERATOR_PUSHER_OUT;
    public static int OPERATOR_PUSHER_IN;
    public static int OPERATOR_LIFT_UP;
    public static int OPERATOR_LIFT_DOWN;

    public static int PIXY;
    public static Gyro GYRO;

    public static Encoder LEFT_ENCODER;
    public static Encoder RIGHT_ENCODER;
    public static Encoder ARM_ENCODER;

    public static int DRIVE_RIGHT = Constants.DRIVE_RIGHT_ID;
    public static int DRIVE_RIGHT_SLAVE = Constants.DRIVE_RIGHT_SLAVE_ID;
    public static int DRIVE_LEFT = Constants.DRIVE_LEFT_ID;
    public static int DRIVE_LEFT_SLAVE = Constants.DRIVE_LEFT_SLAVE_ID;

    public static int INTAKE = Constants.INTAKE_ID;
    public static int ARM = Constants.ARM_LIFT_ID;

    public static int PUSHER_SOLENOID = Constants.PUSHER_SOL_ID;
    public static int LIFT_SOLENOID = Constants.LIFT_SOL_ID;

    public static void Initialize(){
        in = new In();
        out = new Out();
        chassis = new Chassis(DRIVE_RIGHT, DRIVE_RIGHT_SLAVE, DRIVE_LEFT, DRIVE_LEFT_SLAVE);

        out.motors.add(DRIVE_RIGHT, true);
        out.motors.add(DRIVE_RIGHT_SLAVE, true);
        out.motors.add(DRIVE_LEFT, false);
        out.motors.add(DRIVE_LEFT_SLAVE, false);

        out.motors.add(INTAKE, false);
        out.motors.add(ARM, false);

        out.solenoids.add(PUSHER_SOLENOID);
        out.solenoids.add(LIFT_SOLENOID);

        DRIVER_LEFT_Y = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_LEFT_AXIS_ID));
        DRIVER_RIGHT_Y = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_RIGHT_AXIS_ID));
        DRIVER_SLOW = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_SLOW_AXIS_ID));
        DRIVER_FAST = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_FAST_AXIS_ID));
        DRIVER_BALL_SEEK = in.add(new ControllerButtonSensor(driver, Constants.DRIVER_BALL_SEEK_BUTTON_ID));

        OPERATOR_INTAKE_IN = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_INTAKE_IN_ID));
        OPERATOR_INTAKE_OUT = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_INTAKE_OUT_ID));
        OPERATOR_ARM_HEIGHT = in.add(new ControllerAxisSensor(operator, Constants.OPERATOR_ARM_HEIGHT_ID));
        OPERATOR_PUSHER_OUT = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_PUSHER_OUT_ID));
        OPERATOR_PUSHER_IN = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_PUSHER_IN_ID));
        OPERATOR_LIFT_UP = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_LIFT_UP_ID));
        OPERATOR_LIFT_DOWN = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_LIFT_DOWN_ID));

        PIXY = in.add(new PixyBlocksSensor(Constants.PIXY_BALL_ID, 1));

        GYRO = new ADXRS450_Gyro();

        LEFT_ENCODER = new Encoder(Constants.LEFT_ENCODER_1, Constants.LEFT_ENCODER_2, false);
        RIGHT_ENCODER = new Encoder(Constants.RIGHT_ENCODER_1, Constants.RIGHT_ENCODER_2, true);
        ARM_ENCODER = new Encoder(Constants.ARM_ENCODER_1, Constants.ARM_ENCODER_2, false);
    }
}