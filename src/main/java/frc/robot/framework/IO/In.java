package frc.robot.framework.IO;

import java.util.List;
import java.util.ArrayList;

import frc.robot.ButtonProfiles;
import frc.robot.framework.IO.sensors.Sensor;

public class In{
    private List<Sensor<?>> sensors = new ArrayList<>();
    private List<Enum<?>> buttonProfiles = new ArrayList<>();
    private int currentProfile;

    public int add(Sensor<?> sensor){
        this.sensors.add(sensor);
        return sensors.size() - 1;
    }

    public <T> T get(int id){
        return (T)sensors.get(id).get();
    }

    public <T> T getDelta(int id){
        return (T)sensors.get(id).delta;
    }

    public void update(){
        for(Sensor<?> sensor : this.sensors){
            sensor.computeDeltas();
        }
    }

    public void addProfile(Enum<?> profile){
        buttonProfiles.add(profile);
        if(buttonProfiles.size() == 0){
            currentProfile = 0;
        }
    }

    public void incrementProfile(){
        this.currentProfile++;
        if(this.currentProfile >= this.buttonProfiles.size()){
            this.currentProfile = 0;
        }
    }

    public void decrementProfile(){
        this.currentProfile--;
        if(this.currentProfile < 0){
            this.currentProfile = this.buttonProfiles.size()-1;
        }
    }

    public Enum<?> getProfile(){
        return this.buttonProfiles.get(this.currentProfile);
    }
}