package org.firstinspires.ftc.teamcode.old.m1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "ZTest")
public class outtakeTest extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Servo servoOuttakeTurn = hardwareMap.servo.get("outtakeTurn");
        Servo servoOuttakeClose = hardwareMap.servo.get("outtakeClose");
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            double pos1 = 0.5;
            double pos2 = 0.5;
            if (gamepad1.a) {
                pos1 += 0.05;
                servoOuttakeTurn.setPosition(pos1);
            } else if (gamepad1.b) {
                pos1 -= 0.05;
                servoOuttakeTurn.setPosition(pos1);
            } else if (gamepad1.x) {
                pos2 += 0.05;
                servoOuttakeClose.setPosition(pos2);
            } else if (gamepad1.y) {
                pos2 -= 0.05;
                servoOuttakeClose.setPosition(pos2);
            }
            telemetry.addData("servo outtake turn pos = ", servoOuttakeTurn.getPosition());
            telemetry.addData("servo outtake close pos = ", servoOuttakeClose.getPosition());
            telemetry.update();
        }
    }
}