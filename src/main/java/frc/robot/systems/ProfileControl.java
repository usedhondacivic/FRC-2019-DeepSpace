package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import frc.robot.IO;

public class ProfileControl extends Subsystem{
    public ProfileControl(){
        super(SubsystemID.DRIVE);
    }

    public void autoInit(){

    }

    public void autoUpdate(){
        this.teleopUpdate();
    }

    public void teleopInit(){

    }

    public void teleopUpdate(){
        if((Boolean)IO.in.get(IO.OPERATOR_CYCLE_PROFILE_UP) && (Boolean)IO.in.getDelta(IO.OPERATOR_CYCLE_PROFILE_UP)){
            IO.in.incrementProfile();
        }else if((Boolean)IO.in.get(IO.OPERATOR_CYCLE_PROFILE_DOWN) && (Boolean)IO.in.getDelta(IO.OPERATOR_CYCLE_PROFILE_DOWN)){
            IO.in.decrementProfile();
        }
    }

    public void reset(){

    }

    public void disable(){
        
    }
}