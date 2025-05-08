package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
    // Set up the pivot motor as a brushless motor
    final SparkMax armMotor = new SparkMax(ArmConstants.MOTOR_ID, MotorType.kBrushless);

    public Arm() {
        // Assume starting position == REVERSE_LIMIT
        armMotor.getEncoder().setPosition(0);
        armMotor.setCANTimeout(250);

        SparkMaxConfig armConfig = new SparkMaxConfig();
        armConfig.voltageCompensation(ArmConstants.MOTOR_VOLTAGE_COMP);
        armConfig.smartCurrentLimit(ArmConstants.MOTOR_CURRENT_LIMIT);
        armConfig.idleMode(IdleMode.kBrake);
        armConfig.softLimit.forwardSoftLimit(ArmConstants.FORWARD_LIMIT);
        armConfig.softLimit.reverseSoftLimit(ArmConstants.REVERSE_LIMIT);
        armConfig.softLimit.forwardSoftLimitEnabled(true);
        armConfig.softLimit.reverseSoftLimitEnabled(true);
        armMotor.configure(armConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runArmMotor(double speed) {
        armMotor.set(speed);
    }

    public boolean armAtForwardLimit() {
        double armPosition = getArmPosition();
        boolean atLimit = armPosition >= (ArmConstants.FORWARD_LIMIT - ArmConstants.FORWARD_LIMIT_TOLERANCE);

        return atLimit;
    }

    public boolean armAtReverseLimit() {
        double armPosition = getArmPosition();
        boolean atLimit = armPosition > (ArmConstants.REVERSE_LIMIT - ArmConstants.REVERSE_LIMIT_TOLERANCE)
                && armPosition < (ArmConstants.REVERSE_LIMIT + ArmConstants.REVERSE_LIMIT_TOLERANCE);

        return atLimit;
    }

    public double getArmPosition() {
        return armMotor.getEncoder().getPosition();
    }

    public Command armForward(Arm arm) {
        return Commands.runEnd(
            () -> {
                armMotor.set(ArmConstants.FORWARD_SPEED);
            },
            () -> {
                armMotor.set(0);
            },
            arm)
            .onlyWhile(() -> (!armAtForwardLimit()));
    }

    public Command armReverse(Arm arm) {
        return Commands.runEnd(
            () -> {
                armMotor.set(ArmConstants.REVERSE_SPEED);
            },
            () -> {
                armMotor.set(0);
            },
            arm)
            .onlyWhile(() -> (!armAtReverseLimit()));
    }
}
