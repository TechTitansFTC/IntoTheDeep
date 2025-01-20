package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private static final double LIFT_UP = 0.33;
    private static final double LIFT_DOWN = 0.01;
    private static final double WRIST_DEF = 0.52;
    private static final double CLAW_CLOSE = 0;
    private static final double CLAW_OPEN = 0.3;
    private static final double EXTEND_L = 0.2;
    private static final double EXTEND_R = 1;
    private static final double RETRACT_L = 0.6;
    private static final double RETRACT_R = 1;
    private static final double START_L = 0.4;
    private static final double START_R = 0.95;
    private static final double TRANSFERL = 0.2;
    private static final double TRANSFERR = 0.6;

    private Servo claw;//ch3
    private Servo wrist;//ch2
    private Servo lifter;//CH0
    private Servo extendL;
    private Servo extendR;

    public Intake(HardwareMap m){
        this.lifter = m.servo.get("lifter");
        this.extendL = m.servo.get("extendL");
        this.extendR = m.servo.get("extendR");
        this.wrist = m.servo.get("inwrist");
        this.claw = m.servo.get("inclaw");
    }
    public void init(){
        lifter.setPosition(LIFT_UP);
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
    public void wristM(double increment){
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
    public void transfer() {
        extendL.setPosition(TRANSFERL);
        extendR.setPosition(TRANSFERR);

    }
}
