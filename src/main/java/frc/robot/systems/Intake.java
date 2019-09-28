package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import frc.robot.IO;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class Intake extends Subsystem{
    public boolean autoControlled = false;
    public boolean forceOpen = false;
    public boolean forceClosed = false;

    public Intake(){
        super(SubsystemID.INTAKE);
    }

    public void autoInit(){

    }

    public void autoUpdate(){
        this.teleopUpdate();
    }

    public void teleopInit(){

    }

    public void teleopUpdate(){
        if((Double)IO.in.get(IO.OPERATOR_INTAKE_IN) < -0.5){
            IO.out.motors.set(IO.INTAKE_1, -1);
            IO.out.motors.set(IO.INTAKE_2, -1);
        }else if((Double)IO.in.get(IO.OPERATOR_INTAKE_OUT) < -0.5){
            IO.out.motors.set(IO.INTAKE_1, 1);
            IO.out.motors.set(IO.INTAKE_2, 1);
        }else if((Boolean)IO.in.get(IO.DRIVER_BALL_SEEK)){
            IO.out.motors.set(IO.INTAKE_1, -1);
            IO.out.motors.set(IO.INTAKE_2, -1);
        }else{
            IO.out.motors.set(IO.INTAKE_1, 0);
            IO.out.motors.set(IO.INTAKE_2, 0);
        }
        
        if(autoControlled){
            if(forceOpen){
                IO.out.solenoids.set(IO.PUSHER_SOLENOID, Value.kForward);
                System.out.println("Open");
            }else if(forceClosed){
                IO.out.solenoids.set(IO.PUSHER_SOLENOID, Value.kReverse);
                System.out.println("Closed");
            }
        }
        else{
            if(((Boolean)IO.in.get(IO.OPERATOR_PUSHER_OUT) || forceOpen) && !forceClosed){
                IO.out.solenoids.set(IO.PUSHER_SOLENOID, Value.kForward);
                System.out.println("Open");
            }else{
                IO.out.solenoids.set(IO.PUSHER_SOLENOID, Value.kReverse);
                System.out.println("Closed");
            }
        }

        forceOpen = false;
        forceClosed = false;
    }

    public void reset(){

    }

    public void disable(){
        
    }
}