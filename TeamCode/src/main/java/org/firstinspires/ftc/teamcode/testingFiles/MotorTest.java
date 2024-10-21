package org.firstinspires.ftc.teamcode.testingFiles;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(group = "testing")
public class MotorTest extends LinearOpMode {
    DcMotorEx motor;

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        double val = 0.1;
        boolean isOn = false;
        waitForStart();
        while (opModeIsActive()) {

            if (timer.milliseconds() > 100) {
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
            if (gamepad1.a) {
                isOn = !isOn;
            }

            if (isOn) {
                if (val > 0.06 && val < 0.94) {
                    motor.setPower(val);
                }
            } else {
                motor.setPower(0);
            }

            telemetry.addData("on/off", isOn);
            telemetry.addData("saved speed", val);
            telemetry.addData("current speed", motor.getPower());
            telemetry.update();
        }
    }
}


