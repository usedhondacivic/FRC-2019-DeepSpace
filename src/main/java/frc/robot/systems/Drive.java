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

    private double slowSpeed = 0.50;
    private double mediumSpeed = 0.9;
    private double fastSpeed = 1;

    private Notifier liftMacroNotifier;
    private double liftMacroStart;

    private Notifier placeMacroNotifier;
    private double placeMacroStart;
    
    private Notifier pickupMacroNotifier;
    private double pickupMacroStart;

    public Drive(){
        super(SubsystemID.DRIVE);
    }

    public void autoInit(){
        IO.RIGHT_ENCODER.reset();
        IO.LEFT_ENCODER.reset();
    }

    public void autoUpdate(){
        this.teleopUpdate();
    }

    public void teleopInit(){
        IO.RIGHT_ENCODER.reset();
        IO.LEFT_ENCODER.reset();
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
        }
        else if((Boolean)IO.in.get(IO.DRIVER_LIFT_MACRO)){
            if((Boolean)IO.in.getDelta(IO.DRIVER_LIFT_MACRO)){
                this.liftMacroNotifier = new Notifier(this::liftMacro);
                this.liftMacroNotifier.startPeriodic(1f/50f);
                this.liftMacroStart = System.currentTimeMillis();
                Robot.robotLift.autoControlled = true;
            }
        }else if((Boolean)IO.in.getDelta(IO.DRIVER_LIFT_MACRO)){
            this.liftMacroNotifier.stop();
            Robot.robotLift.autoControlled = false;
        }
        else if((Boolean)IO.in.get(IO.DRIVER_PLACE_MACRO)){
            if((Boolean)IO.in.getDelta(IO.DRIVER_PLACE_MACRO)){
                this.placeMacroNotifier = new Notifier(this::hatchPlaceMacro);
                this.placeMacroNotifier.startPeriodic(1f/50f);
                this.placeMacroStart = System.currentTimeMillis();
                Robot.intake.autoControlled = true;
            }
        }else if((Boolean)IO.in.getDelta(IO.DRIVER_PLACE_MACRO)){
            this.placeMacroNotifier.stop();
            Robot.intake.autoControlled = false;
        }
        else if((Boolean)IO.in.get(IO.DRIVER_PICKUP_MACRO)){
            if((Boolean)IO.in.getDelta(IO.DRIVER_PICKUP_MACRO)){
                this.pickupMacroNotifier = new Notifier(this::hatchPickupMacro);
                this.pickupMacroNotifier.startPeriodic(1f/50f);
                this.pickupMacroStart = System.currentTimeMillis();
                Robot.intake.autoControlled = true;
            }
        }else if((Boolean)IO.in.getDelta(IO.DRIVER_PICKUP_MACRO)){
            this.pickupMacroNotifier.stop();
            Robot.intake.autoControlled = false;
        }
        else{
            this.manualDrive();
        }
    }
    
    private void liftMacro(){
        double delta = System.currentTimeMillis() - this.liftMacroStart;
        Robot.arm.setSetpoint(51);
        
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

    private void hatchPlaceMacro(){
        double delta = System.currentTimeMillis() - this.placeMacroStart;
        Robot.intake.forceOpen = false;
        Robot.intake.forceClosed = false;

        if(delta < 1000){
            IO.chassis.drive(this.slowSpeed, this.slowSpeed);
        }else if(delta < 1500){
            Robot.intake.forceOpen = true;
            IO.chassis.drive(0, 0);
        }else if(delta < 2500){
            IO.chassis.drive(-this.slowSpeed, -this.slowSpeed);
            Robot.intake.forceOpen = true;
        }else{
            IO.chassis.drive(0, 0);
        }
    }

    private void hatchPickupMacro(){
        double delta = System.currentTimeMillis() - this.pickupMacroStart;
        Robot.intake.forceOpen = false;
        Robot.intake.forceClosed = false;

        if(delta <200){
            Robot.intake.forceOpen = true;
        }else if(delta < 1000){
            Robot.intake.forceOpen = true;
            IO.chassis.drive(this.slowSpeed, this.slowSpeed);
        }else if(delta < 1500){
            Robot.intake.forceClosed = true;
            IO.chassis.drive(0, 0);
        }else if(delta < 2500){
            IO.chassis.drive(-this.slowSpeed, -this.slowSpeed);
        }else{
            IO.chassis.drive(0, 0);
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

        if((Boolean)IO.in.get(IO.DRIVER_REVERSE_CONTROLS)){
            this.outputLeft *= -1;
            this.outputRight *= -1;
            double tempLeft = this.outputLeft;
            this.outputLeft = this.outputRight;
            this.outputRight = tempLeft;
        }

        IO.chassis.drive(this.outputLeft, this.outputRight);
    }

    public void reset(){

    }

    public void disable(){
        IO.RIGHT_ENCODER.reset();
        IO.LEFT_ENCODER.reset();
    }
}