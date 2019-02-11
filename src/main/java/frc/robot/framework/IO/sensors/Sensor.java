package frc.robot.framework.IO.sensors;

public abstract class Sensor<T>{
    public T delta;
    public T value;

    public abstract T get();

    public abstract void computeDeltas();
}