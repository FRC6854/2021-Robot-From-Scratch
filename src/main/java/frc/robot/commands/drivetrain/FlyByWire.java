package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.KitDrivetrain;

public class FlyByWire extends CommandBase {

	public FlyByWire() {
		addRequirements(KitDrivetrain.getInstance());
	}

	private double nonlinear_input(double in, double coeff) {
		int sgn = in < 0 ? -1 : 1;
		in = Math.pow(in, 2) * sgn * coeff;
		return in;
	}

	@Override
	public void execute() {
		double input_x = RobotContainer.driver.getControllerLeftStickX();
		double input_y = RobotContainer.driver.getControllerLeftStickY();
		KitDrivetrain.getInstance().arcade_drive(nonlinear_input(input_y, 1.0),
												 nonlinear_input(input_x, 0.8));
		if (input_y == 0) {
			if (input_x == 0) {
				System.out.println("Stop");
			} else {
				System.out.println("Rotate");
			}
		} else {
			if (input_x > -0.05 && input_x < 0.05) {
				System.out.println("Straignt");
			} else {
				System.out.println("Curve");
			}
		}
	}

	@Override
	public void end(boolean interrupted) {
		if (interrupted == false) {
		}
		KitDrivetrain.getInstance().arcade_drive(0, 0);
	}
}
