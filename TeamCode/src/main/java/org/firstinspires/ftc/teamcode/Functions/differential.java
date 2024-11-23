package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class differential {
    private DcMotorEx motorL;
    private DcMotorEx motorR;

    public differential (HardwareMap map) {
        this.motorL = (DcMotorEx) map.get("difL");
        this.motorR = (DcMotorEx) map.get("difR");
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
}
