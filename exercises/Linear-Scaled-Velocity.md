# Purpose

The purpose of this exercise is to apply simple mathematical techniques to start the arm at maximum velocity and have
it scale down toward zero as it approaches its endpoint.

This type of control is valuable to ensure mechanical systems don't move outside of their allowed space, causing them
to collide with other parts of the robot.

# Theory

The default Arm code applies a constant speed to the arm, until it is either stopped by the motor controller upon
reaching the soft limit, or, when running using the provided command factories, until it gets within a certain
tolerance of the limit.

But mechanical systems have momentum - an object in motion tends to stay in motion. So if we only tell it to stop the
moment it reaches the endpoint, it will want to keep moving, and may hit something just past its endpoint with
considerable force.

A simple way to remedy this is to gradually reduce its momentum as it gets closer to the endpoint. Since momentum is
mass * velocity, the part of momentum we have the most control over is its velocity.

So imagine we want to have 100% of our maximum velocity when we've travelled 0% of the way, 50% when we've travelled 50%,
and 0% when we've travelled 100%.

# Exercise

We need a way to calculate what percentage of the planned distance we have left to travel, and then apply that as a
percentage of the maximum velocity to the motor speed.

## Questions

* How do we measure our starting and finishing points?
* How to we measure our progress between start and finish?
* How do we use that progress together with the maximum velocity to get the step velocity?

# Wrap up

In this exercise, you scaled the velocity proportionately with the distance remaining. This is a very simple application
of just the 'P' part of a 'PID' control. The 'P' stands for Proportional, applying an input as a proportion of how far you
are from a goal value. In PID control systems, how far you are from a goal value is called the 'error'.

# Advanced Exercises

* How might you keep the maximum velocity for the whole range, and then scale from 100% down to zero just in the last 15%?
* Are there non-linear schemes you could use for scaling the velocity? What would their properties be, and what are their tradeoffs?
  * Example: what if you squared your percentage (as a value in the range of 0.0 - 1.0) before applying it to the maximum speed?
* Can you think of other techniques you could apply to arrive at the endpoint in less time, without sacrificing accuracy?
