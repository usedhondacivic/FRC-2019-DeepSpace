package frc.robot.framework.IO;

import edu.wpi.first.wpilibj.Spark;
import java.util.HashMap;
import java.util.Map;

public class Motors{
    private Map<Integer, MotorWrapper> motors = new HashMap<>();

    private class MotorWrapper{
        public Spark motor;
        public double output;

        public MotorWrapper(int id, boolean reverse) {
            this.motor = new Spark(id);
            this.motor.setInverted(reverse);
        }

        public void set(double output){
            this.motor.set(output);
            this.output = output;
        }

        public void zero(){
            this.output = 0;
            this.motor.set(this.output);
        }
    }

    public void add(int id, boolean reversed){
        motors.put(id, new MotorWrapper(id, reversed));
    }

	public Spark get(int id) {
		return motors.get(id).motor;
	}
	
	public void set(int id, double output) {
		motors.get(id).set(output);
	}	
	
	public void update() {
		for(MotorWrapper motor : this.motors.values()) {
			motor.zero();
		}
	}
	
	public void disable() {
		for(MotorWrapper motor : this.motors.values()) {
			motor.set(0.0);
		}
	}
}