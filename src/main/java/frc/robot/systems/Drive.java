package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import java.util.ArrayList;
import frc.robot.framework.IO.PID.PIDController;

import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.Robot;

public class Drive extends Subsystem{

    private double outputLeft = 0;
    private double outputRight = 0;
    private double pGain = 0.3f;

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
            System.out.println(blocks.size());
            if(blocks.size() > 0){
                Block ball = blocks.get(0);
                int width = 316;
                int error = ball.getX() - width/2;
                IO.chassis.arcadeDrive(1, error*Constants.PIXY_BALL_PGAIN);
            }else{
                this.manualDrive();
            }
        }else{
            this.manualDrive();
        }
    }

    private void manualDrive(){
        double leftSpeed = 0;
        double rightSpeed = 0;
        if((Double)IO.in.get(IO.DRIVER_SLOW) < -0.5){
            leftSpeed = (Double)IO.in.get(IO.DRIVER_LEFT_Y)*0.75;
            rightSpeed = (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*0.75;
        }else if((Double)IO.in.get(IO.DRIVER_FAST) < -0.5){
            leftSpeed = (Double)IO.in.get(IO.DRIVER_LEFT_Y);
            rightSpeed = (Double)IO.in.get(IO.DRIVER_RIGHT_Y);
        }else{
            leftSpeed = (Double)IO.in.get(IO.DRIVER_LEFT_Y)*0.90;
            rightSpeed = (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*0.90;
        }
        //IO.chassis.drive(leftSidePID.get(lastLeft), rightSidePID.get(lastRight));
        this.outputLeft += (leftSpeed - this.outputLeft) * this.pGain;
        this.outputRight += (rightSpeed - this.outputRight) * this.pGain;
        IO.chassis.drive(this.outputLeft, this.outputRight);
    }

    public void reset(){

    }

    public void disable(){
        
    }
}