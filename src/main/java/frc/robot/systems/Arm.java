package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.IO;
import frc.robot.framework.IO.PID.PIDController;

public class Arm extends Subsystem{
    private double setpoint;

    private double pGain = 1f/90f;
    private double iGain = 0;
    private double dGain = 0;

    private double lowLowerPoint = 0;
    private double middleLowerPoint = 0;
    private double highLowerPoint = 0;

    private double lowUpperPoint = 0;
    private double middleUpperPoint = 0;
    private double highUpperPoint = 0;

    private double startAngle = 0;
    private double gravityComp = 0.125;

    private double sensitivity = 2;

    private PIDController armPID = new PIDController(pGain, iGain, dGain);

    public Arm(){
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
        if((Boolean)IO.in.get(IO.OPERATOR_ARM_LOW_LOWER)){
            this.setSetpoint(this.lowLowerPoint);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_MIDDLE_LOWER)){
            this.setSetpoint(this.middleLowerPoint);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_HIGH_LOWER)){
            this.setSetpoint(this.highLowerPoint);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_LOW_UPPER)){
            this.setSetpoint(this.lowUpperPoint);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_MIDDLE_UPPER)){
            this.setSetpoint(this.middleUpperPoint);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_HIGH_UPPER)){
            this.setSetpoint(this.highUpperPoint);
        }else if(Math.abs((Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT)) > 0.1f){
            this.setSetpoint((Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT) * this.sensitivity);
        }

        if((Boolean)IO.in.get(IO.OPERATOR_ARM_BOOST_UP)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kForward);
        }else if((Boolean)IO.in.get(IO.OPERATOR_ARM_BOOST_DOWN)){
            IO.out.solenoids.set(IO.ARM_BOOST_SOLENOID, Value.kReverse);
        }

        double output = this.armPID.get(IO.ARM_ENCODER.getDistance()) + this.getFeedforward(IO.ARM_ENCODER.getDistance());
        IO.out.motors.set(IO.ARM, output);
    }

    public void reset(){

    }

    public void disable(){
        
    }

    private void setSetpoint(double setpoint){
        this.setpoint = setpoint;
        this.armPID.setSetpoint(setpoint);
    }

    private double getFeedforward(double angle){
        return Math.cos(Math.toRadians((angle + this.startAngle))) * this.gravityComp;
    }
}