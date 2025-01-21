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
    public enum inExtend {

        OPEN,
        EXTEND,
        DOWN;

    }
    inExtend inextend = inExtend.EXTEND;
    public enum inHold {
        CLOSE,
        LIFTUP,
        STRAIGHTEN;
    }
    inHold inhold = inHold.CLOSE;

    public enum inDrop{
        OPEN,
        RETRACT,

    }
    inDrop indrop = inDrop.OPEN;

    public enum transfer{

        OPENCLAW,
        START_SHOULDER,
        START_ELBOW,
        START_WRIST,
        RETRACT,
        CLOSECLAW,
        OPENINTAKE,
        SCORE_SHOULDER,
        SCORE_ELBOW,
        SCORE_WRIST,



    }
    transfer Transfer = transfer.OPENCLAW;


    ElapsedTime timer = new ElapsedTime();

    public Functions(HardwareMap map) {

        outtake = new Outtake(map);
        intake = new Intake(map);
    }

    public void init() {

        intake.init();
        outtake.init();
        timer.reset();

    }


    /*
    left bumper - claw open
    right bumper - claw close
    pickup to ram position - a
     ram posiion to score - b
     score to start position - x;
     */

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

    /*
    FSM for extending out intake slides to pickup from submersible
     */
public void inExtend() {
    switch (inextend) {

        case OPEN:
            intake.clawopen();
            inextend = inExtend.DOWN;
            break;
        case EXTEND:
            intake.extendOut();
            inextend = inExtend.OPEN;
            break;
        case DOWN:
            timer.reset();
            while (timer.milliseconds()<500){

            }
            intake.liftdown();
            inextend = inExtend.EXTEND;
            break;

    }

}

public void inHold(){
    switch (inhold) {
        case CLOSE:
            intake.clawclose();

            inhold = inHold.LIFTUP;
            break;
        case LIFTUP:
            timer.reset();

            intake.liftup();
            inhold = inHold.STRAIGHTEN;
            break;
        case STRAIGHTEN:
            timer.reset();

            intake.wristDef();
            inhold = inHold.CLOSE;
            break;


    }
}
    public void intakeclose(){
    intake.clawclose();
    }

    public void inDrop(){
        switch (indrop) {
            case OPEN:
                intake.clawopen();
                indrop = inDrop.RETRACT;
                break;
            case RETRACT:
                timer.reset();
                while (timer.milliseconds()<500){

                }
                intake.extendIn();
                indrop = inDrop.OPEN;
                break;


        }
    }
    public void changeWrist(double increment){
        intake.wristM(increment);
    }

    //under construction
    public void transfer(){
        switch (Transfer) {

            case OPENCLAW:
                outtake.clawOpen();
                Transfer = transfer.START_SHOULDER;
                break;
            case START_SHOULDER:

                outtake.shoulderTR();
                Transfer = transfer.START_WRIST;
                break;
            case START_WRIST:
                outtake.wristStart();
                Transfer = transfer.START_ELBOW;
                break;
            case START_ELBOW:
                outtake.elbowTr();
                Transfer = transfer.RETRACT;
                break;
            case RETRACT:

                intake.extendIn();
                Transfer = transfer.CLOSECLAW;
                break;
            case CLOSECLAW:

                outtake.clawClose();
                Transfer = transfer.OPENINTAKE;
                break;
            case OPENINTAKE:

                intake.clawopen();
                Transfer = transfer.SCORE_SHOULDER;
                break;
            case SCORE_SHOULDER:
                timer.reset();

                outtake.shoulderScore();
                Transfer = transfer.SCORE_ELBOW;
                break;
            case SCORE_ELBOW:

                outtake.elbowScore();
                Transfer = transfer.SCORE_WRIST;
                break;
            case SCORE_WRIST:

                outtake.wristScore();
                Transfer = transfer.OPENCLAW;
                break;
        }
    }

    public void intakeout(){
        intake.clawopen();
        intake.extendOut();
        intake.liftdown();

    }
    public void pick(){
        intake.clawclose();
        intake.wristDef();
        intake.liftup();

    }
    public void drop(){
        intake.clawopen();
        intake.liftup();

    }
    public void inExtendIn(){
        intake.clawopen();
        while (timer.milliseconds() <200){

        }
        intake.liftup();
        while (timer.milliseconds() <200){

        }
        intake.extendIn();
    }
    public void inExtendOut(){
        intake.extendOut();
        while (timer.milliseconds() <300){

        }
        intake.liftdown();
        while (timer.milliseconds() <200){

        }
        intake.clawopen();
    }
    public void inLiftUp(){
        intake.clawclose();
        while (timer.milliseconds() <200){

        }
        intake.wristDef();
        while (timer.milliseconds() <200){

        }
        intake.liftup();

    }
    public void inLiftDown(){
        intake.liftdown();
        while (timer.milliseconds() <200){

        }
        intake.clawopen();

    }

    public void inClawOpen(){
        intake.clawopen();
    }

    public void inClawClose(){
        intake.clawclose();
    }
    public boolean isdeploy(){
        return intake.isDeploy();
    }
}