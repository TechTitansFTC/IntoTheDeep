package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@TeleOp(group = "testing")
public class servoTest extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.servo.get("servo");
        double currentPos = 0.0; 
        boolean isOn = true;
        double baseValue = 0.0;

        double bigVal = 0.1;
        double smallVal = 0.05;

        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            if (timer.milliseconds() > 350) { // prevent spam break
                // change the main var
                if (gamepad1.dpad_down) {
                    currentPos -= bigVal;
                } else if (gamepad1.dpad_up) {
                    currentPos += bigVal;
                } else if (gamepad1.dpad_left) {
                    currentPos -= smallVal;
                } else if (gamepad1.dpad_right) {
                    currentPos += smallVal;
                }

                //allows for changing of increments
                // x - small
                // b - largest (0.1 & 0.05)
                if (gamepad1.x) {
                    bigVal = 0.05;
                    smallVal = 0.01;
                } else if (gamepad1.b) {
                    bigVal = 0.1;
                    smallVal = 0.05;
                }

                // if currentPos is the same as the servoPos
                // saves the current value as a safe value
                if (gamepad1.y && currentPos == servo.getPosition()) {
                    baseValue = currentPos;
                }
            }


            if (gamepad1.a) {
                isOn = !isOn; // panic button toggle
            }

            if (isOn && currentPos != servo.getPosition()) { // if it's on and the intended pos changed
                servo.setPosition(currentPos);
                timer.reset();
            } else { // panic button usage
                servo.setPosition(baseValue);
                TimeUnit.MILLISECONDS.sleep(350);
            }

            telemetry.addData("servo pos:", servo.getPosition());
            telemetry.addData("var val:", currentPos);
            telemetry.addData("base val:", baseValue);
            telemetry.addData("big var:", bigVal);
            telemetry.addData("small var:", smallVal);
            telemetry.update();
        }
    }
}