package org.firstinspires.ftc.teamcode.MotorPowerAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;

@Autonomous(name = "A_AUTON_SPECIMEN")
public class A_AUTON_SPECIMEN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    OuttakeSystem outtake;

    IMU imu;

    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft"); //CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight"); //CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft"); //CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight"); //CH 3

        //life slides
        outtake.getSlidesR().setPower(0.5);
        outtake.getSlidesL().setPower(0.5);
        sleep(2300);
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);
        sleep(1000);

        //move to bar
        backLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        frontRightDrive.setPower(-0.3);
        sleep(1300);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);

        //lift slides down and score
        outtake.getSlidesR().setPower(-0.5);
        outtake.getSlidesL().setPower(-0.5);
        sleep(1300);
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);
        outtake.clawOpen();

        //rotate
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        frontRightDrive.setPower(-0.3);
        sleep(2000);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);

        //move back to start
        frontRightDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        frontLeftDrive.setPower(-0.3);
        backRightDrive.setPower(-0.3);
        sleep(4000);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);

        //Park
        frontRightDrive.setPower(-0.3);
        backLeftDrive.setPower(-0.3);
        frontLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);
        sleep(5000);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        sleep(1000);//brake to place specimen

    }
}


