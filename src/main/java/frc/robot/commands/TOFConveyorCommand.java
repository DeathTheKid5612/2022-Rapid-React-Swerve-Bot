// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ConveyorSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class TOFConveyorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ConveyorSubsystem m_conveyorSubsystem;
  private long initTime;
  private boolean ballFlag;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TOFConveyorCommand(ConveyorSubsystem conveyorSubsystem) {
    
    m_conveyorSubsystem = conveyorSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(m_conveyorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initTime = -1;
    ballFlag = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
        if (m_conveyorSubsystem.getBottomTOFDistance() > Constants.CONVEYOR_TOF_DISTANCE &&
            m_conveyorSubsystem.getTopTOFDistance() > Constants.CONVEYOR_TOF_DISTANCE)
          {
            noBallLoaded();
          }
        else if (m_conveyorSubsystem.getBottomTOFDistance() > Constants.CONVEYOR_TOF_DISTANCE &&
                 m_conveyorSubsystem.getTopTOFDistance() <= Constants.CONVEYOR_TOF_DISTANCE)
          {
            oneBallLoaded();
          }
        else if (m_conveyorSubsystem.getBottomTOFDistance() <= Constants.CONVEYOR_TOF_DISTANCE &&
                 m_conveyorSubsystem.getTopTOFDistance() <= Constants.CONVEYOR_TOF_DISTANCE)
          {
            twoBallLoaded();
          }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_conveyorSubsystem.conveyorOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  //no ball loaded
  private void noBallLoaded()
  {
    m_conveyorSubsystem.bottomConveyorPower(Constants.BOTTOM_CONVEYOR_SPEED);
    //Ball was intaked
    if (m_conveyorSubsystem.getBottomTOFDistance() <= Constants.CONVEYOR_TOF_DISTANCE)
    {
      m_conveyorSubsystem.topConveyorPower(Constants.TOP_CONVEYOR_SPEED);
      ballFlag = true;
    }
    //we have a ball. get it to the top sensor
    if (ballFlag)
    {
      //ball is close to top sensor. Slow down
      if (m_conveyorSubsystem.getBottomTOFDistance() > Constants.CONVEYOR_TOF_DISTANCE &&
          m_conveyorSubsystem.getTopTOFDistance() > Constants.CONVEYOR_TOF_DISTANCE)
      {
        m_conveyorSubsystem.topConveyorPower(Constants.TOP_CONVEYOR_SPEED/2);
      }
      //stop conveyor on top sensor
      if (m_conveyorSubsystem.getTopTOFDistance() <= Constants.CONVEYOR_TOF_DISTANCE)
      {
        m_conveyorSubsystem.conveyorOff();
      }
    }
  }

  //one ball loaded
  private void oneBallLoaded()
  {
    m_conveyorSubsystem.bottomConveyorPower(Constants.BOTTOM_CONVEYOR_SPEED);
    if (m_conveyorSubsystem.getBottomTOFDistance() <= Constants.CONVEYOR_TOF_DISTANCE)
    {
      //we got the second ball
      m_conveyorSubsystem.bottomConveyorPower(0);
    }
  }

  //two ball loaded
  private void twoBallLoaded()
  {
    //we have two balls dont let them jam
    m_conveyorSubsystem.conveyorOff();
  }
}
