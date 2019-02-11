package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import java.util.ArrayList;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.IO;

public class Drive extends Subsystem{
    public Drive(){
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
        if((Boolean)IO.in.get(IO.DRIVER_BALL_SEEK)){
            ArrayList<Block> blocks = (ArrayList<Block>)IO.in.get(IO.PIXY);
            if(blocks.size() > 0){
                Block ball = blocks.get(0);
                int width = 316;
                int error = ball.getX() - width/2;
                IO.chassis.arcadeDrive(0.8, error*Constants.PIXY_BALL_PGAIN);
            }else{
                this.manualDrive();
            }
        }else{
            this.manualDrive();
        }
    }

    private void manualDrive(){
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