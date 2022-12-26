package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.subsystem.SleeveDetector;
import org.firstinspires.ftc.teamcode.subsystem.lift.LiftConstants;

@Autonomous
public class ParkAutoRedRight extends LinearOpMode {
    Pose2d START_POSE = new Pose2d(36, -64, Math.toRadians(270));
    Robot robot;
    SleeveDetector detector = new SleeveDetector();
    SleeveDetection.Color parkingPos = SleeveDetection.Color.MAGENTA;

    public void runOpMode() {
        robot = new Robot(telemetry, hardwareMap);
        robot.init();
        // ElapsedTime timer = new ElapsedTime();
        detector.init(hardwareMap, telemetry);
        robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);

        TrajectorySequence parking1 = robot.drive.trajectorySequenceBuilder(START_POSE)
                .setReversed(true)
                .lineToLinearHeading(new Pose2d(36, -40, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(80, -40, Math.toRadians(90)))
                .build();
        TrajectorySequence parking2 = robot.drive.trajectorySequenceBuilder(START_POSE)
                .setReversed(true)
                .lineToLinearHeading(new Pose2d(36, -40, Math.toRadians(90)))
                .build();
        TrajectorySequence parking3 = robot.drive.trajectorySequenceBuilder(START_POSE)
                .setReversed(true)
                .lineToLinearHeading(new Pose2d(36, -40, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(0, -40, Math.toRadians(90)))
                .build();

        robot.drive.setPoseEstimate(START_POSE);

        // Waiting for start
        int counter = 0;
        while (!isStarted() && !isStopRequested()) {
            parkingPos = detector.getColor();
            telemetry.addData("Parking position", parkingPos);
            telemetry.addData("counter", counter);
            counter++;
            telemetry.update();
        }

        // Start...
        detector.stop();
        waitForStart();

        if (parkingPos == SleeveDetection.Color.MAGENTA) {
            robot.drive.followTrajectorySequenceAsync(parking1);
        } else if ( parkingPos == SleeveDetection.Color.BLUE) {
            robot.drive.followTrajectorySequenceAsync(parking2);
        } else if (parkingPos == SleeveDetection.Color.RED) {
            robot.drive.followTrajectorySequenceAsync(parking3);
        }

        while (!isStopRequested() && robot.drive.isBusy() && opModeIsActive()) {
            telemetry.update();
            robot.update();
        }
    }
}