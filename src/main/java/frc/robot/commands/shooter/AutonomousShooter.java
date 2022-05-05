package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.RobotMap;

/**
 * Creating a very basic autonomous shooter (WIP but does do the base purpose)
 */
public class AutonomousShooter extends CommandBase implements RobotMap {
    
    
    double time  = 1; //90 //3
    double power = 1.0;
    long p = 0;

    public AutonomousShooter() {
        addRequirements(Conveyor.getInstance());
        addRequirements(Shooter.getInstance());
		
        
    }

    @Override
    public void execute() {
        for (int i = 0; i < time; i++) {
            Conveyor.getInstance().setOutputIntake(power);
            Conveyor.getInstance().setOutputUpper(power / 5); //10 is the base, it slows the upper conveyor down to not kill it
                                                              // I have set it to 5 now which des make it around twice as fast in general
            Shooter.getInstance().setOutputTop(MIN_SPEED);
			Shooter.getInstance().setOutputBottom(MIN_SPEED);
            
        }
        p++;
        if(p>=400){ //110 = around 2.3 sec when time = 3
            // 200 = around 4.1 secs when time is 1
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
        
       
    }
}