// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class AutonDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DrivetrainSubsystem m_driveTrainSubsystem;
  int xVelocity, yVelocity, rotVelocity, time;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonDrive(int _xVelocity, int _yVelocity, int _rotVelocity, int _time, DrivetrainSubsystem driveTrainSubsystem) {
    m_driveTrainSubsystem = driveTrainSubsystem;
    xVelocity = _xVelocity;
    yVelocity = _yVelocity;
    rotVelocity = _rotVelocity;
    time = _time;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Position", m_driveTrainSubsystem.getPosition());
    long startTime = System.currentTimeMillis();
    m_driveTrainSubsystem.autonDrive(new ChassisSpeeds(xVelocity, yVelocity, rotVelocity));
    while(System.currentTimeMillis() < (startTime + time))
    {
      //Just wait
    }
    m_driveTrainSubsystem.autonDrive(new ChassisSpeeds(0,0,0));
    
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrainSubsystem.autonDrive(new ChassisSpeeds(0,0,0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
