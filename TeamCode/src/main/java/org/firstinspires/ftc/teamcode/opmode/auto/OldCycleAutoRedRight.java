package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.subsystem.SleeveDetector;
import org.firstinspires.ftc.teamcode.subsystem.lift.LiftConstants;

@Autonomous
public class OldCycleAutoRedRight extends LinearOpMode {
    Pose2d START_POSE = new Pose2d(36, -64, Math.toRadians(270));
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


        TrajectorySequence parking1 = robot.drive.trajectorySequenceBuilder(START_POSE)
//                .addTemporalMarker(() -> {
//                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
//                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
//                    robot.lift.setArmPos(LiftConstants.IdleArm);
//                })
//                .waitSeconds(1.5)
                .setVelConstraint(robot.drive.getVelocityConstraint(50, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .setReversed(true)
                // Preplaced
                .back(50)
                .turn(Math.toRadians(78.2))
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setTargetRotation(120);
                })
                .back(31.8)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(41.5);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(1.2)
                .setVelConstraint(robot.drive.getVelocityConstraint(30, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                    robot.lift.setTargetRotation(240);
                })
                // Cycle #1
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(50, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
//                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
//                    robot.lift.setTargetRotation(240);
//                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
//                })
                .turn(Math.toRadians(10))
                .waitSeconds(0.5)
                .lineToConstantHeading(new Vector2d(69,-12))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
                        robot.lift.setTargetHeight(8);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(15);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .setReversed(true)
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(38, -7))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setTargetRotation(360);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(37);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(1.4)
                .setVelConstraint(robot.drive.getVelocityConstraint(30, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                    robot.lift.setTargetRotation(240);
                })

                // Cycle #2
                .waitSeconds(1.5)
                .forward(40)
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
                    robot.lift.setTargetHeight(8);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(15);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .setReversed(true)
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(38, -7))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setTargetRotation(360);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(37);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(1.4)
                .setVelConstraint(robot.drive.getVelocityConstraint(30, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                    robot.lift.setTargetRotation(240);
                })
                //cycle 3
                .waitSeconds(1.5)
                .forward(40)
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
                    robot.lift.setTargetHeight(5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(15);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .setReversed(true)
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(38, -7))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setTargetRotation(360);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(37);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(1.4)
                .setVelConstraint(robot.drive.getVelocityConstraint(30, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.75, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IdleHeight);
                    robot.lift.setTargetRotation(240);
                })
                //park
                .forward(12)
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
            telemetry.addData("slide pos", robot.lift.getCurrentPosition());
            telemetry.update();
            robot.update();
        }
    }
}
