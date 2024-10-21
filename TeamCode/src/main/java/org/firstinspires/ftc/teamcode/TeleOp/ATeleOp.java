package org.firstinspires.ftc.teamcode.TeleOp;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ATeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
        DcMotorEx motorRightSlides = (DcMotorEx) hardwareMap.dcMotor.get("RS");
        DcMotorEx motorLeftSlides = (DcMotorEx)  hardwareMap.dcMotor.get("LS");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //servo config
        Servo servoRightOuttakeRotate = hardwareMap.servo.get("ROS");
        Servo servoFrontOuttakeRotate = hardwareMap.servo.get("FOS");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
//            servoRightOuttakeRotate.setPosition(0.25);
//            servoFrontOuttakeRotate.setPosition();

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

//            int position = 0;
//            int prevposition = 0;
//            boolean a = false;

//            if (gamepad2.left_stick_y != 0) {
//                motorRightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                motorLeftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                motorRightSlides.setVelocity(-signum(gamepad2.left_stick_y)*1900);
//                motorLeftSlides.setVelocity(-signum(gamepad2.left_stick_y)*2000);
//                position = motorLeftSlides.getCurrentPosition();
//                prevposition = position;
//                a = true;
//            } else if (a) {
//                motorRightSlides.setVelocity(0);
//                motorLeftSlides.setVelocity(0);
//                motorRightSlides.setTargetPosition(motorLeftSlides.getCurrentPosition());
//                motorRightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                motorRightSlides.setVelocity(1000);
//                position = motorLeftSlides.getCurrentPosition();
//                prevposition = position;
//                a = false;
//            }
//            if (prevposition != position && gamepad2.left_stick_y == 0) {
//                motorRightSlides.setTargetPosition(position);
//                motorLeftSlides.setTargetPosition(position);
//                motorRightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                motorLeftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                motorRightSlides.setVelocity(1000);
//                motorLeftSlides.setVelocity(1000);
//                prevposition=position;
//            }

        }
    }
}