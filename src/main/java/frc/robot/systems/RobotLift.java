package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import frc.robot.IO;

public class RobotLift extends Subsystem{
    public RobotLift(){
        super(SubsystemID.ROBOT_LIFT);
    }

    public void autoInit(){

    }

    public void autoUpdate(){

    }

    public void teleopInit(){

    }

    public void teleopUpdate(){
        if((Boolean)IO.in.get(IO.OPERATOR_LIFT_UP)){
            IO.out.solenoids.set(IO.LIFT_SOLENOID, true);
        }else if((Boolean)IO.in.get(IO.OPERATOR_LIFT_DOWN)){
            IO.out.solenoids.set(IO.LIFT_SOLENOID, false);
        }
    }

    public void reset(){

    }

    public void disable(){
        
    }
}