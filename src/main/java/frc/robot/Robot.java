package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.systems.Drive;
import frc.robot.framework.Subsystems;
import frc.robot.framework.Util.RobotState;

public class Robot extends TimedRobot {
    @Override
	public void robotInit() {
		IO.Initialize();
		Subsystems.add(new Drive());
	}
	
	@Override
	public void disabledInit() {
		Subsystems.disableAll();
	}

	@Override
	public void autonomousInit() {
		Subsystems.autoInit();
	}

	@Override
	public void autonomousPeriodic() {
		Subsystems.autoUpdate();
	}

	@Override
	public void teleopInit(){
		Subsystems.teleopInit();
	}

	@Override
	public void teleopPeriodic() {
		Subsystems.teleopUpdate();
	}
}