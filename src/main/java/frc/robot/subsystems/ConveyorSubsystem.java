// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.playingwithfusion.TimeOfFlight;
import com.playingwithfusion.TimeOfFlight.RangingMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {
  private WPI_TalonFX m_conveyor_bottom = new WPI_TalonFX(Constants.SHOOTER_CONVEYOR_BOTTOM);
  private WPI_TalonFX m_conveyor_top = new WPI_TalonFX(Constants.SHOOTER_CONVEYOR_TOP);
  private TimeOfFlight m_tofSensor = new TimeOfFlight(Constants.TOF_SENSOR);


  /** Creates a new ExampleSubsystem. */
  public ConveyorSubsystem() {
    m_tofSensor.setRangingMode(RangingMode.Short, 24);
    //SmartDashboard.putNumber("TOF Value Test:", m_tofSensor.getRange());
    m_conveyor_top.setInverted(true);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void conveyorPower(double power)
  {
    m_conveyor_bottom.set(TalonFXControlMode.PercentOutput, power);
    m_conveyor_top.set(TalonFXControlMode.PercentOutput, power/2);
  }

  public void bottomConveyorPower(double power)
  {
    m_conveyor_bottom.set(TalonFXControlMode.PercentOutput, power);
  }

  public void topConveyorPower(double power)
  {
    m_conveyor_top.set(TalonFXControlMode.PercentOutput, power);
  }

  public void conveyorOff()
  {
    m_conveyor_bottom.set(TalonFXControlMode.PercentOutput, 0);
    m_conveyor_top.set(TalonFXControlMode.PercentOutput, 0);
  }

  public double getTOFDistance()
  {
    return m_tofSensor.getRange();
  }
}
