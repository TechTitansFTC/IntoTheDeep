package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;

@TeleOp(group = "ZTest")
public class servoLimitTest extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.servo.get("servo");
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
//            servo1.setPosition(0);
            //.52 Out - 67 in
            if (gamepad1.a) {
                servo.setPosition(servo.getPosition()+0.05);
                TimeUnit.MILLISECONDS.sleep(350);
            }
            if (gamepad1.b) {
                servo.setPosition(servo.getPosition()-0.05);
                TimeUnit.MILLISECONDS.sleep(350);
            }
            if (gamepad1.x) {
                servo.setPosition(servo.getPosition()-0.01);
                TimeUnit.MILLISECONDS.sleep(350);
            }
            if (gamepad1.y) {
                servo.setPosition(servo.getPosition()+0.01);
                TimeUnit.MILLISECONDS.sleep(350);
            }
            telemetry.addData("servo pos.", servo.getPosition());
            telemetry.update();
        }
    }
}