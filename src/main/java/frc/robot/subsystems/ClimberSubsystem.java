package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.playingwithfusion.TimeOfFlight;
import com.playingwithfusion.TimeOfFlight.RangingMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  private WPI_TalonSRX m_climber1 = new WPI_TalonSRX(Constants.CLIMBER_1);
  private WPI_TalonSRX m_climber2 = new WPI_TalonSRX(Constants.CLIMBER_2);


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

  public void climberPower(double power)
  {
    m_climber1.set(TalonSRXControlMode.PercentOutput, power);
    m_climber2.set(TalonSRXControlMode.PercentOutput, power);
  }

  public void climberOff()
  {
    m_climber1.set(TalonSRXControlMode.PercentOutput, 0);
    m_climber2.set(TalonSRXControlMode.PercentOutput, 0);
  }
}