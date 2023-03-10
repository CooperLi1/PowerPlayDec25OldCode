package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.ejml.dense.row.linsol.LinearSolver_DDRB_to_DDRM;
import org.firstinspires.ftc.teamcode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.subsystem.lift.LiftConstants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class CooperTele extends LinearOpMode {

    private Robot robot;
    private robotState robotState;
    private ElapsedTime timer;
    private ElapsedTime turrettimer;
    private ElapsedTime armtimer;
    private boolean canTurn = false;
    double turretspeed = 0.4;
    double turretaddition = 40;
    double dtspeed = 1;
    double up = 34;
    double mid = 22;
    double low = 11;
    double ground = -1;
    private double front = 0;
    private double back = 660;
    private double right = 330;
    private double left = -330;
    private boolean armup = true;
    private boolean lowheight = false;



    public double CloseDelay = 1000;

    public enum robotState {
        IDLE,
        INTAKING,
        GRABBED,
        LIFTED,
        DROPPED
    }

    public void runOpMode() throws InterruptedException {
        robot = new Robot(telemetry, hardwareMap);
        timer = new ElapsedTime();
        turrettimer = new ElapsedTime();
        armtimer = new ElapsedTime();
        waitForStart();
        robot.init();
        robotState = robotState.INTAKING;
        robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
        robot.lift.turretmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lift.setOpmode("teleop");
        armup = true;
        //robot.lift.turretMaxSpeed = 1;
        while (!isStopRequested() && opModeIsActive()) {

            //anti-tip + regular teleop code - ONLY ON PITCH RIGHT NOW
            double antiTipMulti = 1;
            double correctedpitch = robot.drive.getOrientation().thirdAngle + 3.13;
            robot.drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * dtspeed, //  controls forward
                            -gamepad1.left_stick_x * dtspeed, +//controls strafing
                            -gamepad1.right_stick_x * dtspeed //controls turning
                    )
            );

            if (gamepad2.right_bumper == true) {
                    turretaddition = 10;
            } else {
                    turretaddition = 40;
            }

            if (gamepad1.right_bumper == true) {
                    dtspeed = 0.4;
            } else{
                    dtspeed = 1;
            }


            double TurretPower = gamepad2.right_stick_x;
            if (canTurn = true) {
                if(TurretPower>0.5){
                    robot.lift.setTargetRotation(robot.lift.getCurrentRotation()-turretaddition);
                }else if(TurretPower<-0.5){
                    robot.lift.setTargetRotation(robot.lift.getCurrentRotation()+turretaddition);
                }
            }


            if(gamepad2.left_stick_y > 0.5) {
                robot.lift.setTargetHeight(robot.lift.getTargetHeight()+10);
            } else if(gamepad2.left_stick_y < -0.5) {
                robot.lift.setTargetHeight(robot.lift.getTargetHeight()-10);
            }

            if (canTurn = true) {
                if(turrettimer.milliseconds() > 500){
                    if(gamepad2.a) {
                        robot.lift.setTargetRotation(back);
                        turrettimer.reset();
                    }
                    if(gamepad2.x) {
                        robot.lift.setTargetRotation(right);
                        turrettimer.reset();
                    }
                    if(gamepad2.b && gamepad2.start == false) {
                        robot.lift.setTargetRotation(left);
                        turrettimer.reset();
                    }
                    if(gamepad2.y) {
                        robot.lift.setTargetRotation(front);
                        turrettimer.reset();
                    }
                    if(gamepad1.a && gamepad1.start == false) {
                        robot.lift.setTargetRotation(front);
                        turrettimer.reset();
                    }
                }
            }


            //robot.lift.setPower(Trigger1 * UpLiftMulti);
            //robot.lift.setPower(Trigger2 * DownLiftMulti)
            //robot.lift.setArmPos(robot.lift.getArmPosition() - armpower * armpowermulti);

            switch (robotState) {
                case IDLE:
                    canTurn = true;
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                    if (timer.milliseconds() > 500) {
                        if (gamepad1.left_bumper) {
                            robot.lift.setTargetHeight(LiftConstants.IntakingHeight);
                            timer.reset();
                            robotState = robotState.INTAKING;
                        }
                    }
                    break;
                case INTAKING:
                    canTurn = false;
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                    robot.lift.setTargetHeight(LiftConstants.IntakingHeight);
                    if (timer.milliseconds() > 500) {
                        if (gamepad1.left_bumper) {
                            robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                            robotState = robotState.GRABBED;
                            timer.reset();
                        }
                    }
                    break;
                case GRABBED:
                    canTurn = true;
                    if (timer.milliseconds() > 300) {
                        robot.lift.setArmPos(LiftConstants.IdleArm);
                        robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                    }
                    if (timer.milliseconds() > 500) {
                        if (gamepad1.left_bumper) {
                            robot.lift.setTargetHeight(LiftConstants.IntakingHeight);
                            timer.reset();
                            robotState = robotState.INTAKING;
                        }
                        if (gamepad2.dpad_up) {
                            robot.lift.setTargetHeight(up);
                            robot.lift.setArmPos(LiftConstants.IdleArm);
                            timer.reset();
                            robotState = robotState.LIFTED;
                            armup = true;
                            lowheight = false;
                        }
                        if (gamepad2.dpad_right) {
                            robot.lift.setTargetHeight(mid);
                            robot.lift.setArmPos(LiftConstants.IdleArm);
                            timer.reset();
                            robotState = robotState.LIFTED;
                            armup = true;
                            lowheight = false;
                        }
                        if (gamepad2.dpad_left) {
                            robot.lift.setTargetHeight(low);
                            robot.lift.setArmPos(LiftConstants.IdleArm);
                            timer.reset();
                            robotState = robotState.LIFTED;
                            armup = true;
                            lowheight = false;
                        }
                        if (gamepad2.dpad_down) {
                            robot.lift.setTargetHeight(ground);
                            robot.lift.setArmPos(LiftConstants.IntakingArm);
                            timer.reset();
                            robotState = robotState.LIFTED;
                            armup = false;
                            lowheight = true;
                        }
                    }
                    break;
                case LIFTED:
                    canTurn = true;
                    dtspeed = 0.4;
                    if (timer.milliseconds() > 750) {
                        if (gamepad2.dpad_down || gamepad1.dpad_down) {
                            timer.reset();
                            robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                            robotState = robotState.GRABBED;
                        }
                        if (gamepad1.left_bumper) {
                            robot.lift.setArmPos(LiftConstants.IntakingArm);
                            timer.reset();
                            robotState = robotState.DROPPED;
                        }
                        if (armtimer.milliseconds() > 1000) {
                            if (gamepad2.left_bumper) {
                                robot.lift.setArmPos(LiftConstants.IntakingArm);
                                armup = false;
                                armtimer.reset();
                            }
                            if (gamepad2.left_bumper && armup == false) {
                                robot.lift.setArmPos(LiftConstants.IdleArm);
                                armup = true;
                                armtimer.reset();
                            }
                        }
                    }
                    break;
                case DROPPED:
                    dtspeed = 1;
                    canTurn = false;
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                    if (timer.milliseconds() > 450) {
                        if (lowheight == false) {
                            robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                            robot.lift.setArmPos(LiftConstants.IdleArm);
                            robot.lift.setTargetRotation(front);
                            robotState = robotState.IDLE;
                            timer.reset();
                        }else {
                            robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                            robot.lift.setArmPos(LiftConstants.IdleArm);
                            sleep(150);
                            robot.lift.setTargetRotation(front);
                            robotState = robotState.IDLE;
                            timer.reset();
                        }

                    }
                    break;

            }
            telemetry.addData("turret pos", robot.lift.getCurrentRotation());
            telemetry.addData("turret goal", robot.lift.getTargetRotation());
            telemetry.addData("State", robotState);
            telemetry.addData("Height", robot.lift.getHeight());
            telemetry.addData("TargetHeight", robot.lift.getTargetHeight());
            telemetry.addData("Y value", gamepad2.left_stick_y);
            telemetry.addData("Distance", robot.lift.getDistance());
            telemetry.addData("Orientation", robot.drive.getOrientation());
            telemetry.addData("armpos1", robot.lift.armServo1.getPosition());
            telemetry.addData("armpos2", robot.lift.armServo2.getPosition());
            telemetry.addData("timer", timer.milliseconds());
            telemetry.addData("turrettimer", turrettimer.milliseconds());
            telemetry.addData("dtspeed", dtspeed);
            telemetry.addData("slide power", robot.lift.motor2.getPower());
            telemetry.addData("armup?", armup);
            telemetry.addData("lowheight?", lowheight);
            telemetry.addData("arm timer", armtimer.milliseconds());
            robot.update();
        }
    }
}
