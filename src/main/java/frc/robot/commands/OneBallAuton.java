package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.DumbShooterSubsystem;

public class OneBallAuton extends SequentialCommandGroup{
  
  public OneBallAuton(DumbShooterSubsystem _ShooterSubsystem, ConveyorSubsystem _ConveyorSubsystem, DrivetrainSubsystem _DrivetrainSubsystem)
  {
    addCommands(
      //new AutonShoot(10000, _ShooterSubsystem, _ConveyorSubsystem)
      new AutonDrive(.1, 0, 0, 1000, _DrivetrainSubsystem),
      new AutonShoot(-.44, _ShooterSubsystem, _ConveyorSubsystem),
      new AutonDrive(.25, 0, 0, 3000, _DrivetrainSubsystem)
    );
  }
}
