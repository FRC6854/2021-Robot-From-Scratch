package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.FlyByWire;
import viking.controllers.ctre.VikingSPX;
import viking.controllers.ctre.VikingSRX;

public class KitDrivetrain extends SubsystemBase implements RobotMap {
	private static KitDrivetrain instance = null;
	private VikingSRX left_master = null;
	private VikingSRX right_master = null;
	private VikingSPX left_follower = null;
	private VikingSPX right_follower = null;
	private DifferentialDrive diff_drive = null;

	private SendableChooser<Integer> teleop_drivetrain_cmd_chooser = null;

	public KitDrivetrain() {
		boolean is_inverted = Robot.isReal();

		left_master = new VikingSRX(CAN_LEFT_FRONT, false);
		left_follower = new VikingSPX(CAN_LEFT_BACK, left_master, false);

		right_master = new VikingSRX(CAN_RIGHT_FRONT, is_inverted);
		right_follower = new VikingSPX(CAN_RIGHT_BACK, right_master, is_inverted);

		diff_drive = new DifferentialDrive(left_master, right_master);

		teleop_drivetrain_cmd_chooser = new SendableChooser<>();
		teleop_drivetrain_cmd_chooser.setDefaultOption("ArcadeDrive", 1);
		teleop_drivetrain_cmd_chooser.addOption("Fly-by-Wire", 2);
		SmartDashboard.putData(teleop_drivetrain_cmd_chooser);
	}

	@Override
	public void periodic() {
		if (getCurrentCommand() != null) { // a command is running
			if ((teleop_drivetrain_cmd_chooser.getSelected() == 2)
				&& (getCurrentCommand().getName().equals("ArcadeDrive"))) {
				System.out.println("Fly-by-Wire selected");
				getCurrentCommand().cancel();
				new FlyByWire().schedule();
			} else if ((teleop_drivetrain_cmd_chooser.getSelected() == 1)
					   && (getCurrentCommand().getName().equals("FlyByWire"))) {
				System.out.println("ArcadeDrive selected");
				getCurrentCommand().cancel();
				new ArcadeDrive().schedule();
			}
		}
	}

	@Override
	public void simulationPeriodic() {}

	public static KitDrivetrain getInstance() {
		if (instance == null) {
			instance = new KitDrivetrain();
			instance.setDefaultCommand(new ArcadeDrive());
		}

		return instance;
	}

	public void arcade_drive(double x, double z) {
		diff_drive.curvatureDrive(x, z, true);
		diff_drive.feed();
	}
}
