// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final DumbShooterSubsystem m_dumbShooterSubsystem = new DumbShooterSubsystem();
  private final ConveyorSubsystem m_conveyorSubsystem = new ConveyorSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();

  private final XboxController m_controller_driver = new XboxController(0);
  private final XboxController m_controller_operator = new XboxController(1);

  private final Command m_auton = new OneBallAuton(m_dumbShooterSubsystem, m_conveyorSubsystem, m_drivetrainSubsystem);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    CameraServer.startAutomaticCapture();
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_controller_driver.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND * .5,
            () -> -modifyAxis(m_controller_driver.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND * .5,
            () -> -modifyAxis(m_controller_driver.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND * .5
    ));

    // Configure the button bindings
    configureButtonBindings();

    m_chooser.setDefaultOption("Do Nothing", m_auton);
    m_chooser.addOption("Single Ball", m_auton);

    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Driver controls
    // Back button zeros the gyroscope
    new Button(m_controller_driver::getBackButton)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    new Button(m_controller_driver::getRightBumper).whenHeld(new BottomConveyorUpCommand(m_conveyorSubsystem));
    //Operator Controls
    new Button(m_controller_operator::getYButton).whenHeld(new ConveyorUpCommand(m_conveyorSubsystem));
    new Button(m_controller_operator::getBButton).whenHeld(new ConveyorDownCommand(m_conveyorSubsystem));
    new Button(m_controller_operator::getAButton).whenHeld(new FlywheelCommand(Constants.IS_NOT_INVERTED, m_shooterSubsystem));
    new Button(m_controller_operator::getXButton).whenHeld(new TOFBottomConveyorUpCommand(m_conveyorSubsystem));
    new Button(m_controller_operator::getLeftBumper).whenHeld(new FlywheelReverseCommand(m_dumbShooterSubsystem));

    //new Button().whenHeld(new ClimberCommand(Constants.IS_NOT_INVERTED, m_climberSubsystem));
    //new Trigger(m_controller::getRawButton(10)).whileActiveContinuous(new ClimberCommand(Constants.IS_NOT_INVERTED, m_climberSubsystem));
    new Button(m_controller_driver.getPOV() == 0).whenHeld(new ClimberCommand(Constants.IS_NOT_INVERTED, m_climberSubsystem));
    
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}
