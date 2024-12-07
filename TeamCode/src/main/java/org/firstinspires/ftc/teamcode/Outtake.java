package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    private Servo shoulderL; // main U-D
    private Servo shoulderR; // main U-D
    private Servo elbowL; // subset U-D
    private Servo elbowR;
    private Servo wrist; // L-R
    private Servo claw;

    //TODO: FIND ALL SERVO VALUES
    private final double SHOULDER_L_OUT = 0.0;//arm outside robot
    private final double SHOULDER_L_IN = 0.0;//arm in robot
    private final double SHOULDER_L_N = 0.0;//arm perpendicular
    private final double SHOULDER_R_OUT = 0.0;//same but with other arm
    private final double SHOULDER_R_IN = 0.0;
    private final double SHOULDER_R_N = 0.0;
    private final double ELBOW_L_OUT = 0.0;//forearm out(away from robot)
    private final double ELBOW_L_IN = 0.0;//forearm in(towards robot)
    private final double ELBOW_R_OUT = 0.0;
    private final double ELBOW_R_IN = 0.0;
    private final double WRIST_UP = .75;
    private final double WRIST_DOWN = 0.1;
    private final double CLAW_CLOSE = 0.6;//closes claw
    private final double CLAW_OPEN = 0.45;//open claw

    public Outtake(HardwareMap m){
        this.shoulderL = m.servo.get("rotateML"); //port 4 CH
        this.shoulderR = m.servo.get("rotateMR"); //port 1 EH
        this.elbowL = m.servo.get("elbowL"); //port 5 CH
        this.elbowR = m.servo.get("elbowR"); //port 0 EH
        this.wrist = m.servo.get("wrist"); //port 1 CH
        this.claw = m.servo.get("claw"); //port 2 CH
    }

    public void init() {
        shoulderL.setPosition(SHOULDER_L_N);
        shoulderR.setPosition(SHOULDER_R_N);
        elbowL.setPosition(ELBOW_L_IN);
        elbowR.setPosition(ELBOW_R_IN);
        wrist.setPosition(WRIST_UP);
        claw.setPosition(CLAW_OPEN);
    }

    public void shoulderOut(){
        shoulderL.setPosition(SHOULDER_L_OUT);
        shoulderR.setPosition(SHOULDER_R_OUT);
    }
    public void shoulderIn(){
        shoulderL.setPosition(SHOULDER_L_IN);
        shoulderR.setPosition(SHOULDER_R_IN);
    }

    public void elbowIn(){
        elbowL.setPosition(ELBOW_L_IN);elbowR.setPosition(ELBOW_R_IN);
    }

    /**
     * set elbow to a specific spot
     * @param input - within range
     * @// TODO: 12/5/2024 make sure all the values work
     */
    public void setElbow(int input) {

    }

    public boolean checkElbow(int input) {
        return true;
    }
    public void elbowOut(){
        elbowL.setPosition(ELBOW_L_OUT);
        elbowR.setPosition(ELBOW_R_OUT);
    }

    public void wristUp(){
        wrist.setPosition(WRIST_UP);
    }
    public void wristDown(){
        wrist.setPosition(WRIST_DOWN);
    }

    public void clawClose(){
        claw.setPosition(CLAW_CLOSE);
    }
    public void clawOpen(){
        claw.setPosition(CLAW_OPEN);
    }

    public boolean getClawClosed() {
        if (claw.getPosition() == CLAW_CLOSE) {
            return true;
        }
        return false;
    }

    //TODO: code extend and retract positions
    //TODO: code scoring position



}
