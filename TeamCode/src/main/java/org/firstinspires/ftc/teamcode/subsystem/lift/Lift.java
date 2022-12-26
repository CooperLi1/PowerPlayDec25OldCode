package org.firstinspires.ftc.teamcode.subsystem.lift;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import static org.firstinspires.ftc.teamcode.subsystem.lift.LiftConstants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystem.Subsystem;

@Config
public class Lift implements Subsystem {

    public static double kG = 0.3;

    public DcMotorEx motor1;
    public DcMotorEx motor2;
    public DcMotorEx turretmotor;
    public Servo clawServoB;
    public Servo clawServo;
    public Servo armServo1;
    public Servo armServo2;
    private AnalogSensor weightSensor;
    private RevColorSensorV3 colorRangeSensor;

    //constants in inches and RPM, everything else unitless
    private static double UP_WINCH_RADIUS = 40./25.4;
    private static double MOTOR_GEAR_RATIO =  1 + 46./11.;
    private static double MOTOR_BASE_RPM = 5960;
    private static double TICKS_PER_REVOLUTION = 28;

    private double MAX_POWER = 1;
    private double turretpower;

    public static double kP = 0.3124, kI = 0, kD = 0.01315;
    public static double tkP = 0.3124, tkI = 0, tkD = 0.01315;

    private PIDController pid;
    private PIDController turretpid;

    private int bottomOffset = 0;

    private double targetHeight = 0;
    private double currentHeight = 0;
    private double currentRotation = 0;
    private double targetRotation = 0;
    private double autoRotation = 0;

    private double stackDifference = 1.0;
    public double turretMaxSpeed = 1;

    private String mode = "";
    private String opmode = "";
    private ElapsedTime timer = new ElapsedTime();

    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {
        motor1 = hardwareMap.get(DcMotorEx.class, "lift1");
        motor2 = hardwareMap.get(DcMotorEx.class, "lift2");
        turretmotor = hardwareMap.get(DcMotorEx.class, "turret");

        //reverse correctly
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        turretmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        clawServo = hardwareMap.get(Servo.class, "claw");
        clawServoB = hardwareMap.get(Servo.class, "claw2");
        armServo1 = hardwareMap.get(Servo.class, "arm");
        armServo2 = hardwareMap.get(Servo.class, "arm2");
        colorRangeSensor = hardwareMap.get(RevColorSensorV3.class, "freightColorSensor");

        bottomOffset = motor2.getCurrentPosition();

        pid = new PIDController(kP, kI, kD);
        turretpid = new PIDController(tkP, tkI, tkD);
    }


    @Override
    public void init() {
        turretmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setTargetRotation(0);
        setClaw1Pos(CLAWCLOSEPOS1);
        if (timer.milliseconds() > 1500) {
            setArmPos(IdleArm);
            setTargetHeight(IdleHeight);
        }
    }

    @Override
    public void update() {
        currentHeight = extensionLengthToHeight(encoderTicksToInches(getCurrentPosition()));
        currentRotation = getCurrentRotation();
        double power;
        double tpower;
        power = 0.2;
        tpower = 0;

        if (targetHeight == 0 && currentHeight < 0.3) {
            setLiftPower(0);
        } else if (motor1.getPower() > 0.5 && motor1.getCurrent(CurrentUnit.AMPS) > 10) {
            setLiftPower(-1);
        } else {
            power += pid.calculate(currentHeight);
            setLiftPower(power);
        }

//        if ((targetRotation - currentRotation) < -3) {
//            turretmotor.setPower(-0.5);
//        } else if ((targetRotation - currentRotation) > 3) {
//            turretmotor.setPower(0.5);
//        } else if (Math.abs((targetRotation - currentRotation)) <= 3) {
//            turretmotor.setPower(0);
//        }
        turretpower = tpower;
        if (opmode == "teleop") {
            if (Math.abs((targetRotation - currentRotation)) <= 5) {
                setTurretPower(0);
            } else if (currentHeight > 30) {
                mode = "HIGH";
                turretpid.setPID(0.65, 0.0, .009);
                tpower += turretpid.calculate(currentRotation);
                turretpower = tpower;
                setTurretPower(tpower);
            } else {
                mode = "LOW";
                turretpid.setPID(0.7, 0, 0.025);
                tpower += turretpid.calculate(currentRotation);
                turretpower = tpower;
                setTurretPower(tpower);
            }
        } else if (opmode == "auto") {
            if (Math.abs((autoRotation - currentRotation)) <= 5) {
                turretmotor.setPower(0);
            } else if (autoRotation > currentRotation) {
                if ((autoRotation - currentRotation) <= 20) {
                    turretmotor.setPower(0.1);
                } else if ((autoRotation - currentRotation) <= 60) {
                    turretmotor.setPower(0.25);
                } else if ((autoRotation - currentRotation) <= 100) {
                    turretmotor.setPower(0.4);
                } else {
                    turretmotor.setPower(0.6);
                }
            } else if (autoRotation < currentRotation) {
                if ((autoRotation - currentRotation) >= -20) {
                    turretmotor.setPower(-0.1);
                } else if ((autoRotation - currentRotation) >= -60) {
                    turretmotor.setPower(-0.25);
                } else if ((autoRotation - currentRotation) >= -100) {
                    turretmotor.setPower(-0.4);
                } else {
                    turretmotor.setPower(-0.6);
                }
            }
        }
    }

    @Override
    public void update(TelemetryPacket packet) {

    }

    public double getDistance() {
        return colorRangeSensor.getDistance(DistanceUnit.INCH);
    }

    public void setLiftPower(double power) {
        power = Range.clip(power, -0.3,MAX_POWER);
        motor2.setPower(power);
        motor1.setPower(power);
    }

    public void setTurretPower(double power) {
        power = Range.clip(power, -turretMaxSpeed,turretMaxSpeed);
        turretmotor.setPower(power);
    }

    public double getArmPosition() {
        return armServo1.getPosition();
    }

    public double getHeight() {
        return currentHeight = extensionLengthToHeight(encoderTicksToInches(getCurrentPosition()));
    }

    public double getTargetHeight() {
        return targetHeight;
    }

    public int getCurrentPosition() {
        return motor2.getCurrentPosition() - bottomOffset;
    }

    public double getTargetRotation() {
        return targetRotation;
    }

    public double getCurrentRotation() {
        return turretmotor.getCurrentPosition();
    }

    public void setClaw1Pos(double position){
        clawServo.setPosition(position);
        clawServoB.setPosition(1-position);
    }

    public void setArmPos(double position){
        armServo1.setPosition(1-position);
        armServo2.setPosition(position);
    }

    public void setTargetHeight(double height) {
        if(Math.abs(targetHeight - height) < 0.3) {
            return;
        }
        targetHeight = height;
        pid.setSetPoint(targetHeight);
    }

    public void setTargetRotation(double rotation) {
        if(Math.abs(targetRotation - rotation) < 5) {
            return;
        }
        targetRotation = rotation;
        turretpid.setSetPoint(targetRotation);
    }

    public void setAutoRotation(double rotation) {
        autoRotation = rotation;
    }

    public double getTpower() {
        return turretpower;
    }

    public static double inchesToEncoderTicks(double inches) {
        return inches / (UP_WINCH_RADIUS * Math.PI) * (TICKS_PER_REVOLUTION * MOTOR_GEAR_RATIO);
    }

    public static double encoderTicksToInches(double ticks) {
        return ticks / (TICKS_PER_REVOLUTION * MOTOR_GEAR_RATIO) * UP_WINCH_RADIUS * Math.PI;
    }

    public static double extensionLengthToHeight(double length) {
        return length * Math.sin(Math.toRadians(70));
    }

    public static double heightToExtensionLength(double height) {
        return height / Math.sin(Math.toRadians(70));
    }

    public String getTurretMode() {
        return mode;
    }

    public ElapsedTime.Resolution getTimer() {
        return timer.getResolution();

    }

    public void setOpmode(String mode) {
        opmode = mode;
    }

    public String getOpmode() {
        return opmode;
    }

    public Double getAutoRotation() {
        return autoRotation;
    }

}