// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DumbShooterSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class AutonShoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DumbShooterSubsystem m_shooterSubsystem;
  private final ConveyorSubsystem m_conveyorSubsystem;
  private double shooterVelocity;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonShoot(double inputVelocity, DumbShooterSubsystem shooterSubsystem, ConveyorSubsystem conveyorSubsystem) {
    m_shooterSubsystem = shooterSubsystem;
    m_conveyorSubsystem = conveyorSubsystem;
    shooterVelocity = inputVelocity;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem, conveyorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    long startTime = System.currentTimeMillis();
    m_shooterSubsystem.flywheelPower(-shooterVelocity);
    while(System.currentTimeMillis() < (startTime + 2000))
    {
      //just wait
    }
    m_conveyorSubsystem.conveyorPower(.3);
    startTime = System.currentTimeMillis();
    while(System.currentTimeMillis() < (startTime + 2000))
    {
      //just wait
    }
    m_shooterSubsystem.flywheelOff();
    m_conveyorSubsystem.conveyorOff();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooterSubsystem.flywheelOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
