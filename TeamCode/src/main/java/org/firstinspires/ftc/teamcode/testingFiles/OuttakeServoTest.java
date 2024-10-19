
package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(group = "testing")
class outtakeServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        Servo servo1 = hardwareMap.servo.get("servo1");
        Servo servo2 = hardwareMap.servo.get("servo2");
        double val = 0.1;
        double val2 = 0.1;

        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()) {
            if (timer.milliseconds() > 250) {
                if (gamepad1.dpad_up) {
                    val += 0.1;
                } else if (gamepad1.dpad_right) {
                    val += 0.05;
                } else if (gamepad1.dpad_down) {
                    val -= 0.1;
                } else if (gamepad1.dpad_left) {
                    val -= 0.05;
                }
                timer.reset();
            }
            if (val > 0.06 && val < 0.94) {
                servo1.setPosition(val);

            }
            if (timer.milliseconds() > 250) {
                if (gamepad1.a) {
                    val2 += 0.1;
                } else if (gamepad1.b) {
                    val2 += 0.05;
                } else if (gamepad1.x) {
                    val2 -= 0.1;
                } else if (gamepad1.y) {
                    val2 -= 0.05;
                }
                timer.reset();
            }
            if (val2 > 0.06 && val2 < 0.94) {
                servo2.setPosition(val);

            }
            telemetry.addData("pos = ", val);
            telemetry.addData("pos2 = ", val2);
            telemetry.update();
            telemetry.addData("pos = ", val);
            telemetry.update();
        }
    }
}
