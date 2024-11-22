package org.firstinspires.ftc.teamcode.old.m1.TeleOp;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.concurrent.TimeUnit;

@TeleOp
public class FOteleop19 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor FL = hardwareMap.dcMotor.get("frontLeft");//0
        DcMotor BL = hardwareMap.dcMotor.get("backLeft");//1
        DcMotor FR = hardwareMap.dcMotor.get("frontRight");//3
        DcMotor BR = hardwareMap.dcMotor.get("backRight");//2
        Servo servoRightOuttakeRotate = hardwareMap.servo.get("ROS");
        Servo servoFrontOuttakeRotate = hardwareMap.servo.get("FOS");
        DcMotorEx motorRightSlides = (DcMotorEx) hardwareMap.dcMotor.get("RS");
        DcMotorEx motorLeftSlides = (DcMotorEx)  hardwareMap.dcMotor.get("LS");
        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        motorRightSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftSlides.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;
        servoRightOuttakeRotate.setPosition(0.15);
        servoFrontOuttakeRotate.setPosition(0.6);
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y - gamepad1.right_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing


            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;
            if(gamepad2.dpad_up){
                motorLeftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorRightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorRightSlides.setPower(0.3);
                motorLeftSlides.setPower(0.3);
                TimeUnit.MILLISECONDS.sleep(350);
                motorRightSlides.setPower(0);
                motorLeftSlides.setPower(0);
            }
            if(gamepad2.dpad_down){
                motorLeftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorRightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorRightSlides.setPower(-0.3);
                motorLeftSlides.setPower(-0.3);
                TimeUnit.MILLISECONDS.sleep(350);
                motorRightSlides.setPower(0);
                motorLeftSlides.setPower(0);
            }
            if(gamepad2.a){
                servoRightOuttakeRotate.setPosition(0.28);

                TimeUnit.MILLISECONDS.sleep(350);

            }

            if(gamepad2.x){

                servoRightOuttakeRotate.setPosition(0.15);
                TimeUnit.MILLISECONDS.sleep(350);

            }

            if(gamepad2.y){
                servoFrontOuttakeRotate.setPosition(0.1);
                TimeUnit.MILLISECONDS.sleep(350);

            }

            if(gamepad2.b){

                    servoFrontOuttakeRotate.setPosition(0.6);
                TimeUnit.MILLISECONDS.sleep(350);


            }


            if(gamepad1.left_bumper){
                FL.setPower(frontLeftPower*0.3);
                FR.setPower(frontRightPower*0.3);
                BL.setPower(backLeftPower*0.3);
                BR.setPower(backRightPower*0.3);
            }
            else{
                FL.setPower(frontLeftPower*0.7);
                FR.setPower(frontRightPower*0.7);
                BL.setPower(backLeftPower*0.7);
                BR.setPower(backRightPower*0.7);
            }
            telemetry.addData("rotate pos",servoRightOuttakeRotate.getPosition());
            telemetry.addData("claw pos",servoFrontOuttakeRotate.getPosition());
            telemetry.update();
        }
    }
}