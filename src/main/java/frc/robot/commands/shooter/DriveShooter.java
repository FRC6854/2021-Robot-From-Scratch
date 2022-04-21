package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;
import frc.robot.subsystems.Shooter;
//import viking.vision.Limelight;

/**
 * This class could be written as a state-machine in the shooter subsystem. 
 * The purpose of this is for manual control of the motors with special states depending on driver input or vision target info
 */
public class DriveShooter extends CommandBase implements RobotMap {

	private Shooter shooter = null;

	public DriveShooter() {
		shooter = Shooter.getInstance();
		addRequirements(shooter);
	}
	
	//temp commenting out limelight stuff as this has not been implemented
	//trying to make it analog, original one is on the bottom
	@Override
	public void execute() {
		if (RobotContainer.driver.getControllerRBumper() == true) {
		/*	if (Limelight.getInstance().validTargets() == true) {
				if (Limelight.getInstance().targetY() < MAX_LIMELIGHT_DISTANCE) {
					shooter.setOutputTop(MAX_LIMELIGHT_SPEED);
					shooter.setOutputBottom(MAX_LIMELIGHT_SPEED);
				}
				else {
					shooter.setOutputTop(MIN_SPEED);
					shooter.setOutputBottom(MIN_SPEED);
				}
			} */
		//	else {
				shooter.setOutputTop(MIN_SPEED);
				shooter.setOutputBottom(MIN_SPEED);
		//	}

		}
		else if (RobotContainer.driver.getControllerLBumper() == true) { //RobotContainer.driver.getControllerLTrigger() < 0
			shooter.setOutputTop(0.3);
			shooter.setOutputBottom(-0.35);
		}
		else {
			shooter.fullStopTop();
			shooter.fullStopBottom();
		}
	}
	/*
	@Override
	public void execute() {
		if (RobotContainer.driver.getControllerRBumper() == true) {
			if (Limelight.getInstance().validTargets() == true) {
				if (Limelight.getInstance().targetY() < MAX_LIMELIGHT_DISTANCE) {
					shooter.setOutputTop(MAX_LIMELIGHT_SPEED);
					shooter.setOutputBottom(MAX_LIMELIGHT_SPEED);
				}
				else {
					shooter.setOutputTop(MIN_SPEED);
					shooter.setOutputBottom(MIN_SPEED);
				}
			} 
		//	else {
				shooter.setOutputTop(MIN_SPEED);
				shooter.setOutputBottom(MIN_SPEED);
		//	}

		}
		else if (RobotContainer.driver.getControllerLBumper() == true) {
			shooter.setOutputTop(0.3);
			shooter.setOutputBottom(-0.35);
		}
		else {
			shooter.fullStopTop();
			shooter.fullStopBottom();
		}
	}
	*/

	@Override
	public void end(boolean interrupted) {
		shooter.fullStopTop();
		shooter.fullStopBottom();
	}
}
