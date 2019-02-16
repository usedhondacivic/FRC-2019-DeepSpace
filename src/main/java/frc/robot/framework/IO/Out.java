package frc.robot.framework.IO;

import edu.wpi.first.wpilibj.Compressor;
import frc.robot.framework.Dashboard;

public class Out{
    public Motors motors = new Motors();
    public Solenoids solenoids = new Solenoids();
    public Compressor compressor = new Compressor(0);
    public Dashboard dashboard = new Dashboard();

    public void update(){
        //this.motors.update();
        this.dashboard.update();
        this.compressor.setClosedLoopControl(true);
    }

    public void disable(){
        this.motors.disable();
        this.solenoids.disable();
        this.compressor.setClosedLoopControl(false);
    }
}