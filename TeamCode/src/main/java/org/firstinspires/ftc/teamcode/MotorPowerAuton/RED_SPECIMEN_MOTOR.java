package org.firstinspires.ftc.teamcode.MotorPowerAuton;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@Config()
@Autonomous(name = "RED_SPECIMEN_MOTOR", group = "MOTOR_POWER")



public class RED_SPECIMEN_MOTOR extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    Servo intakeWristL;
    Servo intakeWristR;
    CRServo leftIntake;
    CRServo rightIntake;
    Servo servoRightOuttakeRotate;
    Servo servoFrontOuttakeRotate;
    DcMotorEx motorRightSlides;
    DcMotorEx motorLeftSlides;
    
    final double intakeDown = 0.85;
    final double intakeUp = 0.43;
    
    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");//CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");//CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");//CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");//CH 3
        intakeWristL = hardwareMap.get(Servo.class, "intakeWristL");
        intakeWristR = hardwareMap.get(Servo.class, "intakeWristL");
        leftIntake = hardwareMap.get(CRServo.class, "intakeWristL");
        rightIntake = hardwareMap.get(CRServo.class, "intakeWristL");
        servoRightOuttakeRotate = hardwareMap.servo.get("ROS");// CH 0
        servoFrontOuttakeRotate = hardwareMap.servo.get("FOS");//CH 1
        motorRightSlides = (DcMotorEx) hardwareMap.dcMotor.get("RS");//EH 0
        motorLeftSlides = (DcMotorEx)  hardwareMap.dcMotor.get("LS");//EH 1

        // Put initialization blocks here
        waitForStart();
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftSlides.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorRightSlides.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightIntake.setDirection(CRServo.Direction.REVERSE);
        motorLeftSlides.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRightDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        sleep(1000);//start to chamber
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontRightDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        backLeftDrive.setPower(0.3);
        sleep(300);//start to chamber
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontRightDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        sleep(1000);//turn
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontRightDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        backLeftDrive.setPower(0.3);
        sleep(1000);//strafe
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        intakeWristL.setPosition(intakeDown);
        intakeWristR.setPosition(intakeDown);
        leftIntake.setPower(-1);
        rightIntake.setPower(-1);
        frontRightDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        backLeftDrive.setPower(0.3);
        sleep(500);//forward
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontRightDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        sleep(1000);//back
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        frontRightDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        sleep(1000);//turn to obs
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);


    }

}

