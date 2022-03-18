package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class OneBallAuton extends SequentialCommandGroup{
  
  public OneBallAuton(ShooterSubsystem _ShooterSubsystem, ConveyorSubsystem _ConveyorSubsystem, DrivetrainSubsystem _DrivetrainSubsystem)
  {
    addCommands(
      //new AutonShoot(10000, _ShooterSubsystem, _ConveyorSubsystem)
      new AutonDrive(.1, 0, 0, 500, _DrivetrainSubsystem)
    );
  }
}
