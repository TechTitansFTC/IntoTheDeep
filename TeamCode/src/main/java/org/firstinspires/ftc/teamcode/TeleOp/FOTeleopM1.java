package org.firstinspires.ftc.teamcode.TeleOp;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;

import java.util.concurrent.TimeUnit;

@TeleOp
public class FOTeleopM1 extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;



    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");//CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");//CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");//CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");//CH 3


        OuttakeSystem outtake = new OuttakeSystem(hardwareMap);
        IntakeSystem intake = new IntakeSystem(hardwareMap);


        boolean notReverse = true;



        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.

        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();

        intake.init();
        outtake.init();
//        double intakeSlidePos = intake.slidesIn;

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y, x, rx;
            y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;
            if (gamepad1.left_bumper) {
                y *= 0.2;
                x *= 0.2;
                rx *= 0.2;
            } else if (gamepad1.right_bumper) {
                y *= 0.4;
                x *= 0.4;
                rx *= 0.4;
            }else{
                y *= 0.65;
                x *= 0.65;
                rx *= 0.65;
            }

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
            //TODO: fix outtake stuff
//            if(gamepad1.left_trigger > 0){
//                while(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) != 0){
//                    frontLeftDrive.setPower(-1);
//                    backRightDrive.setPower(1);
//                }
//                frontLeftDrive.setPower(0);
//                backRightDrive.setPower(0);
//            }if(gamepad1.right_trigger > 0){
//                while(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) != 0){
//                    frontRightDrive.setPower(-1);
//                    backLeftDrive.setPower(1);
//                }
//                frontLeftDrive.setPower(0);
//                backRightDrive.setPower(0);
//            }
//            if (gamepad2.dpad_up) {
//                outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                outtake.getSlidesR().setPower(1);
//                outtake.getSlidesL().setPower(1);
//                TimeUnit.MILLISECONDS.sleep(500);
//                outtake.getSlidesR().setPower(0);
//                outtake.getSlidesL().setPower(0);
//            }
//            if (gamepad2.dpad_down) {
//                outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                outtake.getSlidesR().setPower(-1);
//                outtake.getSlidesL().setPower(-1);
//                TimeUnit.MILLISECONDS.sleep(500);
//                outtake.getSlidesR().setPower(0);
//                outtake.getSlidesL().setPower(0);
//            }
            if (gamepad2.left_bumper) {
                outtake.clawOpen();
            }
            if (gamepad2.left_trigger > 0) {
                outtake.rotateUp();
            }
            if (gamepad2.right_bumper) {
                outtake.clawClosed();
            }
            if (gamepad2.right_trigger > 0) {
                outtake.rotateDown();
            }
            //TODO: setup manual control slides
            if (gamepad2.y) {
                intake.slideSet(true);
            }
            if (gamepad2.a) {
                intake.slideSet(false);
            }
            if (gamepad2.right_trigger > 0) {
                intake.rotateToggle();
            }
            if (gamepad2.right_bumper) {
                if (notReverse) {
                    intake.intakeOnToggle();
                } else {
                    intake.reverseIntakeOnToggle();
                }
            }
            if (gamepad1.right_bumper) {
                notReverse = !notReverse;
            }

            if (gamepad1.dpad_up) botHeading = 0;
            if (gamepad1.dpad_left) botHeading = 90;
            if (gamepad1.dpad_up) botHeading = 180;
            if (gamepad1.dpad_right) botHeading = 270;

            frontLeftDrive.setPower(frontLeftPower);
            frontRightDrive.setPower(frontRightPower);
            backLeftDrive.setPower(backLeftPower);
            backRightDrive.setPower(backRightPower);

            telemetry.addData(" botheading",botHeading);
            telemetry.addData("currheading",imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

            telemetry.update();
        }
    }
}