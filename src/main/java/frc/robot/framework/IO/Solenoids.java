package frc.robot.framework.IO;

import edu.wpi.first.wpilibj.Solenoid;
import java.util.HashMap;
import java.util.Map;

public class Solenoids{
    private Map<Integer, SolenoidWrapper> solenoids = new HashMap<>();

    private class SolenoidWrapper{
        public Solenoid sol;

        public SolenoidWrapper(int id) {
            this.sol = new Solenoid(id);
        }

        public void set(boolean output){
            this.sol.set(output);
        }
    }

    public void add(int id){
        solenoids.put(id, new SolenoidWrapper(id));
    }

	public Solenoid get(int id) {
		return solenoids.get(id).sol;
	}
	
	public void set(int id, boolean output) {
		solenoids.get(id).set(output);
	}	
	
	public void disable() {
		for(SolenoidWrapper sol : this.solenoids.values()) {
			sol.set(false);
		}
	}
}