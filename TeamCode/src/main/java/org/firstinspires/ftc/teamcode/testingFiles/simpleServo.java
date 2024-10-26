package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class simpleServo extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.get(Servo.class, "servo");
        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) {return;}
        timer.reset();
        while (opModeIsActive()) {
            if (timer.milliseconds() > 300) {
                if (gamepad1.dpad_up) {
                    servo.setPosition(servo.getPosition() + 0.05);
                } else if (gamepad1.dpad_down) {
                    servo.setPosition(servo.getPosition() - 0.05);
                }
                timer.reset();
            }

            telemetry.addData("pos", servo.getPosition());
            telemetry.update();     
        }
    }
}
