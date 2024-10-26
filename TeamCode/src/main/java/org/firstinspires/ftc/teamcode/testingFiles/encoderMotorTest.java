package org.firstinspires.ftc.teamcode.testingFiles;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(group = "testing")
public class encoderMotorTest extends LinearOpMode {
    public void runOpMode () throws

    InterruptedException{

        DcMotorEx motorRightSlides = (DcMotorEx) hardwareMap.dcMotor.get("motor1");
        DcMotorEx motorLeftSlides = (DcMotorEx) hardwareMap.dcMotor.get("motor2");

        motorRightSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        int position = 0;
        int prevposition = 0;
        boolean a = false;

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {


            if (gamepad2.a) {
                motorLeftSlides.setDirection(DcMotorSimple.Direction.REVERSE);
                motorRightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorLeftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorRightSlides.setVelocity(2000);
                motorLeftSlides.setVelocity(2000);
                motorRightSlides.setTargetPosition(2000);
                motorLeftSlides.setTargetPosition(2000);
                motorRightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad2.b) {
                motorRightSlides.setDirection(DcMotorSimple.Direction.REVERSE);
                motorLeftSlides.setDirection(DcMotorSimple.Direction.FORWARD);
                motorRightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorLeftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorRightSlides.setVelocity(2000);
                motorLeftSlides.setVelocity(2000);
                motorRightSlides.setTargetPosition(-2000);
                motorLeftSlides.setTargetPosition(2000);
                motorRightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
//            if (gamepad2.left_stick_y != 0) {
//                motorRightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                motorLeftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                motorRightSlides.setVelocity(-signum(gamepad2.left_stick_y) * 1900);
//                motorLeftSlides.setVelocity(-signum(gamepad2.left_stick_y) * 2000);
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
//            if (prevposition == position && gamepad2.left_stick_y == 0) {
//                motorRightSlides.setTargetPosition(position);
//                motorLeftSlides.setTargetPosition(position);
//                motorRightSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                motorLeftSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                motorRightSlides.setVelocity(0);
//                motorLeftSlides.setVelocity(0);
//                prevposition = position;
//            }

            telemetry.addData("right pos: ", motorRightSlides.getCurrentPosition());
            telemetry.addData("left pos: ", motorLeftSlides.getCurrentPosition());
            telemetry.update();
        }
    }
}