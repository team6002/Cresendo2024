// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ElbowConstants;
import frc.robot.Constants.ShoulderConstants;
import frc.robot.subsystems.SUB_Arm;
import frc.robot.subsystems.SUB_Drivetrain;
import frc.robot.subsystems.SUB_GlobalVariables;
import frc.robot.subsystems.SUB_Intake;
import frc.robot.subsystems.SUB_Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CMD_ShootSpeakerTall extends SequentialCommandGroup {
  /** Creates a new CMD_ShootSpeaker. */
  public CMD_ShootSpeakerTall(
    SUB_Arm p_arm,
    SUB_Shooter p_shooter,
    SUB_Intake p_intake,
    SUB_GlobalVariables p_variables,
    SUB_Drivetrain p_drivetrain
  ) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      p_variables.CMDsetAutofire(true),
      new CMD_AutofireTall(p_arm, p_drivetrain, p_intake, p_shooter, p_variables),
      p_variables.CMDsetAutofire(false),
      new CMD_ShoulderSetPosition(p_arm, ShoulderConstants.kShoulderHome),
      new CMD_ElbowSetPosition(p_arm, ElbowConstants.kElbowHome)
    );
  }
}
