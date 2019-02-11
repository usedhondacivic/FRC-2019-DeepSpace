package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import frc.robot.IO;

public class Intake extends Subsystem{
    public Intake(){
        super(SubsystemID.ARM);
    }

    public void autoInit(){

    }

    public void autoUpdate(){
        this.teleopUpdate();
    }

    public void teleopInit(){

    }

    public void teleopUpdate(){
        if((Boolean)IO.in.get(IO.OPERATOR_INTAKE_IN)){
            IO.out.motors.set(IO.INTAKE, -1);
        }else if((Boolean)IO.in.get(IO.OPERATOR_INTAKE_OUT)){
            IO.out.motors.set(IO.INTAKE, 1);
        }else if((Boolean)IO.in.get(IO.DRIVER_BALL_SEEK)){
            IO.out.motors.set(IO.INTAKE, -1);
        }

        if((Boolean)IO.in.get(IO.OPERATOR_PUSHER_OUT)){
            IO.out.solenoids.set(IO.PUSHER_SOLENOID, true);
        }else if((Boolean)IO.in.get(IO.OPERATOR_PUSHER_OUT)){
            IO.out.solenoids.set(IO.PUSHER_SOLENOID, false);
        }
    }

    public void reset(){

    }

    public void disable(){
        
    }
}