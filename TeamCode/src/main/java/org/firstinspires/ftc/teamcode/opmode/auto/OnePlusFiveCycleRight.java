//package org.firstinspires.ftc.teamcode.opmode.auto;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
//import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
//import org.firstinspires.ftc.teamcode.subsystem.Robot;
//import org.firstinspires.ftc.teamcode.subsystem.SleeveDetector;
//import org.firstinspires.ftc.teamcode.subsystem.lift.LiftConstants;
//@Autonomous
//public class OnePlusFiveCycleRight extends LinearOpMode{
//    Pose2d START_POSE = new Pose2d(33, -66.5, Math.toRadians(270));
//    Robot robot;
//    SleeveDetector detector = new SleeveDetector();
//    SleeveDetection.Color parkingPos = SleeveDetection.Color.BLUE;
//    private ElapsedTime timer;
//
//    public void runOpMode(){
//        robot = new Robot(telemetry, hardwareMap);
//        timer = new ElapsedTime();
//        robot.init();
//        robot.lift.setClaw1Pos(LiftConstants.CLAWCLOSEPOS1);
//        sleep(1500);
//        robot.lift.setArmPos(LiftConstants.IdleArm);
//        detector.init(hardwareMap, telemetry);
//        robot.lift.setOpmode("auto");
//
//        TrajectorySequence parking1 = robot.drive.trajectorySequenceBuilder(START_POSE)
//                .setVelConstraint(robot.drive.get)
//    }
//}
