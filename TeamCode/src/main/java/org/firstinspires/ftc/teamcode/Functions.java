package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Functions {
    private Outtake outtake;
    private Intake intake;
    private Hang hang;
    private Differential slides;

    public enum SpecimenPickupState {
        SPECIMEN_START,
        SPECIMEN_EXTEND,
        SPECIMEN_PICKUP,
        SPECIMEN_RETRACT,
        SPECIMEN_RAISE,
        SPECIMEN_PREP
    };
    SpecimenPickupState specimenPickup = SpecimenPickupState.SPECIMEN_START;

    public enum SpecimenScoreState {
        SPECIMEN_PREP,
        SPECIMEN_LOWER,
        SPECIMEN_RELEASE,
        SPECIMEN_RETRACT
    };
    SpecimenScoreState specimenScore = SpecimenScoreState.SPECIMEN_PREP;

    public Functions (HardwareMap map) {
        slides = new Differential(map);
        intake = new Intake(map);
        hang = new Hang(map);
        outtake = new Outtake(map);
    }

    public void init() {
        slides.init();
        hang.init();
        intake.init();
        outtake.init();
    }


    /*
    start -> send to down (need to change function)
    extend -> need code
    pickup -> if within range (probably needs to be moved to extend), then if extended, then close the claw
    retract -> need code (bring the outtake back into the robot with specimen on)
    raise -> send up (need to change function)
    prep -> prep for scoring (needs code)
     */
    public void specimenPickUp(boolean start) {
        switch (specimenPickup) {
            case SPECIMEN_START:
                if (start) {
                    slides.outtakeDown();
                    specimenPickup = SpecimenPickupState.SPECIMEN_EXTEND;
                }
                break;
            case SPECIMEN_EXTEND:
                //TODO: add the code to extend
                specimenPickup = SpecimenPickupState.SPECIMEN_PICKUP;
                break;
            case SPECIMEN_PICKUP:
                //TODO: implement differential encoder vars
                if (slides.withinRange(1000, 10, true) && slides.withinRange(1000, 10, false)) {
                    outtake.clawClose();
                    specimenPickup = SpecimenPickupState.SPECIMEN_RETRACT;
                }
                break;
            case SPECIMEN_RETRACT:
                //TODO: add the code to retract
                break;
            case SPECIMEN_RAISE:
                slides.outtakeUp();
                specimenPickup = SpecimenPickupState.SPECIMEN_PREP;
                break;
            case SPECIMEN_PREP:
                //TODO: prep for scoring
                specimenPickup = SpecimenPickupState.SPECIMEN_START;
                break;
        }
    }


    /*
    prep - position from pickup
    lower - get the specimen onto bar
    release - release it
    retract - retract to position for pickup start
     */
    public void specimenScore(boolean start) {
        switch (specimenScore) {
            case SPECIMEN_PREP:
                break;
            case SPECIMEN_LOWER:
                break;
            case SPECIMEN_RELEASE:
                break;
            case SPECIMEN_RETRACT:
                break;
        }
    }
}
