package org.firstinspires.ftc.teamcode.Telops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Functions;


@TeleOp
public class JustForAnish extends LinearOpMode {
    @Override

    public void runOpMode() throws InterruptedException {
        Functions r = new Functions(hardwareMap);
        ElapsedTime timer = new ElapsedTime();
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor leftFront = hardwareMap.dcMotor.get("leftFront");
        DcMotor leftBack = hardwareMap.dcMotor.get("leftBack");
        DcMotor rightFront = hardwareMap.dcMotor.get("rightFront");
        DcMotor rightBack = hardwareMap.dcMotor.get("rightBack");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);

        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();
        r.init();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y - gamepad1.right_stick_y - gamepad2.left_stick_y - gamepad2.right_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x + gamepad2.left_stick_x;
            double rx = gamepad1.right_stick_x + gamepad2.right_stick_x;
            if (gamepad2.left_bumper){
                y*=0.4;
                x*=0.4;
                rx*=0.4;
            }else if (gamepad2.right_bumper){
                y*=0.8;
                x*=0.8;
                rx*=0.8;
            }
            if(r.isdeploy()){
                y*=0.3;
                x*=0.3;
                rx*=0.3;
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

            leftFront.setPower(frontLeftPower);
            leftBack.setPower(backLeftPower);
            rightFront.setPower(frontRightPower);
            rightBack.setPower(backRightPower);

            if (gamepad1.x){
                r.start();
            }
            if (gamepad1.y){
                r.score();
            }
            if (gamepad1.b){
                r.pulldown();

            }
            if (gamepad1.dpad_down){
                r.inLiftDown();
            }
            if (gamepad1.dpad_left){
                r.inExtend();
            }
            if (gamepad1.dpad_right){
                r.inExtendIn();

            }
            if (gamepad1.dpad_up) {
                r.inLiftUp();

            }
            if (gamepad1.left_bumper){
                r.inClawOpen();

            }
            if (gamepad1.right_bumper){
                r.inClawClose();
            }
            if(gamepad1.left_trigger>0.2){
                r.changeWrist(-0.1);
                timer.reset();
                while (timer.milliseconds()<300){

                }
            }
            if(gamepad1.right_trigger>0.2){
                r.changeWrist(0.1);
                timer.reset();
                while (timer.milliseconds()<300){

                }
            }
            telemetry.addData("isdeploy:", r.isdeploy());
            telemetry.update();
        }
    }
}