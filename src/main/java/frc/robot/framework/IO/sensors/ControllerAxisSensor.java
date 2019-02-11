package frc.robot.framework.IO.sensors;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerAxisSensor extends Sensor<Double>{
    private Joystick controller;
    private int buttonID;

    public ControllerAxisSensor(Joystick joystick, int port){
        this.controller = joystick;
        this.buttonID = port;
    }

    public Double get(){
        return -this.controller.getRawAxis(this.buttonID);
    }

    public void computeDeltas(){
        if(this.value != null){
            this.delta = this.get() - this.value;
        }
        this.value = this.get();
    }
}