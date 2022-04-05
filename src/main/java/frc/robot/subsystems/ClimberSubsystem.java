package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.playingwithfusion.TimeOfFlight;
import com.playingwithfusion.TimeOfFlight.RangingMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  private VictorSPX m_climber_lift = new VictorSPX(Constants.CLIMBER_LIFT);
  private VictorSPX m_climber_rot = new VictorSPX(Constants.CLIMBER_ROT);


  /** Creates a new ExampleSubsystem. */
  public ClimberSubsystem() {
    
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void climberLiftPower(double power)
  {
    m_climber_lift.set(VictorSPXControlMode.PercentOutput, power);
  }

  public void climberLiftOff()
  {
    m_climber_lift.set(VictorSPXControlMode.PercentOutput, 0);
  }

  public void climberRotPower(double power)
  {
    m_climber_rot.set(VictorSPXControlMode.PercentOutput, power);
  }

  public void climberRotOff()
  {
    m_climber_rot.set(VictorSPXControlMode.PercentOutput, 0);
  }

  public void climberOff()
  {
    m_climber_rot.set(VictorSPXControlMode.PercentOutput, 0);
    m_climber_lift.set(VictorSPXControlMode.PercentOutput, 0);
  }
}