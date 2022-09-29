package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.RobotMap;

/**
 * Creating a very basic autonomous shooter (WIP but does do the base purpose)
 */
public class AutonomousShooter extends CommandBase implements RobotMap {
    
    double power = 1.0; 
    long p = 0;

    public AutonomousShooter() {
        addRequirements(Conveyor.getInstance());
        addRequirements(Shooter.getInstance());
		addRequirements(KitDrivetrain.getInstance());
        
    }

    @Override
    public void execute() { // Runs every 20 ms
        
            Conveyor.getInstance().setOutputIntake(power);
            Conveyor.getInstance().setOutputUpper(power / 8); //10 is the base, it slows the upper conveyor down to not kill it
                                                              
            Shooter.getInstance().setOutputTop(MIN_SPEED);
			Shooter.getInstance().setOutputBottom(MIN_SPEED);
            
            if (p < 75) { //For 1.5 seconds, the robot goes forwards (the conveyor is the front) to pick up any balls behind it
                KitDrivetrain.getInstance().arcade_drive(0.25, 0);
            }
        
        p++;
        if(p>=600){ //After 12 seconds, stop the robot
            cancel();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted == false) {
        }
        Conveyor.getInstance().fullStopIntake();
		Conveyor.getInstance().fullStopUpper();
        Shooter.getInstance().fullStopTop();
		Shooter.getInstance().fullStopBottom();
        KitDrivetrain.getInstance().arcade_drive(0,0);
    }
}