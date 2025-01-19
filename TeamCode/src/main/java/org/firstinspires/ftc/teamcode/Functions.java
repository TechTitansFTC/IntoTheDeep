package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Functions {
    private Outtake outtake;
    private Intake intake;

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

    SpecimenScoreState specimenScore = SpecimenScoreState.SCORE_CLAW;
/////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    public enum inExtend {
        LIFTUP,
        OPEN,
        EXTEND,
        DOWN;

    }
    inExtend inextend = inExtend.LIFTUP;
    public enum inHold {
        CLOSE,
        LIFTUP,
        STRAIGHTEN;
    }
    inHold inhold = inHold.CLOSE;

    public enum inDrop{
        OPEN,
        RETRACT,
        LIFTDOWN,
        CLOSE;
    }
    inDrop indrop = inDrop.OPEN;

    ElapsedTime timer = new ElapsedTime();

    public Functions(HardwareMap map) {

        outtake = new Outtake(map);
//        intake = new Intake(map);
    }

    public void init() {

//        intake.init();
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
                while (timer.milliseconds() <200){

                }
                outtake.wristScore();
                specimenScore = SpecimenScoreState.SCORE_CLAW;
                break;
            case SCORE_CLAW:
                outtake.clawClose();
                timer.reset();

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
/////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
public void inExtend() {
    switch (inextend) {
        case LIFTUP:

            inextend = inExtend.OPEN;
            break;
        case OPEN:

            inextend = inExtend.EXTEND;
            break;
        case EXTEND:

            inextend = inExtend.DOWN;
            break;
        case DOWN:

            inextend = inExtend.LIFTUP;
            break;

    }
    switch (inhold) {
        case CLOSE:

            inhold = inHold.LIFTUP;
            break;
        case LIFTUP:

            inhold = inHold.STRAIGHTEN;
            break;
        case STRAIGHTEN:

            inhold = inHold.CLOSE;
            break;


    }
}





}