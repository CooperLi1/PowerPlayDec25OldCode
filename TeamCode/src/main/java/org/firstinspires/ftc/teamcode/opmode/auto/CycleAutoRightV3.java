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
public class CycleAutoRightV3 extends LinearOpMode {
    Pose2d START_POSE = new Pose2d(33, -64.5, Math.toRadians(275));
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
                .lineToLinearHeading(new Pose2d(45, -16, Math.toRadians(284)))
                .back(2)
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.15, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    robot.lift.setTargetHeight(7);
                })


                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.7)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -14, Math.toRadians(338)))
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
                    robot.lift.setArmPos(0.7);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -16, Math.toRadians(345)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(5.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })

                // Cycle #2


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(13);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -16, Math.toRadians(342.5)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(4);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #3


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(13);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(76, -16, Math.toRadians(340)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(3);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #4


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
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
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(76, -16, Math.toRadians(337.5)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.3, () -> {
                    robot.lift.setTargetHeight(0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.4, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .waitSeconds(0.3)
                .back(35)
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
                .lineToLinearHeading(new Pose2d(45, -16, Math.toRadians(284)))
                .back(2)
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.15, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    robot.lift.setTargetHeight(7);
                })


                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.7)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -14, Math.toRadians(338)))
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
                    robot.lift.setArmPos(0.7);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -16, Math.toRadians(345)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(5.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })

                // Cycle #2


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(13);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -16, Math.toRadians(342.5)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(4);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #3


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(13);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(76, -16, Math.toRadians(340)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(3);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #4


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
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
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(76, -16, Math.toRadians(337.5)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.3, () -> {
                    robot.lift.setTargetHeight(0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.4, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .waitSeconds(0.3)
                .back(15)
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
                .lineToLinearHeading(new Pose2d(45, -16, Math.toRadians(284)))
                .back(2)
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    robot.lift.setTargetHeight(36);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(0.15, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setAutoRotation(240);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    robot.lift.setTargetHeight(7);
                })


                // Cycle #1


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.7)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -14, Math.toRadians(338)))
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
                    robot.lift.setArmPos(0.7);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -16, Math.toRadians(345)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(5.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })

                // Cycle #2


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(13);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(75, -16, Math.toRadians(342.5)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(4);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #3


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
                //pick up cone
                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
                })
                .waitSeconds(0.4)
                .addTemporalMarker(() -> {
                    robot.lift.setTargetHeight(13);
                })
                .setReversed(true)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(76, -16, Math.toRadians(340)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {
                    robot.lift.setTargetHeight(3);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                // Cycle #4


                .addTemporalMarker(() -> {
                })
                .setReversed(false)
                .waitSeconds(0.3)
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(87, -12, Math.toRadians(345)))
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
                .setVelConstraint(robot.drive.getVelocityConstraint(20, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .lineToLinearHeading(new Pose2d(76, -16, Math.toRadians(337.5)))
                .UNSTABLE_addTemporalMarkerOffset(-0.75, () -> {
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                    robot.lift.setAutoRotation(120);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    robot.lift.setClaw1Pos(LiftConstants.CLAWOPENPOS1);
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    robot.lift.setAutoRotation(240);
                    robot.lift.setArmPos(LiftConstants.IdleArm);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.3, () -> {
                    robot.lift.setTargetHeight(0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.4, () -> {
                    robot.lift.setArmPos(LiftConstants.IntakingArm);
                })
                .setVelConstraint(robot.drive.getVelocityConstraint(40, Math.toRadians(180), DriveConstants.TRACK_WIDTH))
                .waitSeconds(0.3)
                .forward(15)
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