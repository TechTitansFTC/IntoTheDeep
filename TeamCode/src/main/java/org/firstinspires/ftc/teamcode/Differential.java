package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Differential {
    private DcMotorEx motorL;
    private DcMotorEx motorR;

    ElapsedTime timer = new ElapsedTime();

    //TODO: implement differential PIDs
    //TODO: find values for motors

    public Differential(HardwareMap map) {
        this.motorL = (DcMotorEx) map.get("difL");
        this.motorR = (DcMotorEx) map.get("difR");
    }

    public void init() {
        motorR.setPower(0);
        motorL.setPower(0);
        timer.reset();
    }

    public void slidesControl(boolean up, boolean down, boolean out, boolean in) {
        boolean inputReceived = up || down || out || in;
        if (timer.milliseconds() > 500) {
            if (inputReceived) {
                if (up) {
                    outtakeUp();
                } else if (down) {
                    outtakeDown();
                } else if (out) {
                    intakeOut();
                } else {
                    intakeIn();
                }
                timer.reset();
            } else {
                slidesOff();
            }
        }
    }

    public void outtakeUp(int t) {
        timer.reset();
        motorR.setPower(0.6);
        motorL.setPower(-0.6);
        while (timer.milliseconds() <= t){

        }
        motorR.setPower(0);
        motorL.setPower(0);
    }

    public void outtakeDown(int t) {
        timer.reset();
        motorR.setPower(-0.6);
        motorL.setPower(0.6);
        while (timer.milliseconds() <= t){

        }
        motorR.setPower(0);
        motorL.setPower(0);
    }

    public void intakeOut() {
        motorR.setPower(-0.6);
        motorL.setPower(-0.6);
    }

    public void intakeIn() {
        motorR.setPower(0.6);
        motorL.setPower(0.6);
    }

    public void slidesOff() {
        motorR.setPower(0);
        motorL.setPower(0);
    }

    public boolean withinRange(int target, int tolerance, boolean leftMotor) {
        if (leftMotor) {
            return Math.abs(motorL.getCurrentPosition() - target) < tolerance;
        } else {
            return Math.abs(motorR.getCurrentPosition() - target) < tolerance;
        }
    }
}
