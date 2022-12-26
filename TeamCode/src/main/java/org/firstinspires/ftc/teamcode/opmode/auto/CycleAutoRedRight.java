package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.subsystem.SleeveDetector;
import org.firstinspires.ftc.teamcode.subsystem.lift.LiftConstants;

@Autonomous
public class CycleAutoRedRight extends LinearOpMode {
    Pose2d START_POSE = new Pose2d(33, -66.5, Math.toRadians(270));
    Robot robot;
    SleeveDetector detector = new SleeveDetector();
    SleeveDetection.Color parkingPos = SleeveDetection.Color.BLUE;
    private ElapsedTime timer;

    public void runOpMode() {
        robot = new Robot(telemetry, hardwareMap);
        timer = new ElapsedTime();
        robot.init();
        robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
        sleep(1500);
        robot.lift.setArmPos(LiftConstants.IdleArm);
        detector.init(hardwareMap, telemetry);
        robot.lift.setOpmode("auto");

        TrajectorySequence parking1 = robot.drive.trajectorySequenceBuilder(START_POSE)
//                .addTemporalMarker(() -> {
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
//                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .waitSeconds(1.5)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .setReversed(true)
                // Preplaced
                .lineToLinearHeading(new Pose2d(38, -10, Math.toRadians(290)))
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(34);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.4)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .back(4.3)
                .waitSeconds(0.7)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })



                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.9)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
                    robot.lift.setTargetHeight(10);
                })
                .waitSeconds(0.2)
                .lineToLinearHeading(new Pose2d(80, -2, Math.toRadians(367)))
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(10)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .waitSeconds(0.1)
                .addTemporalMarker(() -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(51, -10, Math.toRadians(356)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setAutoRotation(360);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setAutoRotation(41.5);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.4)
                .setVelConstraint(robot.drive.getVelocityConstraint(15, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .strafeLeft(5)
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
//
//                // Cycle #2
//
//
//                .setReversed(false)
//                .waitSeconds(0.9)
//                .setVelConstraint(robot.drive.getVelocityConstraint(50, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
//                    robot.lift.setTargetHeight(7);
//                })
//                .lineToLinearHeading(new Pose2d(84, 4, Math.toRadians(347)))
//                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
//                    robot.lift.setTargetHeight(15);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .setReversed(true)
//                .waitSeconds(0.3)
//                .lineToConstantHeading(new Vector2d(44, -7))
//                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
//                    robot.lift.setTargetRotation(360);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
//                    robot.lift.setTargetHeight(37);
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .waitSeconds(1)
//                .setVelConstraint(robot.drive.getVelocityConstraint(30, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
//                    robot.lift.setArmPos(LiftConstants.IntakingArm);
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
//                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
//                    robot.lift.setTargetRotation(240);
//                })
//
//
//                //cycle 3
//
//
//                .setReversed(false)
//                .waitSeconds(0.9)
//                .setVelConstraint(robot.drive.getVelocityConstraint(50, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
//                    robot.lift.setTargetHeight(6);
//                })
//                .lineToLinearHeading(new Pose2d(84, 4, Math.toRadians(347)))
//                //pick up cone
//                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
//                })
//                .waitSeconds(0.3)
//                .addTemporalMarker(() -> {
//                    robot.lift.setTargetHeight(15);
//                })
//                .waitSeconds(0.1)
//                .addTemporalMarker(() -> {
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .setReversed(true)
//                .waitSeconds(0.3)
//                .lineToLinearHeading(new Pose2d(51, -4, Math.toRadians(346)))
//                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
//                    robot.lift.setTargetRotation(360);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
//
//                    robot.lift.setTargetHeight(41.5);
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .waitSeconds(0.4)
//                .setVelConstraint(robot.drive.getVelocityConstraint(15, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .strafeLeft(9)
//                .waitSeconds(0.7)
//                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
//                    robot.lift.setArmPos(LiftConstants.IntakingArm);
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
//                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
//                    robot.lift.setTargetRotation(240);
//                })
//
//
//                //cycle 4
//
//
//                .setReversed(false)
//                .waitSeconds(0.9)
//                .setVelConstraint(robot.drive.getVelocityConstraint(50, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
//                    robot.lift.setTargetHeight(5);
//                })
//                .lineToLinearHeading(new Pose2d(84, -10, Math.toRadians(347)))
//                //pick up cone
//                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
//                })
//                .waitSeconds(0.3)
//                .addTemporalMarker(() -> {
//                    robot.lift.setTargetHeight(15);
//                })
//                .waitSeconds(0.1)
//                .addTemporalMarker(() -> {
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .setReversed(true)
//                .waitSeconds(0.3)
//                .lineToLinearHeading(new Pose2d(51, -4, Math.toRadians(346)))
//                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
//                    robot.lift.setTargetRotation(360);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
//
//                    robot.lift.setTargetHeight(41.5);
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .waitSeconds(0.4)
//                .setVelConstraint(robot.drive.getVelocityConstraint(15, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .strafeLeft(9)
//                .waitSeconds(0.7)
//                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
//                    robot.lift.setArmPos(LiftConstants.IntakingArm);
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
//                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
//                    robot.lift.setTargetRotation(240);
//                })
//                //park
//                .forward(12)
                .build();


        robot.drive.setPoseEstimate(START_POSE);

        // Waiting for start
        while (!isStarted() && !isStopRequested()) {
            parkingPos = detector.getColor();
            telemetry.addData("timer", timer.milliseconds());
            telemetry.update();
        }

        // Start...
        detector.stop();
        waitForStart();

        if (parkingPos == SleeveDetection.Color.MAGENTA) {
            robot.drive.followTrajectorySequenceAsync(parking1);
            detector.stop();
        } else if ( parkingPos == SleeveDetection.Color.BLUE) {
            robot.drive.followTrajectorySequenceAsync(parking1);
            detector.stop();
        } else if (parkingPos == SleeveDetection.Color.RED) {
            robot.drive.followTrajectorySequenceAsync(parking1);
            detector.stop();
        }

        while(opModeIsActive()) {
            telemetry.addData("turret pos", robot.lift.getCurrentRotation());
            telemetry.addData("turret target", robot.lift.getAutoRotation());
            telemetry.addData("opmode", robot.lift.getOpmode());
            telemetry.addData("slide pos", robot.lift.getCurrentPosition());
            telemetry.update();
            robot.update();
        }
    }
}