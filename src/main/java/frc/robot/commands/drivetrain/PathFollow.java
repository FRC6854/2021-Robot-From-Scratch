package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.KitDrivetrain;

public class PathFollow extends CommandBase {
	final int fwdtmr = 100;
	final int rtttmr = 150;

	long p = 0;

	public PathFollow() {
		addRequirements(KitDrivetrain.getInstance());
	}

	@Override
	public void execute() {
		if (p < fwdtmr) {
			KitDrivetrain.getInstance().arcade_drive(0.2, 0);
		} else if (p >= fwdtmr && p < (fwdtmr + rtttmr)) {
			KitDrivetrain.getInstance().arcade_drive(0, 0.25);
		} else {
			KitDrivetrain.getInstance().arcade_drive(0, 0);
		}
		System.out.println(p);
		p++;
	}

	@Override
	public void end(boolean interrupted) {
		if (interrupted == false) {
		}
		p = 0;
	}
}
