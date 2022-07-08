package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import viking.controllers.ctre.VikingSPX;
import viking.controllers.ctre.VikingSRX;

public class KitDrivetrain extends SubsystemBase implements RobotMap {
	private static KitDrivetrain instance = null;
	private VikingSRX left_master = null;
	private VikingSRX right_master = null;
	private VikingSPX left_follower = null;
	private VikingSPX right_follower = null;
	private DifferentialDrive diff_drive = null;

	private AHRS gyroscope = null;

	public KitDrivetrain() {
		boolean is_inverted = Robot.isReal();

		left_master = new VikingSRX(CAN_LEFT_FRONT, false);
		left_follower = new VikingSPX(CAN_LEFT_BACK, left_master, false);

		right_master = new VikingSRX(CAN_RIGHT_FRONT, is_inverted);
		right_follower = new VikingSPX(CAN_RIGHT_BACK, right_master, is_inverted);

		diff_drive = new DifferentialDrive(left_master, right_master);

		gyroscope = new AHRS(Port.kMXP);
		gyroscope.reset();
	}

	@Override
	public void periodic() {
	}

	@Override
	public void simulationPeriodic() {
	}

	public static KitDrivetrain getInstance() {
		if (instance == null) {
			instance = new KitDrivetrain();
		}

		return instance;
	}

	public void arcade_drive(double x, double z) {
		diff_drive.curvatureDrive(x, z, true);
		diff_drive.feed();
	}

	public boolean have_gyro() {
		return gyroscope.isConnected();
	}

	public double gyro_get_pitch() {
		return gyroscope.getPitch();
	}

	public double gyro_get_roll() {
		return gyroscope.getRoll();
	}

	public double gyro_get_yaw() {
		return gyroscope.getYaw();
	}

	public double get_left_master_ticks() {
		return left_master.getTicks();
	}

	public double get_right_master_ticks() {
		return right_master.getTicks();
	}

	public double get_left_master_velocity() {
		return left_master.getVelocity();
	}

	public double get_right_master_velocity() {
		return right_master.getVelocity();
	}
}
