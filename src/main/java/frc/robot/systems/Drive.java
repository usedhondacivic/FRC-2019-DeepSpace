package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import frc.robot.IO;

public class Drive extends Subsystem{
    public Drive(){
        super(SubsystemID.DRIVE);
    }

    public void autoInit(){

    }

    public void autoUpdate(){
        System.out.println("Right: "+IO.ENCODER_RIGHT.get()+" Left: "+IO.ENCODER_LEFT.get()+" Gyro: "+IO.GYRO.getAngle());
    }

    public void teleopInit(){

    }

    public void teleopUpdate(){
        if((Double)IO.in.get(IO.DRIVER_SLOW) < -0.5){
            IO.chassis.drive((Double)IO.in.get(IO.DRIVER_LEFT_Y)*0.65, (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*0.65);
        }else if((Double)IO.in.get(IO.DRIVER_FAST) < -0.5){
            IO.chassis.drive(IO.in.get(IO.DRIVER_LEFT_Y), IO.in.get(IO.DRIVER_RIGHT_Y));
        }else{
            IO.chassis.drive((Double)IO.in.get(IO.DRIVER_LEFT_Y)*0.85, (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*0.85);
        }
    }

    public void reset(){

    }

    public void disable(){
        
    }
}