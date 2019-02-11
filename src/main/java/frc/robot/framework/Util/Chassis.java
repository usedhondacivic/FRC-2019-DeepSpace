package frc.robot.framework.Util;

import frc.robot.IO;

public class Chassis{
    private int left;
    private int leftSlave;
    private int right;
    private int rightSlave;

    public Chassis(int right, int rightSlave, int left, int leftSlave){
        this.left = left;
        this.leftSlave = leftSlave;
        this.right = right;
        this.rightSlave = rightSlave;
    }

    public void drive(double leftSpeed, double rightSpeed){
        IO.out.motors.set(this.left, leftSpeed);
        IO.out.motors.set(this.leftSlave, leftSpeed);
        IO.out.motors.set(this.right, rightSpeed);
        IO.out.motors.set(this.rightSlave, rightSpeed);
    }
}