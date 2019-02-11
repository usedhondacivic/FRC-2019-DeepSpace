package frc.robot.framework.IO;

import frc.robot.framework.Dashboard;

public class Out{
    public Motors motors = new Motors();
    public Solenoids solenoids = new Solenoids();
    public Dashboard dashboard = new Dashboard();
    
    public void update(){
        this.motors.update();
        this.dashboard.update();
    }

    public void disable(){
        this.motors.disable();
        this.solenoids.disable();
    }
}