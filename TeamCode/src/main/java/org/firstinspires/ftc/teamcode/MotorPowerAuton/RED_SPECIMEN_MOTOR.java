package org.firstinspires.ftc.teamcode.MotorPowerAuton;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

//import org.firstinspires.ftc.teamcode.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;

@Config()
@Autonomous(name = "RED_SPECIMEN_MOTOR", group = "MOTOR_POWER")



public class RED_SPECIMEN_MOTOR extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    
    Servo intakeSlidesL;
    Servo intakeSlidesR;
    Servo intakeWristL;
    Servo intakeWristR;
    CRServo leftIntake;
    CRServo rightIntake;
    
    Servo servoRightOuttakeRotate;
    Servo servoLeftOuttakeRotate;
    Servo servoFrontOuttakeRotate;
    DcMotorEx motorRightSlides;
    DcMotorEx motorLeftSlides;
    
    final double INTAKE_DOWN = 0.85;
    final double INTAKE_UP = 0.43;
    final double OUTTAKE_LOW = 0.2;
    final double OUTTAKE_HIGH = 0.85;
    final int HIGH_CHAMBER_TOP = 700;
    
    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");//CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");//CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");//CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");//CH 3

        intakeSlidesL = hardwareMap.get(Servo.class, "inSlidesL");
        intakeSlidesR = hardwareMap.get(Servo.class, "inSlidesR");
        intakeWristL = hardwareMap.get(Servo.class, "inRotateL");
        intakeWristR = hardwareMap.get(Servo.class, "inRotateL");
        leftIntake = hardwareMap.get(CRServo.class, "geckoL");
        rightIntake = hardwareMap.get(CRServo.class, "geckoR");
        
        servoLeftOuttakeRotate = hardwareMap.servo.get("outtakeLeft");
        servoRightOuttakeRotate = hardwareMap.servo.get("outtakeRight");// CH 0
        servoFrontOuttakeRotate = hardwareMap.servo.get("outClaw");//CH 1
        motorRightSlides = (DcMotorEx) hardwareMap.dcMotor.get("outRotateR");//EH 0
        motorLeftSlides = (DcMotorEx)  hardwareMap.dcMotor.get("outtakeLeft");//EH 1
        
        
        OuttakeSystem outtake = new OuttakeSystem(hardwareMap);
//        IntakeSystem intake = new IntakeSystem(hardwareMap);
        
        
        

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

        
        outtake.slideSet(HIGH_CHAMBER_TOP);
        outtake.rotateSet(0);
        outtake.clawClosed();
        frontRightDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        sleep(1000);//start to chamber
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);
        outtake.slideSet(HIGH_CHAMBER_TOP-200);
        outtake.clawOpen();
        frontRightDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        backLeftDrive.setPower(0.3);
        sleep(300);//start to cha mber
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
        intakeWristL.setPosition(INTAKE_DOWN);
        intakeWristR.setPosition(INTAKE_DOWN);
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

