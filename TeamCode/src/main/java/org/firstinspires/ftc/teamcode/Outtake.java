package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    Differential slides;
    private Servo shoulderL; // main U-D
    private Servo shoulderR; // main U-D
    private Servo elbow; // subset U-D
    private Servo wrist; // L-R
    private Servo claw;

    public enum SpecimenState {
        SPECIMEN_START,
        SPECIMEN_EXTEND,
        SPECIMEN_PICKUP,
        SPECIMEN_RETRACT,
        SPECIMEN_RAISE,
        SPECIMEN_PREP
    };
    SpecimenState specimen = SpecimenState.SPECIMEN_START;

//TODO: FIND ALL SERVO VALUES
    private final double SHOULDER_L_OUT = 0.0;//arm outside robot
    private final double SHOULDER_L_IN = 0.0;//arm in robot
    private final double SHOULDER_L_N = 0.0;//arm perpendicular
    private final double SHOULDER_R_OUT = 0.0;//same but with other arm
    private final double SHOULDER_R_IN = 0.0;
    private final double SHOULDER_R_N = 0.0;
    private final double ELBOW_OUT = 0.0;//forearm out(away from robot)
    private final double ELBOW_IN = 0.0;//forearm in(towards robot)
    private final double ELBOW_N = 0.0;//forearm straight
    private final double ELBOW_ANG = 0.0;//forearm slight angle away for clipping
    private final double WRIST_PRO = 0.75;//pronated wrist(faces away robot)
    private final double WRIST_SUP = 0.1;//supinated wrist(faces towards robot)
    private final double CLAW_CLOSE = 0.6;//closes claw
    private final double CLAW_OPEN = 0.45;//open claw

    public Outtake(HardwareMap m, Differential slides){
        this.shoulderL = m.servo.get("rotateML");
        this.shoulderR = m.servo.get("rotateMR");
        this.elbow = m.servo.get("elbow");
        this.wrist = m.servo.get("wrist");
        this.claw = m.servo.get("claw");
        this.slides = slides;
    }

    public void init() {
        shoulderL.setPosition(SHOULDER_L_N);
        shoulderR.setPosition(SHOULDER_R_N);
        elbow.setPosition(ELBOW_N);
        wrist.setPosition(WRIST_PRO);
        claw.setPosition(CLAW_OPEN);
        slides.init();
    }

    public void shoulderOut(){
        shoulderL.setPosition(SHOULDER_L_OUT);
        shoulderR.setPosition(SHOULDER_R_OUT);
    }
    public void shoulderN(){
        shoulderL.setPosition(SHOULDER_L_N);
        shoulderR.setPosition(SHOULDER_R_N);
    }
    public void shoulderIn(){
        shoulderL.setPosition(SHOULDER_L_IN);
        shoulderR.setPosition(SHOULDER_R_IN);
    }

    public void elbowIn(){
        elbow.setPosition(ELBOW_IN);
    }
    public void elbowOut(){
        elbow.setPosition(ELBOW_OUT);
    }
    public void elbowN(){
        elbow.setPosition(ELBOW_N);
    }
    public void elbowAng(){
        elbow.setPosition(ELBOW_ANG);
    }

    public void wristPro(){
        wrist.setPosition(WRIST_PRO);
    }
    public void wristSup(){
        wrist.setPosition(WRIST_SUP);
    }

    public void clawClose(){
        claw.setPosition(CLAW_CLOSE);
    }
    public void clawOpen(){
        claw.setPosition(CLAW_OPEN);
    }

    //TODO: code extend and retract positions
    //TODO: code scoring position


    /*
    code for FSM -
    start -> send to down (need to change function)
    extend -> need code
    pickup -> if within range (probably needs to be moved to extend), then if extended, then close the claw
    retract -> need code (bring the outtake back into the robot with specimen on)
    raise -> send up (need to change function)
    prep -> prep for scoring (needs code)
     */
    public void specimenPickUp(boolean start) {
        switch (specimen) {
            case SPECIMEN_START:
                if (start) {
                    slides.outtakeDown();
                    specimen = SpecimenState.SPECIMEN_EXTEND;
                }
                break;
            case SPECIMEN_EXTEND:
                //TODO: add the code to extend
                specimen = SpecimenState.SPECIMEN_PICKUP;
                break;
            case SPECIMEN_PICKUP:
                //TODO: implement differential encoder vars
                if (slides.withinRange(1000, 10, true) && slides.withinRange(1000, 10, false)) {
                    clawClose();
                    specimen = SpecimenState.SPECIMEN_RETRACT;
                }
                break;
            case SPECIMEN_RETRACT:
                //TODO: add the code to retract
                break;
            case SPECIMEN_RAISE:
                slides.outtakeUp();
                specimen = SpecimenState.SPECIMEN_PREP;
                break;
            case SPECIMEN_PREP:
                //TODO: prep for scoring
                specimen = SpecimenState.SPECIMEN_START;
                break;
        }
    }
}
