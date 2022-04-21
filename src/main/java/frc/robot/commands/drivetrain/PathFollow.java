package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.KitDrivetrain;
// The main autonomous functions
public class PathFollow extends CommandBase {
    class WP{ //Class with all of the main variables needed
        public int starttime;
        public int duration;
        public double drive_x; //x is forward
        public double drive_z; //z is side to side (turning it)
    };
    WP[] wps=new WP[4]; //Creates x amoung of "instances" of the class so each variable can have their own values

    long p = 0;

    public PathFollow() {
        addRequirements(KitDrivetrain.getInstance());
		//So instead of giving it a distance, you give it a speed, and a time for execution

        //Go forward
        wps[0]=new WP();
        wps[0].starttime=0;
        wps[0].duration=100; //amount of 20 milliseconds (so 2000 milliseconds, 2 seconds)
        wps[0].drive_x=0.2; // 0.6 metres per second ish
        wps[0].drive_z=0;

        //Makes it spin (hopefully a perfect 360 but things occur)
        wps[1]=new WP();
        wps[1].starttime=100;
        wps[1].duration=230; //200
        wps[1].drive_x=0;
        wps[1].drive_z=0.25; //0.25

        wps[2]=new WP();
        wps[2].starttime=330;
        wps[2].duration=100;
        wps[2].drive_x=0.2;
        wps[2].drive_z=0;

        wps[3]=new WP();
        wps[3].starttime=430;
        wps[3].duration=230; //200
        wps[3].drive_x=0;
        wps[3].drive_z=0.25; //0.25
    }

    @Override
    public void execute() {
        for (int i=0;i<wps.length;i++) { // runs for how many instances of the wp class array there is
            if (p>=wps[i].starttime && p<(wps[i].starttime+wps[i].duration)) {
                KitDrivetrain.getInstance().arcade_drive(wps[i].drive_x, wps[i].drive_z);
                break;
            }
        }
        System.out.println(p);
        p++;
        if(p>=600){
            cancel();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted == false) {
        }
        p = 0;
        KitDrivetrain.getInstance().arcade_drive(0, 0);
    }
}