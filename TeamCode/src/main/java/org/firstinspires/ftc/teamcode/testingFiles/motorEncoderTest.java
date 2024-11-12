package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class motorEncoderTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        DcMotorEx testing = (DcMotorEx) hardwareMap.dcMotor.get("motor1");
        testing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int val = 50;


        waitForStart();

        testing.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testing.setTargetPosition(val);
        if(isStopRequested()) return;
        while (opModeIsActive()) {

            if (timer.milliseconds() > 100) {
                if (gamepad1.dpad_up) {
                    val += 100;
                } else if (gamepad1.dpad_right) {
                    val += 50;
                } else if (gamepad1.dpad_down) {
                    val -= 100;
                } else if (gamepad1.dpad_left) {
                    val -= 50;
                }
                timer.reset();
            }
            if (gamepad1.a) {
                testing.setPower(1);
            } else if (gamepad1.b) {
                testing.setPower(0);
            }

            if (val > 0) {
                testing.setTargetPosition(val);
            }
            telemetry.addData("pos = ", val);
            telemetry.addData("power = ", testing.getPower());
            telemetry.update();
        }
    }
}
