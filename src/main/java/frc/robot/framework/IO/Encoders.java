package frc.robot.framework.IO;

import java.util.HashMap;
import java.util.Map;
import edu.wpi.first.wpilibj.Encoder;

public class Encoders{
    private Map<Integer, EncoderWrapper> encoders = new HashMap<>();
    private class EncoderWrapper{
        private Encoder encoder;
    
        public EncoderWrapper(int portOne, int portTwo, boolean reverse){
            this.encoder = new Encoder(portOne, portTwo, reverse);
        }
    
        public double getDistance(){
            return this.encoder.getDistance();
        }
    
        public double getVelocity(){
            return this.encoder.getRate();
        }

        public void reset(){
            this.encoder.reset();
        }
    }

    public void add(int id, int portOne, int portTwo, boolean reverse){
        encoders.put(id, new EncoderWrapper(portOne, portTwo, reverse));
    }

	public Encoder get(int id) {
		return encoders.get(id).encoder;
    }
    
    public double getDistance(int id) {
        return encoders.get(id).getDistance();
    }

    public double getVelocity(int id) {
        return encoders.get(id).getVelocity();
    }

	public void disable() {
		for(EncoderWrapper encoder : this.encoders.values()) {
			encoder.reset();
		}
	}
}