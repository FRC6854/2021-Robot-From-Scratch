package frc.robot.commands.testing;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.KitDrivetrain;

public class DriveDistance extends CommandBase {

	PIDController pidc = null;
	PIDController pidh = null;

	double itick;
	double initial_heading = 0;

	public DriveDistance() {
		addRequirements(KitDrivetrain.getInstance());
		System.out.println("INIT");

		pidc = new PIDController(0.0002, 0, 0.00005);
		pidh = new PIDController(0.05, 0, 0.0);
		itick = KitDrivetrain.getInstance().get_left_master_ticks();

		SmartDashboard.putNumber("POS", 0);
		initial_heading = KitDrivetrain.getInstance().gyro_get_yaw();
	}

	@Override
	public void initialize() {
		itick = KitDrivetrain.getInstance().get_left_master_ticks();
		SmartDashboard.putNumber("Target Position", 0);
		initial_heading = KitDrivetrain.getInstance().gyro_get_yaw();
	}

	@Override
	public void execute() {
		double ltick = KitDrivetrain.getInstance().get_left_master_ticks();
		double rtick = KitDrivetrain.getInstance().get_right_master_ticks();
		double target = 80000;
		double pid_value = -pidc.calculate(ltick, itick + target);
		double pid_clamped = MathUtil.clamp(pid_value, -0.5, 0.5);
		System.out.println("TICK " + ltick + " PID " + pid_value + " CLAMP " + pid_clamped);
		double hz_pid_value = pidh.calculate(KitDrivetrain.getInstance().gyro_get_yaw(), initial_heading);
		double hz_pid_clamped = MathUtil.clamp(hz_pid_value, -0.2, 0.2);
		KitDrivetrain.getInstance().arcade_drive(pid_clamped, hz_pid_clamped);
	}

	@Override
	public void end(boolean interrupted) {
		KitDrivetrain.getInstance().arcade_drive(0, 0);
	}
}
