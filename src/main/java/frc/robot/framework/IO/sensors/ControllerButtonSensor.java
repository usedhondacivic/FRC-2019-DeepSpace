package frc.robot.framework.IO.sensors;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerButtonSensor extends Sensor<Boolean>{
    private Joystick controller;
    private int buttonID;

    public ControllerButtonSensor(Joystick joystick, int port){
        this.controller = joystick;
        this.buttonID = port;
    }

    public Boolean get(){
        return this.controller.getRawButton(this.buttonID);
    }

    public void computeDeltas(){
        if(this.value != null){
            this.delta = (this.get() != this.value);
        }
        this.value = this.get();
    }
}