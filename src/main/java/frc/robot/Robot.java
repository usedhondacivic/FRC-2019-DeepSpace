package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.systems.Drive;
import frc.robot.systems.Arm;
import frc.robot.systems.Intake;
import frc.robot.systems.ProfileControl;
import frc.robot.systems.RobotLift;
import frc.robot.framework.Subsystems;

public class Robot extends TimedRobot {
    @Override
	public void robotInit() {
		IO.Initialize();
        Subsystems.add(new Drive());
        Subsystems.add(new Arm());
        Subsystems.add(new Intake());
        Subsystems.add(new RobotLift());
        Subsystems.add(new ProfileControl());
	}
    @Override
    public void robotPeriodic() {
        IO.out.update();
        IO.in.update();
    }
    
	@Override
	public void disabledInit() {
        Subsystems.disableAll();
        IO.out.disable();
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