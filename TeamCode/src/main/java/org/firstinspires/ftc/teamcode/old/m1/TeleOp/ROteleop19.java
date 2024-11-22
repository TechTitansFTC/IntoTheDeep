package org.firstinspires.ftc.teamcode.old.m1.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ROteleop19 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor FL = hardwareMap.dcMotor.get("frontLeft");
        DcMotor BL = hardwareMap.dcMotor.get("backLeft");
        DcMotor FR = hardwareMap.dcMotor.get("frontRight");
        DcMotor BR = hardwareMap.dcMotor.get("backRight");
        DcMotorEx motorRightSlides = (DcMotorEx) hardwareMap.dcMotor.get("RS");
        DcMotorEx motorLeftSlides = (DcMotorEx)  hardwareMap.dcMotor.get("LS");
        Servo servoRightOuttakeRotate = hardwareMap.servo.get("ROS");
        Servo servoFrontOuttakeRotate = hardwareMap.servo.get("FOS");
        motorRightSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            servoRightOuttakeRotate.setPosition(0.28);
            servoFrontOuttakeRotate.setPosition(0.45);


            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            FL.setPower(frontLeftPower);
            BL.setPower(backLeftPower);
            FR.setPower(frontRightPower);
            BR.setPower(backRightPower);
            if(gamepad2.a){
                if(servoRightOuttakeRotate.getPosition()!=0.15){
                    servoRightOuttakeRotate.setPosition(0.15);
                }
                else {
                    servoRightOuttakeRotate.setPosition(0.28);
                }
            }
            if(gamepad2.b){
                if(servoRightOuttakeRotate.getPosition()!=0.45){
                    servoRightOuttakeRotate.setPosition(0.45);
                }
                else {
                    servoRightOuttakeRotate.setPosition(0.6);
                }
            }

        }
    }
}
