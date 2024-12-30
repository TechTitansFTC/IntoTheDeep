package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Timer;

public class Functions {
    private Outtake outtake;
    private Intake intake;
    private Differential slides;

    public enum SpecimenPickupState {
        START_SHOULDER,
        START_ELBOW,
        START_WRIST,
        START_CLAW
    }

    SpecimenPickupState specimenPickup = SpecimenPickupState.START_SHOULDER;

    public enum SpecimenPulldownState {
        PULLDOWN_SHOULDER,
        PULLDOWN_ELBOW,
        PULLDOWN_WRIST,
        PULLDOWN_CLAW
    }

    SpecimenPulldownState specimenPulldown = SpecimenPulldownState.PULLDOWN_WRIST;

    public enum SpecimenScoreState {
        SCORE_SHOULDER,
        SCORE_ELBOW,
        SCORE_WRIST,
        SCORE_CLAW
    }

    SpecimenScoreState specimenScore = SpecimenScoreState.SCORE_SHOULDER;

    public enum SamplePickState {
        PICK_ROTATE,
        PICK_WHEEL
    }
    SamplePickState samplePick = SamplePickState.PICK_ROTATE;

    public enum SampleHoldState {
        HOLD_ROTATE,
        HOLD_WHEEL,
        HOLD_SLIDES
    }
    SampleHoldState sampleHold = SampleHoldState.HOLD_ROTATE;

    public final String[] MAKESHIFT = {"DOWN", "CLOSE", "UP", "SCORE"};
    ElapsedTime timer = new ElapsedTime();

    boolean x, a, b = false;

    public Functions(HardwareMap map) {

        intake = new Intake(map);
        outtake = new Outtake(map);
    }

    public void init() {

        intake.init();
        outtake.init();
        timer.reset();
    }

    //left bumper - claw open
    //right bumper - claw close
    //pickup to ram position - a
    // ram posiion to score - b
    // score to start position - x;

    public void start() {
        switch (specimenPickup) {
            case START_CLAW:
                outtake.clawOpen();
                specimenPickup = SpecimenPickupState.START_SHOULDER;
                break;
            case START_SHOULDER:
                outtake.shoulderStart();
                specimenPickup = SpecimenPickupState.START_ELBOW;
                break;
            case START_ELBOW:
                outtake.elbowStart();
                specimenPickup = SpecimenPickupState.START_WRIST;
                break;
            case START_WRIST:
                outtake.wristStart();
                specimenPickup = SpecimenPickupState.START_CLAW;
                break;

        }
    }

    public void pulldown() {
        switch (specimenPulldown) {
            case PULLDOWN_WRIST:
                outtake.wristPulldown();
                specimenPulldown = SpecimenPulldownState.PULLDOWN_CLAW;
                break;
            case PULLDOWN_CLAW:
                outtake.clawClose();
                specimenPulldown = SpecimenPulldownState.PULLDOWN_SHOULDER;
                break;
            case PULLDOWN_ELBOW:
                outtake.elbowPulldown();
                specimenPulldown = SpecimenPulldownState.PULLDOWN_WRIST;
                break;
            case PULLDOWN_SHOULDER:

                outtake.shoulderPullDown();
                specimenPulldown = SpecimenPulldownState.PULLDOWN_ELBOW;
                break;


        }
    }

    public void score() {
        switch (specimenScore) {
            case SCORE_SHOULDER:
                outtake.shoulderScore();
                specimenScore = SpecimenScoreState.SCORE_ELBOW;
                break;
            case SCORE_ELBOW:
                outtake.elbowScore();
                specimenScore = SpecimenScoreState.SCORE_WRIST;
                break;
            case SCORE_WRIST:
                outtake.wristScore();
                specimenScore = SpecimenScoreState.SCORE_CLAW;
                break;
            case SCORE_CLAW:
                outtake.clawClose();
                specimenScore = SpecimenScoreState.SCORE_SHOULDER;
                break;
        }
    }

    public void autoScore(){
        outtake.clawClose();
        outtake.shoulderScore();
        outtake.elbowScore();
        outtake.wristScore();

    }
    public void autoPullDown(){
        outtake.clawClose();
        outtake.elbowPulldown();
        outtake.shoulderPullDown();
        outtake.wristPulldown();

    }
    public void autoStart(){
        outtake.clawOpen();
        outtake.shoulderStart();
        outtake.elbowStart();
        outtake.wristStart();

    }

    public void openClaw() {
        outtake.clawOpen();
    }
    public void closeClaw() {
        outtake.clawClose();
    }

    public void samplePick(){
        switch (samplePick){
            case PICK_ROTATE:
                intake.armOut();
                samplePick = SamplePickState.PICK_WHEEL;
                break;
            case  PICK_WHEEL:
                intake.wheelIn();
                samplePick = SamplePickState.PICK_WHEEL;
                break;
        }
    }

    public void sampleHold(){
        switch (sampleHold){
            case HOLD_ROTATE:
                intake.armUp();
                sampleHold = SampleHoldState.HOLD_WHEEL;
                break;
            case  HOLD_WHEEL:
                intake.wheelIn();
                sampleHold = SampleHoldState.HOLD_SLIDES;
                break;
            case  HOLD_SLIDES:
                intake.slidesPos(0);
                sampleHold = SampleHoldState.HOLD_SLIDES;
                break;
        }
    }
    public void inWheelOut() {
        intake.wheelOut();
    }
    public void inWheelIn() {
        intake.wheelIn();
    }
    public void inOut() {
        intake.armOut();
    }
    public void inUp() { intake.armUp(); }
    public void inWheelOff(){intake.wheelOff();}
    public void manualSlides(double pwr){intake.manualSlides(pwr);}

}