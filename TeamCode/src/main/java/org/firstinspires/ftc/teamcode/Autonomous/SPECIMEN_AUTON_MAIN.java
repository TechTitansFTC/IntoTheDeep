package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config()
@Autonomous(name = "SpecimenMain")


public class SPECIMEN_AUTON_MAIN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;





    BNO055IMU imu;

    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");//CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");//CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");//CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");//CH 3




        OuttakeSystem outtake = new OuttakeSystem(hardwareMap);
        IntakeSystem intake = new IntakeSystem(hardwareMap);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);




        // Put initialization blocks here
        waitForStart();
        // Put run blocks here
        outtake.init();
//        //rotates backwards
//        backLeftDrive.setPower(0.3);
//        backRightDrive.setPower(-0.3);
//        frontLeftDrive.setPower(0.3);
//        frontRightDrive.setPower(-0.3);
//        sleep(9500);
//        frontRightDrive.setPower(0);
//        backLeftDrive.setPower(0);
//        backRightDrive.setPower(0);
//        frontLeftDrive.setPower(0);
        //moves to bar
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(1000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);

        outtake.topBarDeposit();

        sleep(500);//brake to place specimen
        //rotates to human player
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(200);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to human player
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(1000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates to pick up the speciman
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(400);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves forward to pick up speciman
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(100);
        //gives break to pick up speciman
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);

        outtake.pickUp();

        sleep(300);
        //moves back from the specimen, after picking it up
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(0.3);
        sleep(100);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates to the bar
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(800);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to the bar
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(940);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates to align with the bar
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(50);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to bar to drop specimen #2
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(100);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);

        outtake.topBarDeposit();

        //moves away from the bar to rotate
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(0.3);
        sleep(100);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates back to human player
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(0.3);
        sleep(450);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //moves to human player to PARk
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(0.3);
        sleep(1500);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        //rotates so that field centric works
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(0.3);
        sleep(650);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(-1);
        backRightDrive.setPower(-1);
        frontLeftDrive.setPower(-1);
        frontRightDrive.setPower(-1);
    }

}