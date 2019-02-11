package frc.robot.framework;

import frc.robot.framework.Util.SubsystemID;

public abstract class Subsystem{
    public SubsystemID id;

    public Subsystem(SubsystemID id){
        this.id = id;
    }

    public abstract void teleopInit();

    public abstract void teleopUpdate();

    public abstract void autoInit();

    public abstract void autoUpdate();

    public abstract void reset();

    public abstract void disable();
}