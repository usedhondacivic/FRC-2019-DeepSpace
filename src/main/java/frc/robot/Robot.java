package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.systems.Drive;
import frc.robot.systems.Arm;
import frc.robot.systems.Intake;
import frc.robot.systems.ProfileControl;
import frc.robot.systems.RobotLift;
import frc.robot.framework.Subsystems;

public class Robot extends TimedRobot {
    public static Drive drive = new Drive();
    public static Arm  arm = new Arm();
    public static Intake intake = new Intake();
    public static RobotLift robotLift = new RobotLift();
    public static ProfileControl profileControl = new ProfileControl();

    @Override
	public void robotInit() {
		IO.Initialize();
        Subsystems.add(drive);
        Subsystems.add(arm);
        Subsystems.add(intake);
        Subsystems.add(robotLift);
        Subsystems.add(profileControl);
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