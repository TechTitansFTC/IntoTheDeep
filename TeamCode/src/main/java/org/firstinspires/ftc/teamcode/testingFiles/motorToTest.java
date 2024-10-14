package org.firstinspires.ftc.teamcode.testingFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(group = "testing")
public class motorToTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        DcMotorEx testing = (DcMotorEx) hardwareMap.dcMotor.get("motor");
        testing.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int val = 1000;
        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                val += 1000;
            } else if (gamepad1.dpad_right) {
                val += 500;
            } else if (gamepad1.dpad_down) {
                val -= 1000;
            } else if (gamepad1.dpad_left) {
                val -= 500;
            }
            if (gamepad1.a) {
                testing.setPower(1);
            } else if (gamepad1.b) {
                testing.setPower(0);
            }
            // TODO - add limits
            testing.setTargetPosition(val);
            telemetry.addData("pos = ", val);
            telemetry.addData("power = ", testing.getPower());
            telemetry.update();
        }
    }
}