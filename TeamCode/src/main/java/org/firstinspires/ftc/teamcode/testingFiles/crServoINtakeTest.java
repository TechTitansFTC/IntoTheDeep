package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class crServoINtakeTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        CRServo servo1 = (CRServo) hardwareMap.crservo.get("servo1");
        CRServo servo2 = (CRServo) hardwareMap.crservo.get("servo2");
        int val = 0;
        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()) {
            if (timer.milliseconds() > 250) {
                if (gamepad1.a) {
                    val = 1;
                } else if (gamepad1.b) {
                    val = -1;
                } else if (gamepad1.x) {
                    val = 0;
                }
                timer.reset();
            }
            servo1.setPower(val);
            servo2.setPower(val * -1);
            telemetry.addData("pos = ", val);
            telemetry.update();
        }
    }
}
