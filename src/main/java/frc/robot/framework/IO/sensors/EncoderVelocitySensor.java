package frc.robot.framework.IO.sensors;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderVelocitySensor extends Sensor<Double>{
    private Encoder encoder;

    public EncoderVelocitySensor(Encoder encoder){
        this.encoder = encoder;
    }

    public Double get(){
        return encoder.getRate();
    }

    public void computeDeltas(){
        if(this.value != null){
            this.delta = this.get() - this.value;
        }
        this.value = this.get();
    }
}