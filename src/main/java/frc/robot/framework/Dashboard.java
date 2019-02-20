package frc.robot.framework;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import java.util.ArrayList;
import frc.robot.IO;
import frc.robot.Robot;

public class Dashboard{
    private UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    private SendableChooser autoChooser = new SendableChooser<String>();
    private SendableChooser delayChooser = new SendableChooser<Integer>();

    public Dashboard(){
        autoChooser.setDefaultOption("Default", "default");

        delayChooser.setDefaultOption("1 second", 1);
        delayChooser.setDefaultOption("5 second", 5);
        delayChooser.setDefaultOption("10 second", 10);
        delayChooser.setDefaultOption("15 second", 15);

        SmartDashboard.putBoolean("Ball detected", false);
        SmartDashboard.putNumber("Right encoder", 0);
        SmartDashboard.putNumber("Left encoder", 0);
        SmartDashboard.putNumber("Arm encoder", 0);

        SmartDashboard.putNumber("Camera exposure", 50);

        SmartDashboard.putString("Button profile", "");
        
        camera1.setExposureManual((int)SmartDashboard.getNumber("Camera exposure", 100));
        camera1.setResolution(500, 375);
        camera1.setFPS(30);
    }

    public void update(){
        ArrayList<Block> blocks = (ArrayList<Block>)IO.in.get(IO.PIXY);
        if(blocks.size() > 0){
            SmartDashboard.putBoolean("Ball detected", false);
        }

        SmartDashboard.putNumber("Right encoder", IO.RIGHT_ENCODER.getDistance());
        SmartDashboard.putNumber("Left encoder", IO.LEFT_ENCODER.getDistance());
        SmartDashboard.putNumber("Arm encoder", IO.ARM_ENCODER.getDistance() + Robot.arm.startAngle);

        SmartDashboard.putString("Button profile", IO.in.getProfile().toString());
    }

    public String getAuto(){
        return (String) autoChooser.getSelected();
    }
}