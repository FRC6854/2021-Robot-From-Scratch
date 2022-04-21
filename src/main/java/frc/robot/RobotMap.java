package frc.robot;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
public interface RobotMap {
	/**
	 * ROBOT MAP
	 *
	 * Constants for physical conections on robot
	 *
	 * FORMAT: (data type can be changed as needed)
	 * public static final int CONECTOR_LOCATION/SUBSYSTEM = NUMBER
	 *
	 * to reference data from RobotMap include
	 *
	 * "implements RobotMap" on the class declaration ex.
	 * public class CoolRobot implements RobotMap
	 *
	 * DO NOT HARDCODE NUMBERS IN CODE. ALWAYS UPDATE & REFERENCE TO THIS FILE OR CONSTANTS.
	 * MAGIC NUMBERS SUCK!!!!
	 *
	 */

	/**
	 * ROBOT MAP
	 *
	 *   Shooter
	 *    BACK
	 * ||||||||||
	 * ||||||||||
	 * ||||||||||
	 * ||||||||||
	 * |||    |||
	 *    FRONT
	 *  Conveyor
	 */

	/**
	 * Controllers: Number in drivestation NOTE: Position in ds list can be locked
	 * inside drivestation Make sure this is done before each match and check before
	 * each match that the order is correct.
	 */
	public static final int CONTROLLER_DRIVER = 0;
	public static final int CONTROLLER_OPERATOR = 1;

	/**
	 * Motor Controllers: Specify the connection type (CAN, PWM) and
	 * location/subsystem in the constant
	 */

	// DRIVETRAIN
	public static final int CAN_LEFT_FRONT = 20;
	public static final int CAN_RIGHT_FRONT = 13;
	public static final int CAN_LEFT_BACK = 12;
	public static final int CAN_RIGHT_BACK = 10;

	public static final int CAN_PIGEON_IMU = 21;

	// SHOOTER
	public static final int CAN_TOP_SHOOTER = 5;
	public static final int CAN_BOTTOM_SHOOTER = 2;

	// CONVEYORS
	public static final int CAN_INTAKE_CONVEYOR = 1;
	public static final int CAN_UPPER_CONVEYOR = 4;

	// CLIMBER
	public static final int CAN_WINCH_MASTER = 2;
	public static final int CAN_WINCH_SLAVE = 1;
	public static final int CAN_LIFT = 7;
	public static final int CAN_MASS_SHIFTER = 8;

	/**
	 * PWM devices e.g. Addressable LED
	 */
	public static final int PWN_ADDRESSABLE_LED = 4;
	
	/**
	 * --------------------
	 * 	DRIVETRAIN CONSTANTS
	 * --------------------
	 */
	public final double DRIVETRAIN_kP = 1.0;
	public final double DRIVETRAIN_kI = 0.0;
	public final double DRIVETRAIN_kD = 0.0;
	public final double DRIVETRAIN_kF = 0.0;

	public final double DRIVETRAIN_kWheelRadius = 0.0762;
	public final double DRIVETRAIN_kMetersPerRevolution = 2 * Math.PI * DRIVETRAIN_kWheelRadius;

	public final int DRIVETRAIN_kCPR = 4096;

	public final DifferentialDriveKinematics DRIVETRAIN_kKinematics = new DifferentialDriveKinematics(0.65);

	public final double DRIVETRAIN_kMaxSpeed = 1;
	public final double DRIVETRAIN_kMaxAcceleration = 1;

	public final double DRIVETRAIN_ksVolts = 0.952;
	public final double DRIVETRAIN_kvVoltSecondsPerMeter = 3.08;
	public final double DRIVETRAIN_kaVoltSecondsSquaredPerMeter = 0.0424;

	public final DifferentialDriveVoltageConstraint DRIVETRAIN_kAutoVoltageConstraint = new DifferentialDriveVoltageConstraint(
		new SimpleMotorFeedforward(DRIVETRAIN_ksVolts, DRIVETRAIN_kvVoltSecondsPerMeter, DRIVETRAIN_kaVoltSecondsSquaredPerMeter),
		DRIVETRAIN_kKinematics,
		10
	);

	public final TrajectoryConfig DRIVETRAIN_kAutoConfig = new TrajectoryConfig(
	  DRIVETRAIN_kMaxSpeed,
	  DRIVETRAIN_kMaxAcceleration
	).setKinematics(DRIVETRAIN_kKinematics).addConstraint(DRIVETRAIN_kAutoVoltageConstraint);

	public final double DRIVETRAIN_kPVelocity = 5; // Value with follower (without: 0.545)

	/**
	 * --------------------
	 * 	GYRO CONSTANTS
	 * --------------------
	 */
	public final double GYRO_kP = 0.025;
	public final double GYRO_kI = 0.0;
	public final double GYRO_kD = 0.2;

	/**
	 * -------------------------
	 * 	VISION AIMING CONSTANTS
	 * -------------------------
	 */
	public final double AIM_kP = 0.05;
	public final double AIM_kI = 0.00003;
	public final double AIM_kD = 0.02;

	public final double AIM_kMaxCommand = 0.75;
	public final double AIM_kThreshold = 3;

	/**
	 * --------------------
	 *  CONVEYOR CONSTANTS
	 * --------------------
	 */
	public final double CONVEYOR_kP = 0;
	public final double CONVEYOR_kI = 0;
	public final double CONVEYOR_kD = 0;
	public final double CONVEYOR_kF = 0;
	public final double CONVEYOR_ACCELERATION = 0;
	public final double CONVEYOR_MAX_VELOCITY = 0;

	/**
	 * --------------------
	 *   SHOOTER CONSTANTS
	 * --------------------
	 */


	public final double SHOOTER_kP = 0.001;
	public final double SHOOTER_kI = 0;
	public final double SHOOTER_kD = 0.005;
	public final double SHOOTER_kF = 0;
	public final double SHOOTER_ACCELERATION = 0;
	public final double SHOOTER_MAX_VELOCITY = 0;

	public final double MAX_LIMELIGHT_DISTANCE = -7;
	public final double MAX_LIMELIGHT_SPEED = 0.60;

	public final double MIN_SPEED = 0.55;

	/**
	 * --------------------
	 *   CLIMBER CONSTANTS
	 * --------------------
	 */
	public final double LIFT_kP = 0;
	public final double LIFT_kI = 0;
	public final double LIFT_kD = 0;
	public final double LIFT_kF = 0;
	public final double LIFT_ACCELERATION = 0;
	public final double LIFT_MAX_VELOCITY = 0;
	public final float LIFT_MAX_ROTATIONS = 19f;

	public final double WINCH_kP = 0;
	public final double WINCH_kI = 0;
	public final double WINCH_kD = 0;
	public final double WINCH_kF = 0;
	public final double WINCH_ACCELERATION = 0;
	public final double WINCH_MAX_VELOCITY = 0;

	public final double SHIFT_kP = 0;
	public final double SHIFT_kI = 0;
	public final double SHIFT_kD = 0;
	public final double SHIFT_kF = 0;
	public final double SHIFT_ACCELERATION = 0;
	public final double SHIFT_MAX_VELOCITY = 0;
}
