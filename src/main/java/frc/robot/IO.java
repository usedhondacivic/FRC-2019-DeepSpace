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
    public static int DRIVER_REVERSE_CONTROLS;
    public static int DRIVER_LIFT_UP;
    public static int DRIVER_LIFT_MACRO;

    public static int OPERATOR_ARM_REZERO;

    public static int OPERATOR_INTAKE_IN;
    public static int OPERATOR_INTAKE_OUT;
    public static int OPERATOR_ARM_HEIGHT;
    public static int OPERATOR_PUSHER_OUT;
    public static int OPERATOR_CYCLE_PROFILE_UP;
    public static int OPERATOR_CYCLE_PROFILE_DOWN;
    public static int OPERATOR_LIFT_UP;
    public static int OPERATOR_ARM_BOOST_UP;
    public static int OPERATOR_ARM_BOOST_DOWN;

    public static int OPERATOR_ARM_LOW_HATCH;
    public static int OPERATOR_ARM_MIDDLE_HATCH;
    public static int OPERATOR_ARM_HIGH_HATCH;
    public static int OPERATOR_ARM_LOW_PORT;
    public static int OPERATOR_ARM_MIDDLE_PORT;
    public static int OPERATOR_ARM_HIGH_PORT;

    public static int OPERATOR_COMPRESSOR;

    public static int PIXY;
    public static Gyro GYRO;

    public static Encoder LEFT_ENCODER;
    public static Encoder RIGHT_ENCODER;
    public static Encoder ARM_ENCODER;

    public static int DRIVE_RIGHT = Constants.DRIVE_RIGHT_ID;
    public static int DRIVE_RIGHT_SLAVE = Constants.DRIVE_RIGHT_SLAVE_ID;
    public static int DRIVE_LEFT = Constants.DRIVE_LEFT_ID;
    public static int DRIVE_LEFT_SLAVE = Constants.DRIVE_LEFT_SLAVE_ID;

    public static int INTAKE_1 = Constants.INTAKE_1_ID;
    public static int INTAKE_2 = Constants.INTAKE_2_ID;
    public static int ARM = Constants.ARM_LIFT_ID;

    public static int PUSHER_SOLENOID;
    public static int LIFT_SOLENOID;
    public static int ARM_BOOST_SOLENOID;

    public static void Initialize(){
        in = new In();

        in.addProfile(ButtonProfiles.ROCKET_HATCH);
        in.addProfile(ButtonProfiles.ROCKET_PORT);
        in.addProfile(ButtonProfiles.PNEUMATICS);
        out = new Out();
        chassis = new Chassis(DRIVE_RIGHT, DRIVE_RIGHT_SLAVE, DRIVE_LEFT, DRIVE_LEFT_SLAVE);

        out.motors.add(DRIVE_RIGHT, true);
        out.motors.add(DRIVE_RIGHT_SLAVE, true);
        out.motors.add(DRIVE_LEFT, false);
        out.motors.add(DRIVE_LEFT_SLAVE, false);

        out.motors.add(INTAKE_1, true);
        out.motors.add(INTAKE_2, true);
        out.motors.add(ARM, false);

        PUSHER_SOLENOID = out.solenoids.add(Constants.PUSHER_SOL_1_ID, Constants.PUSHER_SOL_2_ID);
        LIFT_SOLENOID = out.solenoids.add(Constants.LIFT_SOL_1_ID, Constants.LIFT_SOL_2_ID);
        ARM_BOOST_SOLENOID = out.solenoids.add(Constants.ARM_BOOST_SOL_1_ID, Constants.ARM_BOOST_SOL_2_ID);

        DRIVER_LEFT_Y = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_LEFT_AXIS_ID));
        DRIVER_RIGHT_Y = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_RIGHT_AXIS_ID));
        DRIVER_SLOW = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_SLOW_AXIS_ID));
        DRIVER_FAST = in.add(new ControllerAxisSensor(driver, Constants.DRIVER_FAST_AXIS_ID));
        DRIVER_BALL_SEEK = in.add(new ControllerButtonSensor(driver, Constants.DRIVER_BALL_SEEK_BUTTON_ID));
        DRIVER_REVERSE_CONTROLS = in.add(new ControllerButtonSensor(driver, Constants.DRIVER_REVERSE_CONTROLS_ID));
        DRIVER_LIFT_UP = in.add(new ControllerButtonSensor(driver, Constants.DRIVER_LIFT_UP_ID));
        DRIVER_LIFT_MACRO = in.add(new ControllerButtonSensor(driver, Constants.DRIVER_LIFT_MACRO_ID));

        OPERATOR_ARM_REZERO = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_ARM_REZERO_ID));

        OPERATOR_INTAKE_IN = in.add(new ControllerAxisSensor(operator, Constants.OPERATOR_INTAKE_IN_ID));
        OPERATOR_INTAKE_OUT = in.add(new ControllerAxisSensor(operator, Constants.OPERATOR_INTAKE_OUT_ID));
        OPERATOR_ARM_HEIGHT = in.add(new ControllerAxisSensor(operator, Constants.OPERATOR_ARM_HEIGHT_ID));
        OPERATOR_PUSHER_OUT = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_PUSHER_OUT_ID, ButtonProfiles.PNEUMATICS, true));
        OPERATOR_CYCLE_PROFILE_DOWN = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_CYCLE_PROFILE_DOWN_ID));
        OPERATOR_CYCLE_PROFILE_UP = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_CYCLE_PROFILE_UP_ID));
        OPERATOR_ARM_BOOST_UP = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_ARM_BOOST_UP_ID, ButtonProfiles.PNEUMATICS));
        OPERATOR_ARM_BOOST_DOWN = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_ARM_BOOST_DOWN_ID, ButtonProfiles.PNEUMATICS));

        OPERATOR_ARM_LOW_HATCH = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_LOW_HEIGHT_ID, ButtonProfiles.ROCKET_HATCH));
        OPERATOR_ARM_MIDDLE_HATCH = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_MIDDLE_HEIGHT_ID, ButtonProfiles.ROCKET_HATCH));
        OPERATOR_ARM_HIGH_HATCH = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_HIGH_HEIGHT_ID, ButtonProfiles.ROCKET_HATCH));
        OPERATOR_ARM_LOW_PORT = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_LOW_HEIGHT_ID, ButtonProfiles.ROCKET_PORT));
        OPERATOR_ARM_MIDDLE_PORT = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_MIDDLE_HEIGHT_ID, ButtonProfiles.ROCKET_PORT));
        OPERATOR_ARM_HIGH_PORT = in.add(new ControllerButtonSensor(operator, Constants.OPERATOR_HIGH_HEIGHT_ID, ButtonProfiles.ROCKET_PORT));

        PIXY = in.add(new PixyBlocksSensor(Constants.PIXY_BALL_ID, 3));

        //GYRO = new ADXRS450_Gyro();

        LEFT_ENCODER = new Encoder(Constants.LEFT_ENCODER_1, Constants.LEFT_ENCODER_2, false);
        RIGHT_ENCODER = new Encoder(Constants.RIGHT_ENCODER_1, Constants.RIGHT_ENCODER_2, true);
        ARM_ENCODER = new Encoder(Constants.ARM_ENCODER_1, Constants.ARM_ENCODER_2, true);
        ARM_ENCODER.setDistancePerPulse(3f/5f);
    }
}