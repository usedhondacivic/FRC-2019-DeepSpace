package frc.robot;

import frc.robot.framework.Util.Buttons;

public class Constants {
    public static final int DRIVE_LEFT_ID = 0;
    public static final int DRIVE_LEFT_SLAVE_ID = 1;
    public static final int DRIVE_RIGHT_ID = 2;
    public static final int DRIVE_RIGHT_SLAVE_ID = 3;

    public static final int ARM_LIFT_ID = 4;
    public static final int INTAKE_1_ID = 5;
    public static final int INTAKE_2_ID = 6;

    public static final int LIFT_SOL_1_ID = 5;
    public static final int LIFT_SOL_2_ID = 4;
    public static final int PUSHER_SOL_1_ID = 0;
    public static final int PUSHER_SOL_2_ID = 1;
    public static final int ARM_BOOST_SOL_1_ID = 2;
    public static final int ARM_BOOST_SOL_2_ID = 3;

    public static final int DRIVER_LEFT_AXIS_ID = Buttons.LEFT_JOYSTICK_Y;
    public static final int DRIVER_RIGHT_AXIS_ID = Buttons.RIGHT_JOYSTICK_Y;
    public static final int DRIVER_SLOW_AXIS_ID = Buttons.LEFT_TRIGGER;
    public static final int DRIVER_FAST_AXIS_ID = Buttons.RIGHT_TRIGGER;

    public static final int DRIVER_BALL_SEEK_BUTTON_ID = Buttons.A;
    public static final int DRIVER_LIFT_BUTTON_ID = Buttons.RIGHT_SHOULDER;

    public static final int OPERATOR_INTAKE_IN_ID = Buttons.RIGHT_TRIGGER;
    public static final int OPERATOR_INTAKE_OUT_ID = Buttons.LEFT_TRIGGER;
    public static final int OPERATOR_ARM_HEIGHT_ID = Buttons.RIGHT_JOYSTICK_Y;
    public static final int OPERATOR_CYCLE_PROFILE_DOWN_ID = Buttons.LEFT_SHOULDER;
    public static final int OPERATOR_CYCLE_PROFILE_UP_ID = Buttons.RIGHT_SHOULDER;
    public static final int OPERATOR_PUSHER_OUT_ID = Buttons.X;
    public static final int OPERATOR_LIFT_UP_ID = Buttons.X;
    public static final int OPERATOR_ARM_BOOST_UP_ID = Buttons.Y;
    public static final int OPERATOR_ARM_BOOST_DOWN_ID = Buttons.A;

    public static final int OPERATOR_LOW_HEIGHT_ID = Buttons.A;
    public static final int OPERATOR_MIDDLE_HEIGHT_ID = Buttons.B;
    public static final int OPERATOR_HIGH_HEIGHT_ID = Buttons.Y;

    public static final int LEFT_ENCODER_1 = 0;
    public static final int LEFT_ENCODER_2 = 1;
    public static final int RIGHT_ENCODER_1 = 2;
    public static final int RIGHT_ENCODER_2 = 3;

    public static final int ARM_ENCODER_1 = 4;
    public static final int ARM_ENCODER_2 = 5;

    public static final int PIXY_BALL_ID = 1;
    public static final int PIXY_VISION_TARGET_ID = 2;
    public static final int PIXY_PORTAL_ID = 3;

    public static final double PIXY_BALL_PGAIN = 2f/260f;
}