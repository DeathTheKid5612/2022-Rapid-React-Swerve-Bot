// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_TalonFX m_flywheel = new WPI_TalonFX(Constants.SHOOTER_FLYWHEEL, "AuxCAN");


  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
    m_flywheel.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    m_flywheel.configNominalOutputForward(0);
    m_flywheel.configNominalOutputReverse(0);
    m_flywheel.configPeakOutputForward(1);
    m_flywheel.configPeakOutputReverse(-1);

    m_flywheel.config_kP(0, Constants.kShooterVelocityGains.kP, 30);
    m_flywheel.config_kI(0, Constants.kShooterVelocityGains.kI, 30);
    m_flywheel.config_kD(0, Constants.kShooterVelocityGains.kD, 30);
    m_flywheel.config_kF(0, Constants.kShooterVelocityGains.kF, 30);
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
    m_flywheel.set(TalonFXControlMode.Velocity, power);
  }

  public void flywheelOff()
  {
    m_flywheel.set(TalonFXControlMode.PercentOutput, 0);
  }
}
