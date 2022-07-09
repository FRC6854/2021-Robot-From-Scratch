package frc.robot.commands.testing;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.KitDrivetrain;
import viking.OI;
import viking.logging.CSVWriter;
import viking.vision.limelight.Limelight;

public class VisionTest extends CommandBase {
	Limelight lm = null;
	CSVWriter cw = null;
	final boolean enable_csv_logging = false;

	public VisionTest() {
		addRequirements(KitDrivetrain.getInstance());
		KitDrivetrain.getInstance().arcade_drive(0, 0);
		lm = new Limelight();
	}

	@Override
	public void initialize() {
		if (enable_csv_logging) {
			cw = new CSVWriter("/home/lvuser/limelight-" +
					OI.getCurrentSystemTimeDate(true) + ".csv");
		}
	}

	@Override
	public void execute() {
		System.out.println("" + lm.validTargets() + "," +
				lm.targetX() + "," +
				lm.targetY() + "," +
				lm.targetA() + "," +
				lm.skewOrRotation() + "," +
				lm.latencyContribution() + "," +
				lm.shortSideLength() + "," +
				lm.longSideLength() + "," +
				lm.horizontalSideLengh() + "," +
				lm.verticalSideLength() + "," +
				lm.getPipeline() + "," +
				lm.driverMode() + "," +
				lm.getDistanceFromTarget() + "," +
				lm.getHorzAngle());

		if (enable_csv_logging) {
			ArrayList<Double> ar = new ArrayList<>();
			ar.add(lm.validTargets() ? 1.0 : 0.0);
			ar.add(lm.targetX());
			ar.add(lm.targetY());
			ar.add(lm.targetA());
			ar.add(lm.skewOrRotation());
			ar.add(lm.latencyContribution());
			ar.add(lm.shortSideLength());
			ar.add(lm.longSideLength());
			ar.add(lm.horizontalSideLengh());
			ar.add(lm.verticalSideLength());
			ar.add((double) lm.getPipeline());
			ar.add((double) lm.driverMode());
			ar.add(lm.getDistanceFromTarget());
			ar.add(lm.getHorzAngle());
			cw.appendAndFlush(ar);
		}

	}

	@Override
	public void end(boolean interrupted) {
		KitDrivetrain.getInstance().arcade_drive(0, 0);
		if (enable_csv_logging) {
			cw.close();
		}
	}
}
