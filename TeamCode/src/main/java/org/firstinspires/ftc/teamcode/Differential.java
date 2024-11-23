package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Differential {
    private DcMotorEx motorL;
    private DcMotorEx motorR;
    //TODO: implement differential PIDs
    //TODO: find values for motors

    public Differential(HardwareMap map) {
        this.motorL = (DcMotorEx) map.get("difL");
        this.motorR = (DcMotorEx) map.get("difR");
    }

    public void init() {
        motorR.setPower(0);
        motorL.setPower(0);
    }

    public void outtakeUp() {
        motorR.setPower(-1);
        motorL.setPower(1);
    }

    public void outtakeDown() {
        motorR.setPower(1);
        motorL.setPower(-1);
    }

    public void intakeOut() {
        motorR.setPower(1);
        motorL.setPower(1);
    }

    public void intakeIn() {
        motorR.setPower(0);
        motorL.setPower(0);
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
