package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    public Servo shoulderL; // main U-D
    public Servo shoulderR; // main U-D
    public Servo elbow; // subset U-D
    public Servo wrist; // L-R
    public Servo claw;
//    public DcMotorEx slidesL;
//    public DcMotorEx slidesR;

    double ticks = 384.5;

    public final double SHOULDER_L_SCORE = 0.36; //CH 4
    public final double SHOULDER_R_SCORE = 0.64; // CH 5
    public final double ELBOW_SCORE= 0.45; //EH 2
    public final double WRIST_SCORE = 0.7;
    //CLAW - OPEN


    public final double SHOULDER_L_START = 0; //CH 4
    public final double SHOULDER_R_START = 1; // CH 5
    public final double ELBOW_START= 0.55; //EH 2
    public final double WRIST_START = 0;
    //CLAW - OPEN

    public final double SHOULDER_L_PULLDOWN = 0.53; //CH 4
    public final double SHOULDER_R_PULLDOWN = 0.47; // CH 5
    public final double ELBOW_PULLDOWN = 0.55; //EH 2
    public final double WRIST_PULLDOWN = 0.7;
    //CLAW - CLOSED

    public final double CLAW_CLOSE = 0.4;
    public final double CLAW_OPEN = 0.7;

    public Outtake(HardwareMap m){
        this.shoulderL = m.servo.get("rotateML"); //port 6 CH
        this.shoulderR = m.servo.get("rotateMR"); //port 0 EH
        this.elbow = m.servo.get("elbow"); //port 5 CH
        this.wrist = m.servo.get("wrist"); //port 2 CH
        this.claw = m.servo.get("claw"); //port 1 CH
//        this.slidesL = (DcMotorEx) m.get("slidesL");
//        this.slidesR = (DcMotorEx) m.get("slidesR");

    }

    public void init(){
        shoulderL.setPosition(SHOULDER_L_START);
        shoulderR.setPosition(SHOULDER_R_START);
        wrist.setPosition(WRIST_START);
        claw.setPosition(CLAW_CLOSE);
        elbow.setPosition(ELBOW_START);
//        slidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        slidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
//    public void slides(double turn,double p){
//        slidesL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slidesR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        int runpos = (int) (ticks*turn);
//        slidesR.setTargetPosition(runpos);
//        slidesL.setTargetPosition(runpos);
//        slidesL.setPower(p);
//        slidesR.setPower(p);
//        slidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }

    public void shoulderScore(){
        shoulderL.setPosition(SHOULDER_L_SCORE);
        shoulderR.setPosition(SHOULDER_R_SCORE);
    }
    public void shoulderStart(){
        shoulderL.setPosition(SHOULDER_L_START);
        shoulderR.setPosition(SHOULDER_R_START);
    }
    public void shoulderPullDown(){
        shoulderL.setPosition(SHOULDER_L_PULLDOWN);
        shoulderR.setPosition(SHOULDER_R_PULLDOWN);
    }
    public void wristScore(){
        wrist.setPosition(WRIST_SCORE);
    }
    public void wristStart(){
        wrist.setPosition(WRIST_START);
    }
    public void wristPulldown(){
        wrist.setPosition(WRIST_PULLDOWN);
    }

    public void clawClose() { claw.setPosition(CLAW_CLOSE); }
    public void clawOpen(){
        claw.setPosition(CLAW_OPEN);
    }

    public void elbowStart(){
        elbow.setPosition(ELBOW_START);
    }
    public void elbowScore(){
        elbow.setPosition(ELBOW_SCORE);
    }
    public void elbowPulldown(){
        elbow.setPosition(ELBOW_PULLDOWN);
    }

}
