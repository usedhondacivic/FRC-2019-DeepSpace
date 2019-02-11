package frc.robot.framework.IO;

import java.util.List;
import java.util.ArrayList;
import frc.robot.framework.IO.sensors.Sensor;

public class In{
    private List<Sensor<?>> sensors = new ArrayList<>();

    public int add(Sensor<?> sensor){
        this.sensors.add(sensor);
        return sensors.size() - 1;
    }

    public <T> T get(int id){
        return (T)sensors.get(id).get();
    }

    public void update(){
        for(Sensor<?> sensor : this.sensors){
            sensor.computeDeltas();
        }
    }
}