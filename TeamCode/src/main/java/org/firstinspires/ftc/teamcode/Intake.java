package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo inClaw;
    private Servo lRotate;
//    private Servo rRotate;

    //TODO: find intake servo values
    //TODO: finalize intake PIVs
    public final double IN_CLAW_CLOSED = 0.15;
    public final double IN_CLAW_OPEN = 0.5;
    public final double IN_L_START = 0.35;
    public final double IN_L_PICK = 0.7;
//    public final double IN_R_START = 0.65;
//    public final double IN_R_PICK = 0.32;

    public Intake (HardwareMap map) {
        this.inClaw = (Servo) map.get("inClaw");
        this.lRotate = (Servo) map.get("inL");
//        this.rRotate = (Servo) map.get("inR");
    }

    public void init () {
        inClaw.setPosition(IN_CLAW_OPEN);
        lRotate.setPosition(IN_L_START);
//        rRotate.setPosition(IN_R_START);
    }

    public void inClawClosed() {inClaw.setPosition(IN_CLAW_CLOSED);}

    public void inClawOpen() {inClaw.setPosition(IN_CLAW_OPEN);}

    public void inArmUp() {
        lRotate.setPosition(IN_L_START);
//        rRotate.setPosition(IN_R_START);
    }

    public void inArmOut() {
        lRotate.setPosition(IN_L_PICK);
//        rRotate.setPosition(IN_R_PICK);
    }
}
