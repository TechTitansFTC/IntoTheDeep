package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class servoTestOuttake extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Servo shoulderL = hardwareMap.get(Servo.class, "rotateML"); // main U-D
        Servo shoulderR = hardwareMap.get(Servo.class, "rotateMR"); // main U-D
        Servo elbow = hardwareMap.get(Servo.class, "elbow"); // subset U-D
        Servo wrist = hardwareMap.get(Servo.class, "wrist"); // L-R
        Servo claw = hardwareMap.get(Servo.class, "claw");
        ElapsedTime timer = new ElapsedTime();
        waitForStart();
        if (isStopRequested()) {return;}
        timer.reset();
        int testServoNum = 1;
        String servo = "";
        while (opModeIsActive()) {
            if (timer.milliseconds() > 300) {
                if (gamepad1.dpad_up) {
                    servo.setPosition(servo.getPosition() + 0.05);
                } else if (gamepad1.dpad_down) {
                    servo.setPosition(servo.getPosition() - 0.05);
                } else if (gamepad1.dpad_right) {
                    servo.setPosition(servo.getPosition() + 0.01);
                } else if (gamepad1.dpad_left) {
                    servo.setPosition(servo.getPosition() - 0.01);
                }
                timer.reset();
            }

            if (gamepad1.right_bumper) {
                testServoNum += 1;
            } else if (gamepad1.left_bumper) {
                testServoNum -= 1;
            }

            if (testServoNum == 1) {
                servo = "shoulderL";
            } else if (testServoNum == 2) {
                servo = "shoulderR";
            } else if (testServoNum == 3) {
                servo = "elbow";
            } else if (testServoNum == 4) {
                servo = "wrist";
            } else if (testServoNum == 5) {
                servo = "claw";
            }

            telemetry.addData("Testing Servo", servo);

            telemetry.addData("pos", shoulderL.getPosition());
            telemetry.addData("pos", shoulderR.getPosition());
            telemetry.addData("pos", elbow.getPosition());
            telemetry.addData("pos", wrist.getPosition());
            telemetry.addData("pos", claw.getPosition());
            telemetry.update();
        }
    }
}
