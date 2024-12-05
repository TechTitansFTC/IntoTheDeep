package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private CRServo geckoL;
    private CRServo geckoR;
    private Servo armL;
    private Servo armR;
    private Servo elbow;

    //TODO: find intake servo values
    //TODO: finalize intake PIVs
    private final double ARM_L_IN = 0;
    private final double ARM_L_OUT = 0;
    private final double ARM_R_IN = 0;
    private final double ARM_R_OUT = 0;
    private final double ELBOW_BASE = 0;
    private final double ELBOW_EXTENDED = 0;

    public Intake (HardwareMap map) {
        this.geckoL = (CRServo) map.get("geckoL");
        this.geckoR = (CRServo) map.get("geckoR");
        this.armL = (Servo) map.get("intakeL");
        this.armR = (Servo) map.get("intakeR");
        this.elbow = (Servo) map.get("intake");
    }

    public void init () {
        geckoL.setPower(0);
        geckoR.setPower(0);
        armL.setPosition(ARM_L_IN);
        armR.setPosition(ARM_R_IN);
        elbow.setPosition(ELBOW_BASE);
    }

    public void intakeOn() {geckoL.setPower(-1);geckoR.setPower(1);}

    public void intakeOff() {geckoL.setPower(0);geckoR.setPower(0);}

    public void armIn() {
        armL.setPosition(ARM_L_IN);
        armR.setPosition(ARM_R_IN);
    }

    public void armOut() {
        armL.setPosition(ARM_L_OUT);
        armR.setPosition(ARM_R_OUT);
    }

    public void elbowOut() {elbow.setPosition(ELBOW_EXTENDED);}

    public void elbowIn() {elbow.setPosition(ELBOW_BASE);}
}
