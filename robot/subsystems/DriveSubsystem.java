/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;




public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */
  private VictorSP rightmastermotor, leftmastermotor, rightslavemotor, leftslavemotor;
  private double rightspeed, leftspeed;
  DifferentialDrive driveTrain;
  private double angle;
  SpeedControllerGroup rightMotors, leftMotors;

  public DriveSubsystem() {
       rightmastermotor=new VictorSP(Constants.rightmastermotor);
       leftmastermotor=new VictorSP(Constants.leftmastermotor);
       rightslavemotor=new VictorSP(Constants.rightslavemotor);
       leftslavemotor=new VictorSP(Constants.leftslavemotor);
       rightMotors = new SpeedControllerGroup(rightmastermotor, rightslavemotor);
       leftMotors = new SpeedControllerGroup(leftmastermotor, leftslavemotor);
       driveTrain = new DifferentialDrive(leftMotors, rightMotors);
       
  }

  public void rawMotorOutput(double leftSpeed, double rightSpeed) {
    driveTrain.tankDrive(leftSpeed, rightSpeed);
    }

  public void setV(double direction, double velocity){
    
    

    if(direction<90&&direction>=0){
      leftspeed=1;
      rightspeed=1-(1/90*direction);
    }else if (direction<180&&direction>=90){
      leftspeed=-1;
      rightspeed=-1+(1/90*(180-direction));
    }else if (direction<270&&direction>=180){
      leftspeed=-1+(1/90*(direction-180));
      rightspeed=-1;
    }else{
      leftspeed=1-(1/90*(direction-270));
      rightspeed=1;
    }
    rawMotorOutput(leftspeed, rightspeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
