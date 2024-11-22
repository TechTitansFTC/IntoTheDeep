package org.firstinspires.ftc.teamcode.old.m1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "ZTest")
public class intakeTest extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        CRServo servoIntakeRight = (CRServo) hardwareMap.servo.get("intakeRight");
        CRServo servoIntakeLeft = (CRServo) hardwareMap.servo.get("intakeLeft");
        CRServo servoIntakeFront = (CRServo) hardwareMap.servo.get("intakeFront");
        Servo servoIntakeTurn = hardwareMap.servo.get("intakeTurn");
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            double pos = 0.5;
            if (gamepad1.left_bumper) {
                servoIntakeRight.setPower(1);
                servoIntakeLeft.setPower(1);
                servoIntakeFront.setPower(1);
            } else if (gamepad1.right_bumper) {
                servoIntakeRight.setPower(0);
                servoIntakeLeft.setPower(0);
                servoIntakeFront.setPower(0);
            } else if (gamepad1.a) {
                pos += 0.05;
                servoIntakeTurn.setPosition(pos);
            } else if (gamepad1.b) {
                pos -= 0.05;
                servoIntakeTurn.setPosition(pos);
            }
            telemetry.addData("servo intake pos = ", servoIntakeTurn.getPosition());
            telemetry.update();
        }
    }
}