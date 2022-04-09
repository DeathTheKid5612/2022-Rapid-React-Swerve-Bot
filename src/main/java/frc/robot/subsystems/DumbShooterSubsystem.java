// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DumbShooterSubsystem extends SubsystemBase {
  private WPI_TalonFX m_flywheel = new WPI_TalonFX(Constants.SHOOTER_FLYWHEEL, "AuxCAN");


  /** Creates a new ExampleSubsystem. */
  public DumbShooterSubsystem() {
    m_flywheel.setInverted(true);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void flywheelPower(double power)
  {
    m_flywheel.set(TalonFXControlMode.PercentOutput, power);
  }

  public void flywheelOff()
  {
    m_flywheel.set(TalonFXControlMode.PercentOutput, 0);
  }
}
