package frc.robot.framework;

import java.util.ArrayList;
import java.util.List;
import frc.robot.framework.Subsystem;

public class Subsystems{
    public static List<Subsystem> subsystems = new ArrayList<Subsystem>();

    public static void add(Subsystem newSystem){
        subsystems.add(newSystem);
    }

    public static void teleopInit(){
        for(Subsystem subsystem : subsystems){
            subsystem.teleopInit();
        }
    }

    public static void teleopUpdate(){
        for(Subsystem subsystem : subsystems){
            subsystem.teleopUpdate();
        }
    }

    public static void autoInit(){
        for(Subsystem subsystem : subsystems){
            subsystem.autoInit();
        }
    }

    public static void autoUpdate(){
        for(Subsystem subsystem : subsystems){
            subsystem.autoUpdate();
        }
    }

    public static void resetAll(){
        for(Subsystem subsystem : subsystems){
            subsystem.reset();
        }
    }

    public static void disableAll(){
        for(Subsystem subsystem : subsystems){
            subsystem.disable();
        }
    }
}