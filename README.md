# Purpose

This project is mean to serve as a template, test lab, and test bench for robotic arm subsystem code.

The provided features are intentionally very minimal, so as to allow room to explore, without presupposing
anything important about the requirements of the application, or the skill/understanding of the user.

# What's provided

## subsystems/Arm.java

This is a relatively basic subsystem that enables control of a single SparkMax-controller DC Brushless motor
with pre-programmed encoder-based forward and reverse soft limits (limiting the range in which the arm can swing).

If offers a few basic public methods exposing operations for making the motor run (in the range of -1.0 to 1.0),
the motor's encoder value, and querying whether the arm is at its foward or reverse limit.

Finally it has two simple command factories that, when the returned factories are run, they will rotate the arm in
the specified direction until the soft limit is hit, and then the command will return.

## Constants.java

A class collecting various preprogrammed values specifying the operating parameters of the robot code.

It specifies which attached gamepad is used for control (the one assigned to port 0 in the Driver Station),
provides some nice mnemonic constants for gamepad button IDs, as well as the operational parameters of the Arm itself.

The default CAN ID for the arm motor is 9.  The default forward speed is 20% (0.20) and the default reverse speed is
80% (-0.80).

## RobotContainer.java

This initializes the Arm subsystem, connects to the gamepad, and then binds the Left Trigger button to the command for
running the arm foward, and the Right Trigger button for running the arm backward.

# Exercises

* [Linearly Scaled Velocity](exercises/Linear-Scaled-Velocity.md)
