package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ButtonProfiles;
import frc.robot.IO;
import frc.robot.framework.IO.PID.PIDController;

public class Arm extends Subsystem{
    public double setpoint;

    private double pGain = 1/18f;//1/25, 1/15 
    private double iGain = 0f;//0.0005
    private double dGain = 0.20f;//0.25, 0.2, 0.45

    private double lowHatchAngle = 39.5f;
    private double middleHatchAngle = 86.4f;
    private double highHatchAngle = 138;

    private double lowPortAngle = 70.625;
    private double middlePortAngle = 116.97;
    private double highPortAngle = 165;

    private double maxAngle = 152;

    public double driveHoldAngle = 35;
    public double ballGrabAngle = 28;

    public double startAngle = 28;
    private double gravityComp = 0.45;

    private double sensitivity = 2;

    private PIDController armPID = new PIDController(pGain, iGain, dGain);
    private boolean zerodInAuto = false;

    public Arm(){
        super(SubsystemID.ARM);
    }

    public void autoInit(){
        IO.ARM_ENCODER.reset();
        this.setSetpoint(this.startAngle);
        zerodInAuto = true;
    }

    public void autoUpdate(){
        this.teleopUpdate();
    }

    public void teleopInit(){
        if(!this.zerodInAuto){
            IO.ARM_ENCODER.reset();
            this.setSetpoint(this.startAngle);
        }
    }

    public void teleopUpdate(){
        if((Boolean)IO.in.get(IO.OPERATOR_ARM_LOW_HATCH)){
            this.setSetpoint(this.lowHatchAngle);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_MIDDLE_HATCH)){
            this.setSetpoint(this.middleHatchAngle);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_HIGH_HATCH)){
            this.setSetpoint(this.highHatchAngle);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_LOW_PORT)){
            this.setSetpoint(this.lowPortAngle);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_MIDDLE_PORT)){
            this.setSetpoint(this.middlePortAngle);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_HIGH_PORT)){
            this.setSetpoint(this.highPortAngle);
        }else if(Math.abs((Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT)) > 0.1f){
            this.setpoint += (Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT) * this.sensitivity;
            this.setSetpoint(this.setpoint);
        }else if(((Double)IO.in.get(IO.OPERATOR_INTAKE_IN) < -0.5 || (Boolean)IO.in.get(IO.DRIVER_BALL_SEEK)) && this.setpoint <= this.lowPortAngle){
            this.setSetpoint(this.ballGrabAngle);
        }else if(this.setpoint == this.ballGrabAngle){
            this.setSetpoint(this.driveHoldAngle);
        }

        if((Boolean)IO.in.get(IO.OPERATOR_ARM_BOOST_UP)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kForward);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_BOOST_DOWN)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kReverse);
        }else if(this.setpoint > this.maxAngle + 10 && (IO.ARM_ENCODER.getDistance() + this.startAngle) >= (this.maxAngle - 10f)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kForward);
        }else{
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kReverse);
        }

        System.out.println("Set: "+this.setpoint+", Encoder: "+ (IO.ARM_ENCODER.getDistance() + this.startAngle) +", Max: "+this.maxAngle+", Bool: "+( (IO.ARM_ENCODER.getDistance() + this.startAngle) >= (this.maxAngle - 10f)));

        if((Boolean)IO.in.get(IO.OPERATOR_ARM_REZERO)){
            this.setSetpoint(this.setpoint-3, false);
        }else if((Boolean)IO.in.getDelta(IO.OPERATOR_ARM_REZERO)){
            IO.ARM_ENCODER.reset();
            this.setSetpoint(this.startAngle);
        }

        double output = this.armPID.get(IO.ARM_ENCODER.getDistance()) + this.getFeedforward(IO.ARM_ENCODER.getDistance());
        //double output = (Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT) + this.getFeedforward(IO.ARM_ENCODER.getDistance());
        output = Math.max( -0.2f, Math.min(output, 0.9f));
        //output = (Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT) + this.getFeedforward(IO.ARM_ENCODER.getDistance());
        IO.out.motors.set(IO.ARM, output);
        SmartDashboard.putNumber("Arm setpoint", this.setpoint);
        SmartDashboard.putNumber("Arm output", output);
        SmartDashboard.putNumber("Controller output", (Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT));
    }

    public void reset(){

    }

    public void disable(){
        zerodInAuto = false;
    }

    public void setSetpoint(double setpoint){
        this.setpoint = setpoint;
        setpoint = Math.max(this.startAngle, Math.min(setpoint, this.maxAngle));
        this.armPID.setSetpoint(setpoint - this.startAngle);
    }

    public void setSetpoint(double setpoint, boolean safe){
        if(safe){
            setSetpoint(setpoint);
            return;
        }
        this.setpoint = setpoint;
        this.armPID.setSetpoint(setpoint - this.startAngle);
    }

    private double getFeedforward(double angle){
        return Math.sin(Math.toRadians(angle + this.startAngle)) * this.gravityComp;
    }
}