package frc.robot.framework.IO;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import java.util.ArrayList;

public class Solenoids{
    private ArrayList<SolenoidWrapper> solenoids = new ArrayList<>();

    private class SolenoidWrapper{
        public DoubleSolenoid sol;

        public SolenoidWrapper(int portOne, int portTwo) {
            this.sol = new DoubleSolenoid(portOne, portTwo);
        }

        public void set(Value output){
            this.sol.set(output);
        }
    }

    public int add(int portOne, int portTwo){
        solenoids.add(new SolenoidWrapper(portOne, portTwo));
        return solenoids.size() - 1;
    }

	public DoubleSolenoid get(int id) {
		return solenoids.get(id).sol;
	}
	
	public void set(int id, Value output) {
		solenoids.get(id).set(output);
	}	
	
	public void disable() {
		for(SolenoidWrapper sol : this.solenoids) {
			sol.set(DoubleSolenoid.Value.kReverse);
		}
	}
}