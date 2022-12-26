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
public class CycleAutoRightV2 extends LinearOpMode {
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
                .lineToLinearHeading(new Pose2d(45, -10.5, Math.toRadians(280)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    robot.lift.setTargetHeight(8);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })


                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.8)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -12, Math.toRadians(345)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(40, 2, Math.toRadians(342.5)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.3)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(6.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #2


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.6)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -10, Math.toRadians(342.5)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(50, 2, Math.toRadians(340)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(5.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #3


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.6)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -8, Math.toRadians(340)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(48, 2, Math.toRadians(337.5)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                //park
                .waitSeconds(0.5)
                .back(20)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.01, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IntakingHeight);
                })
                .build();

        TrajectorySequence parking2 = robot.drive.trajectorySequenceBuilder(START_POSE)
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
                .lineToLinearHeading(new Pose2d(45, -10.5, Math.toRadians(280)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    robot.lift.setTargetHeight(8);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })


                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.8)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -12, Math.toRadians(345)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(40, 2, Math.toRadians(342.5)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.3)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(6.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #2


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.6)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -10, Math.toRadians(342.5)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(47.5, 2, Math.toRadians(340)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(5.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #3


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.6)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -8, Math.toRadians(340)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(50, 2, Math.toRadians(337.5)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                //park
                .waitSeconds(0.5)
                .forward(20)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.01, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IntakingHeight);
                })
                .build();
        TrajectorySequence parking3 = robot.drive.trajectorySequenceBuilder(START_POSE)
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
                .lineToLinearHeading(new Pose2d(45, -10.5, Math.toRadians(280)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    robot.lift.setTargetHeight(8);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })


                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.8)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -12, Math.toRadians(345)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(40, 2, Math.toRadians(342.5)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.3)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(6.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #2


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.6)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -10, Math.toRadians(342.5)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(50, 2, Math.toRadians(340)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setTargetHeight(5.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.55, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #3


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.6)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(70, -8, Math.toRadians(340)))
                .setVelConstraint(robot.drive.getVelocityConstraint(35, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .forward(12)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(15);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(48, 2, Math.toRadians(337.5)))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setAutoRotation(360);
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                //park
                .waitSeconds(0.5)
                .forward(45)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, () -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.01, () -> {
                    robot.lift.setTargetHeight(LiftConstants.IntakingHeight);
                })
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
        } else if (parkingPos == SleeveDetection.Color.BLUE) {
            robot.drive.followTrajectorySequenceAsync(parking2);
            detector.stop();
        } else if (parkingPos == SleeveDetection.Color.RED) {
            robot.drive.followTrajectorySequenceAsync(parking3);
            detector.stop();
        }

        while (opModeIsActive()) {
            telemetry.addData("turret pos", robot.lift.getCurrentRotation());
            telemetry.addData("turret target", robot.lift.getAutoRotation());
            telemetry.addData("opmode", robot.lift.getOpmode());
            telemetry.addData("slide pos", robot.lift.getCurrentPosition());
            telemetry.update();
            robot.update();
        }
    }
}