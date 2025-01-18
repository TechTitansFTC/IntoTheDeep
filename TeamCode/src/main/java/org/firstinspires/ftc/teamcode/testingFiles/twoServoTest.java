package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class twoServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        Servo servo1 = hardwareMap.servo.get("servo1");
        Servo servo2 = hardwareMap.servo.get("servo2");
        servo1.setDirection(Servo.Direction.REVERSE);
        double val = 0;
        double val2 = 1;
        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()) {
            if (timer.milliseconds() > 250) {
                if (gamepad1.dpad_up) {
                    val += 0.1;
                    val2 -= 0.1;
                } else if (gamepad1.dpad_right) {
                    val += 0.05;
                    val2 -= 0.05;
                } else if (gamepad1.dpad_down) {
                    val += 0.1;
                    val2 -= 0.1;
                } else if (gamepad1.dpad_left) {
                    val -= 0.05;
                    val2 -= 0.05;
                }
                timer.reset();
            }
            if (val > -1 && val < 1.1) {
                servo1.setPosition(val);
                servo2.setPosition(val2);
            }

            telemetry.addData("pos = ", servo1.getPosition());
            telemetry.addData("pos2 = ", servo2.getPosition());
            telemetry.update();
        }
    }
}
