package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.IO;
import frc.robot.framework.IO.PID.PIDController;

public class Arm extends Subsystem{
    public double setpoint;

    private double pGain = 1f/25f;//1/15
    private double iGain = 0;
    private double dGain = 0.20f;//0.45

    private double lowHatchAngle = 51;
    private double middleHatchAngle = 100;
    private double highHatchAngle = 180;

    private double lowPortAngle = 74;
    private double middlePortAngle = 117;
    private double highPortAngle = 180;

    private double maxAngle = 163;

    public double driveHoldAngle = 35;
    public double ballGrabAngle = 27;

    public double startAngle = 28;
    private double gravityComp = 0.41;

    private double sensitivity = 2;

    private PIDController armPID = new PIDController(pGain, iGain, dGain);

    public Arm(){
        super(SubsystemID.ARM);
    }

    public void autoInit(){

    }

    public void autoUpdate(){
        //this.teleopUpdate();
    }

    public void teleopInit(){
        IO.ARM_ENCODER.reset();
        this.setSetpoint(27);
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
        }else if((Double)IO.in.get(IO.OPERATOR_INTAKE_IN) < -0.5){
            this.setSetpoint(this.ballGrabAngle);
        }else if(this.setpoint == this.ballGrabAngle){
            this.setSetpoint(this.driveHoldAngle);
        }

        if((Boolean)IO.in.get(IO.OPERATOR_ARM_BOOST_UP)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kForward);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_BOOST_DOWN)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kReverse);
        }else if(this.setpoint > this.maxAngle + 10){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kForward);
        }else{
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kReverse);
        }

        double output = this.armPID.get(IO.ARM_ENCODER.getDistance()) + this.getFeedforward(IO.ARM_ENCODER.getDistance());
        output = Math.min(output, 0.9);
        IO.out.motors.set(IO.ARM, output);
        SmartDashboard.putNumber("Arm setpoint", this.setpoint);
        SmartDashboard.putNumber("Arm output", output);
        SmartDashboard.putNumber("Controller output", (Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT));
    }

    public void reset(){

    }

    public void disable(){
        
    }

    public void setSetpoint(double setpoint){
        this.setpoint = setpoint;
        setpoint = Math.max(this.startAngle, Math.min(setpoint, this.maxAngle));
        this.armPID.setSetpoint(setpoint - this.startAngle);
    }

    private double getFeedforward(double angle){
        return Math.sin(Math.toRadians(angle + this.startAngle)) * this.gravityComp;
    }
}