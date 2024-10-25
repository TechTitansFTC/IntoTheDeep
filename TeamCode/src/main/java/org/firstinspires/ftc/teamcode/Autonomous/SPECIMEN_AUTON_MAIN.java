package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Config()
@Autonomous(name = "SpecimenMain")


public class SPECIMEN_AUTON_MAIN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor slidesLOuttake;
    DcMotor slidesROuttake;
    Servo claw;
    Servo rotateR;
    Servo rotateL;

    BNO055IMU imu;

    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        OuttakeSystem outtake = new OuttakeSystem();{

        }
        OuttakeSystem (hardwareMap hardwareMap); {
            this.slidesLOuttake = (DcMotorEx) hardwareMap.get("outtakeLeft");
            this.slidesROuttake = (DcMotorEx) hardwareMap.get("outtakeRight");
            this.claw = hardwareMap.servo.get("outClaw");
            this.rotateL = hardwareMap.servo.get("outRotateL");
            this.rotateR = hardwareMap.servo.get("outRotateR");
        }



        // Put initialization blocks here
        waitForStart();
        // Put run blocks here

        //rotates backwards
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(9500);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to bar
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(5000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        sleep(500);//brake to place specimen
        //rotates to human player
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(6500);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to human player
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(8000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates to pick up the speciman
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(3000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves forward to pick up speciman
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(800);
        //gives break to pick up speciman
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        sleep(800);
        //moves back from the specimen, after picking it up
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(0.3);
        sleep(800);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates to the bar
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(6500);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to the bar
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(7500);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates to align with the bar
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(3000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to bar to drop specimen #2
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(800);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves away from the bar to rotate
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(0.3);
        sleep(800);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates back to human player
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(0.3);
        sleep(3500);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to human player to PARk
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(0.3);
        sleep(12150);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates so that field centric works
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(0.3);
        sleep(6000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
    }

}