package frc.robot.framework.IO.PID;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDController{
    private double pGain;
    private double iGain;
    private double dGain;

    private double accumulatedError = 0;
    private double lastTime = 0;
    private double lastError = 0;

    private double setpoint = 0;

    public PIDController(double p, double i, double d){
        this.pGain = p;
        this.iGain = i;
        this.dGain = d;
    }

    public void setSetpoint(double setpoint){
        this.accumulatedError = 0;
        this.lastTime = 0;
        this.lastError = 0;
        this.setpoint = setpoint;
    }

    public double get(double mesured){
        double error = this.setpoint - mesured;
        double derivative = 0;
        if(this.lastTime != 0){
            derivative = (error - this.lastError) / (System.currentTimeMillis() - this.lastTime);
        }
        this.lastTime = System.currentTimeMillis();
        this.accumulatedError += error;
        SmartDashboard.putNumber("I gain", this.accumulatedError * this.iGain);
        SmartDashboard.putNumber("D gain", derivative * this.dGain);
        return error * this.pGain + this.accumulatedError * this.iGain + derivative * this.dGain;
    }
}