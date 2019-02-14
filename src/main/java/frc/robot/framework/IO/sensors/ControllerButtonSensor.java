package frc.robot.framework.IO.sensors;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.IO;

public class ControllerButtonSensor extends Sensor<Boolean>{
    private Joystick controller;
    private int buttonID;
    private Enum<?> profile;
    private boolean global = true;
    private boolean invertSelection = false;

    public ControllerButtonSensor(Joystick joystick, int port){
        this.controller = joystick;
        this.buttonID = port;
    }

    public ControllerButtonSensor(Joystick joystick, int port, Enum<?> profile){
        this.controller = joystick;
        this.buttonID = port;
        this.profile = profile;
        this.global = false;
    }

    public ControllerButtonSensor(Joystick joystick, int port, Enum<?> profile, Boolean allBut){
        this.controller = joystick;
        this.buttonID = port;
        this.profile = profile;
        this.global = false;
        this.invertSelection = true;
    }

    public Boolean get(){
        if(this.global){
            return this.controller.getRawButton(this.buttonID);
        }else if(!this.invertSelection){
            return (this.controller.getRawButton(this.buttonID) && (this.profile == IO.in.getProfile()));
        }else{
            return (this.controller.getRawButton(this.buttonID) && (this.profile != IO.in.getProfile()));
        }
    }

    public void computeDeltas(){
        if(this.value != null){
            this.delta = (this.get() != this.value);
        }
        this.value = this.get();
    }
}