package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class OuttakeTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Servo shoulderL = hardwareMap.get(Servo.class, "rotateML"); // main U-D
        Servo shoulderR = hardwareMap.get(Servo.class, "rotateMR"); // main U-D
        Servo elbowL = hardwareMap.get(Servo.class, "elbowL"); // subset U-D
        Servo elbowR = hardwareMap.get(Servo.class, "elbowR");
        Servo wrist = hardwareMap.get(Servo.class, "wrist"); // L-R
        Servo claw = hardwareMap.get(Servo.class, "claw");
        Servo[] servos = {shoulderL, shoulderR, elbowL, elbowR, wrist, claw};
        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) {return;}
        timer.reset();
        int testServoNum = 0;
        while (opModeIsActive()) {
            Servo temp = servos[testServoNum];
            if (timer.milliseconds() > 300) {
                if (gamepad1.dpad_up) {
                    temp.setPosition(temp.getPosition() + 0.05);
                } else if (gamepad1.dpad_down) {
                    temp.setPosition(temp.getPosition() - 0.05);
                } else if (gamepad1.dpad_right) {
                    temp.setPosition(temp.getPosition() + 0.01);
                } else if (gamepad1.dpad_left) {
                    temp.setPosition(temp.getPosition() - 0.01);
                }
                timer.reset();
            }

            if (gamepad1.right_bumper) {
                testServoNum += 1;
            } else if (gamepad1.left_bumper) {
                testServoNum -= 1;
            }

            telemetry.addData("Testing Servo", servos[testServoNum]);

            telemetry.addData("pos", shoulderL.getPosition());
            telemetry.addData("pos", shoulderR.getPosition());
            telemetry.addData("pos", elbowL.getPosition());
            telemetry.addData("pos", wrist.getPosition());
            telemetry.addData("pos", claw.getPosition());
            telemetry.update();
            if (testServoNum >= servos.length) {
                testServoNum = 0;
            }
        }
    }
}
