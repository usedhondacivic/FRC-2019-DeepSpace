package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drive extends Subsystem{

    private double outputLeft = 0;
    private double outputRight = 0;
    private double pGain = 0.3f;

    private double slowSpeed = 0.70;
    private double mediumSpeed = 0.9;
    private double fastSpeed = 1;

    private Notifier liftMacroNotifier;
    private double liftMacroStart;

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
                IO.chassis.arcadeDrive(1, error*Constants.PIXY_BALL_PGAIN);
            }else{
                this.manualDrive();
            }
        }else if((Boolean)IO.in.get(IO.DRIVER_LIFT_MACRO)){
            if((Boolean)IO.in.getDelta(IO.DRIVER_LIFT_MACRO)){
                this.liftMacroNotifier = new Notifier(this::liftMacro);
                this.liftMacroNotifier.startPeriodic(1f/50f);
                this.liftMacroStart = System.currentTimeMillis();
                Robot.robotLift.autoControlled = true;
            }
        }else if((Boolean)IO.in.getDelta(IO.DRIVER_LIFT_MACRO)){
            this.liftMacroNotifier.stop();
            Robot.robotLift.autoControlled = false;
        }else{
            this.manualDrive();
        }
    }
    
    private void liftMacro(){
        double delta = System.currentTimeMillis() - this.liftMacroStart;
        if(delta < 150){
            IO.out.solenoids.set(IO.LIFT_SOLENOID, Value.kForward);
        }else if(delta < 600){
            IO.chassis.drive(-this.mediumSpeed, -this.mediumSpeed);
        }else if(delta < 1100){
            IO.out.solenoids.set(IO.LIFT_SOLENOID, Value.kReverse);
        }else if(delta < 1250){
            IO.chassis.drive(-this.mediumSpeed, -this.mediumSpeed);
        }
    }

    private void manualDrive(){
        double leftSpeed = 0;
        double rightSpeed = 0;
        if((Double)IO.in.get(IO.DRIVER_FAST) < -0.5){
            leftSpeed = (Double)IO.in.get(IO.DRIVER_LEFT_Y)*this.fastSpeed;
            rightSpeed = (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*this.fastSpeed;
        }else{
            leftSpeed = (Double)IO.in.get(IO.DRIVER_LEFT_Y)*this.mediumSpeed;
            rightSpeed = (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*this.mediumSpeed;
        }
        
        this.outputLeft += (leftSpeed - this.outputLeft) * this.pGain;
        this.outputRight += (rightSpeed - this.outputRight) * this.pGain;

        if((Double)IO.in.get(IO.DRIVER_SLOW) < -0.5){
            this.outputLeft = (Double)IO.in.get(IO.DRIVER_LEFT_Y)*this.slowSpeed;
            this.outputRight = (Double)IO.in.get(IO.DRIVER_RIGHT_Y)*this.slowSpeed;
        }
        IO.chassis.drive(this.outputLeft, this.outputRight);
    }

    public void reset(){

    }

    public void disable(){
        
    }
}