package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Autonomous extends SequentialCommandGroup{
  
  public Autonomous(ConveyorSubsystem _ConveyorSubsystem, ShooterSubsystem _ShooterSubsystem, DrivetrainSubsystem _DrivetrainSubsystem)
  {
    addCommands(
      //new AutonShoot(10000, _ShooterSubsystem, _ConveyorSubsystem)
      //new AutonDrive(0, 0, 0, )
    );
  }
}
