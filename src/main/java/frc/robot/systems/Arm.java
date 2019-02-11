package frc.robot.systems;

import frc.robot.framework.Subsystem;
import frc.robot.framework.Util.SubsystemID;
import frc.robot.IO;
import frc.robot.framework.IO.PID.PIDController;

public class Arm extends Subsystem{
    private double setpoint;

    private double pGain = 0;
    private double iGain = 0;
    private double dGain = 0;

    private double sensitivity = 2;

    private PIDController armPID = new PIDController(1f/90f, 0, 0);

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
        if((Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT) != 0){
            this.setSetpoint((Double)IO.in.get(IO.OPERATOR_ARM_HEIGHT) * this.sensitivity);
        }
        double output = this.armPID.get(IO.ARM_ENCODER.getDistance());
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
}