// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.FlyByWire;
import frc.robot.commands.drivetrain.PathFollow;
import frc.robot.commands.testing.DriveDistance;
import frc.robot.commands.testing.Rotate;
import frc.robot.commands.testing.VisionTest;
import frc.robot.subsystems.KitDrivetrain;
import viking.Controller;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// The robot's subsystems and commands are defined here...
	public static Controller driver = null;

	// command chooser
	private SendableChooser<Command> teleop_drivetrain_cmd_chooser = null;
	private SendableChooser<Command> autonomous_command_chooser = null;

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		KitDrivetrain.getInstance();
		driver = new Controller(0);
		// Configure the button bindings
		configureButtonBindings();

		teleop_drivetrain_cmd_chooser = new SendableChooser<>();
		teleop_drivetrain_cmd_chooser.setDefaultOption("ArcadeDrive", new ArcadeDrive());
		teleop_drivetrain_cmd_chooser.addOption("Fly-by-Wire", new FlyByWire());
		SmartDashboard.putData("Teleop command", teleop_drivetrain_cmd_chooser);

		autonomous_command_chooser = new SendableChooser<>();
		autonomous_command_chooser.setDefaultOption("Path Follow", new PathFollow());
		autonomous_command_chooser.addOption("Vision Test", new VisionTest());
		autonomous_command_chooser.addOption("Drive Distance", new DriveDistance());
		autonomous_command_chooser.addOption("Rotate", new Rotate());
		SmartDashboard.putData("Autonomous command", autonomous_command_chooser);

		CameraServer.startAutomaticCapture();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be created by
	 * instantiating a {@link GenericHID} or one of its subclasses ({@link
	 * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
	 * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {}

	/**
	 * Use this to pass the teleop command to the main {@link Robot} class.
	 *
	 * @return the command to run in teleop
	 */
	public Command getTeleopCommand() {
		return teleop_drivetrain_cmd_chooser.getSelected();
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		return autonomous_command_chooser.getSelected();
	}
}
