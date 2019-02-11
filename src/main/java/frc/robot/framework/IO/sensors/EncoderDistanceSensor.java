package frc.robot.framework.IO.sensors;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderDistanceSensor extends Sensor<Integer>{
    private Encoder encoder;

    public EncoderDistanceSensor(Encoder encoder){
        this.encoder = encoder;
    }

    public Integer get(){
        return encoder.get();
    }

    public void computeDeltas(){
        if(this.value != null){
            this.delta = this.get() - this.value;
        }
        this.value = this.get();
    }
}