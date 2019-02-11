package frc.robot.framework.IO;

public class Out{
    public Motors motors = new Motors();
    
    public void update(){
        this.motors.update();
    }

    public void disable(){
        this.motors.disable();
    }
}