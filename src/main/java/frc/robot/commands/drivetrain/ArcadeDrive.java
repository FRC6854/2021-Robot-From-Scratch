package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.KitDrivetrain;

public class ArcadeDrive extends CommandBase {

	public ArcadeDrive() {
		addRequirements(KitDrivetrain.getInstance());
	}

	@Override
	public void execute() {
		KitDrivetrain.getInstance().arcade_drive(RobotContainer.driver.getControllerLeftStickY(),
												 RobotContainer.driver.getControllerLeftStickX());
	}

	@Override
	public void end(boolean interrupted) {
		if (interrupted == false) {
		}
	}
}
