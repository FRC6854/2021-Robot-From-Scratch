package frc.robot.commands.testing;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.KitDrivetrain;

public class Rotate extends CommandBase {
	double initial_heading = 0;
	double target_heading = 0;

	PIDController pidc = null;

	public Rotate() {
		addRequirements(KitDrivetrain.getInstance());

		initial_heading = KitDrivetrain.getInstance().gyro_get_yaw();
		System.out.println("Initial heading " + initial_heading);
		target_heading = -90; // A large enough heading
		System.out.println("Target heading " + target_heading);
		pidc = new PIDController(0.05, 0, 0.0);

		SmartDashboard.putNumber("ANGLE", 0);
	}

	@Override
	public void execute() {
		double yaw = KitDrivetrain.getInstance().gyro_get_yaw();
		System.out.println("Current Yaw " + yaw);
		double pid_value = pidc.calculate(yaw, SmartDashboard.getNumber("ANGLE", 0));
		System.out.println("PID calculate " + pid_value);
		double pid_clamped = MathUtil.clamp(pid_value, -0.4, 0.4);
		System.out.println("PID calculate clamped " + pid_clamped);
		KitDrivetrain.getInstance().arcade_drive(0, pid_clamped);
		double error = pidc.getPositionError();
		System.out.println("Error is " + error);
		if (error < 0.5) {
			System.out.println("Rotation finished");
		}

		System.out.println();
	}

	@Override
	public void end(boolean interrupted) {
		KitDrivetrain.getInstance().arcade_drive(0, 0);
	}
}
