package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private static final double LIFT_UP = 0;
    private static final double LIFT_DOWN = 0;
    private static final double WRIST_DEF = 0;
    private static final double CLAW_CLOSE = 0;
    private static final double CLAW_OPEN = 0;
    private static final double EXTEND_L = 0;
    private static final double EXTEND_R = 0;
    private static final double RETRACT_L = 0;
    private static final double RETRACT_R = 0;
    private static final double START_L = 0;
    private static final double START_R = 0;

    private Servo claw;
    private Servo wrist;
    private Servo lifter;
    private Servo extendL;
    private Servo extendR;

    public Intake(HardwareMap m){
        this.lifter = m.servo.get("lifter"); //port 6 CH
        this.extendL = m.servo.get("extendL"); //port 0 EH
        this.extendR = m.servo.get("extendR"); //port 5 CH
        this.wrist = m.servo.get("wrist"); //port 2 CH
        this.claw = m.servo.get("claw"); //port 1 CH
    }
    public void init(){
        lifter.setPosition(LIFT_DOWN);
        extendL.setPosition(START_L);
        wrist.setPosition(WRIST_DEF);
        claw.setPosition(CLAW_CLOSE);
        extendR.setPosition(START_R);
    }

    public void liftup(){
        lifter.setPosition(LIFT_UP);
    }
    public void liftdown(){
        lifter.setPosition(LIFT_DOWN);
    }
    public void wristDef(){
        wrist.setPosition(WRIST_DEF);
    }
    public void wristM(int increment){
        wrist.setPosition(wrist.getPosition() + increment);
    }
    public void clawopen(){
        claw.setPosition(CLAW_OPEN);
    }
    public void clawclose(){
        claw.setPosition(CLAW_CLOSE);
    }
    public void extendOut(){
        extendL.setPosition(EXTEND_L);
        extendR.setPosition(EXTEND_R);
    }
    public void extendIn() {
        extendL.setPosition(RETRACT_L);
        extendR.setPosition(RETRACT_R);
        extendL.setPosition(START_L);
        extendR.setPosition(START_R);
    }
}
