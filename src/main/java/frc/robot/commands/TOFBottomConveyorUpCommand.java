// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ConveyorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class TOFBottomConveyorUpCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ConveyorSubsystem m_subsystem;
  private long initTime;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TOFBottomConveyorUpCommand(ConveyorSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initTime = -1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.bottomConveyorPower(Constants.CONVEYOR_SPEED);
    if (m_subsystem.getTOFDistance() < 1000)
    {
      initTime = System.currentTimeMillis();
    }
    if (initTime != -1 && System.currentTimeMillis() < (initTime + Constants.CONVEYOR_TOF_TIME))
    {
      m_subsystem.topConveyorPower(Constants.CONVEYOR_SPEED);
    }
    else
    {
      initTime = -1;
      m_subsystem.topConveyorPower(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.conveyorOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
