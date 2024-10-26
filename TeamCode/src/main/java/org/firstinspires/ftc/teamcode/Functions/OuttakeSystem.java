package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class OuttakeSystem {

    private DcMotorEx slidesL;
    private DcMotorEx slidesR;

    private Servo Claw;
    private Servo rotateL;
    private Servo rotateR;

    private ElapsedTime timer = new ElapsedTime();

    //TODO: FIND THE slide VAR VALUES
    final public int slideDown = 1000;
    final public int slideUp = 1001;

    final public int HIGH_BAR_TOP = 700;
    public final int SPECIMEN_RECIEVE = 100;
    final public int HIGH_BAR_BOTTOM = 600;
    final public int TOP_BASKET = 700;

    final public double clawOpen = 0.35;
    final public double clawClosed = 0.52;

    //TODO: FIND THE LEFT ROTATE SERVO VALS
    final public double rotateLeftUp = 0.01;
    final public double rotateLeftAngle = 0.01;
    final public double rotateLeftDown = 0.00;
    final public double rotateRightUp = 0.12;
    final public double rotateRightAngle = 0.35;
    final public double rotateRightDown = 0.6;

    //TODO: FIND TIMER VALUES
    final public int slideTimer = 350;
    final public int rotateTimer = 250;

    public OuttakeSystem (HardwareMap map) {
        this.slidesL = (DcMotorEx) map.get("outtakeLeft");
        this.slidesR = (DcMotorEx) map.get("outtakeRight");
        this.Claw = map.servo.get("outClaw");
        this.rotateL = map.servo.get("outRotateL");
        this.rotateR = map.servo.get("outRotateR");
    }

    public void init() {
        timer.reset();
        slidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slidesL.setTargetPosition(slideDown);
        slidesR.setTargetPosition(slideDown);
        slidesL.setPower(1);
        slidesR.setPower(1);

        Claw.setPosition(clawClosed);

//        rotateL.setPosition(rotateLeftDown);
        rotateR.setPosition(rotateRightDown);
    }


    public void slideSet(int position) { // FOR TELEOP
        if (slidesL.getTargetPosition() != position && slidesR.getTargetPosition() != position) {
            slidesL.setTargetPosition(position);
            slidesR.setTargetPosition(position);
        }
    }

    public void rotateUp() {
        timer.reset();
        rotateR.setPosition(rotateRightUp);
//        rotateL.setPosition(rotateLeftUp);
        while (timer.milliseconds() < rotateTimer) {

        }
    }

    public void rotateDown() {
        timer.reset();
        rotateR.setPosition(rotateRightAngle);
//        rotateL.setPosition(rotateLeftAngle);
        while (timer.milliseconds() < rotateTimer) {

        }
    }

    public void rotateTrueDown() {
        timer.reset();
        rotateR.setPosition(rotateRightDown);
//        rotateL.setPosition(rotateLeftDown);
        while (timer.milliseconds() < rotateTimer) {

        }
    }

    //TODO: fix it -> need work with all 3
    public void rotateSet(int UD) {
        timer.reset();
        if (UD > 0 && rotateR.getPosition() == rotateRightDown
//                && rotateL.getPosition() == rotateLeftDown
        ) {
            rotateR.setPosition(rotateRightUp);
//            rotateL.setPosition(rotateLeftUp);
        }
        else{
            rotateR.setPosition(rotateRightDown);
//            rotateL.setPosition(rotateLeftDown);
        }
    }

    //TODO: make a better toggle / make it work with all 3 values (for now i janked it to work with angle)
    public void rotateToggle() {
        timer.reset();
        if (rotateR.getPosition() == rotateRightAngle
//                && rotateL.getPosition() == rotateLeftDown
        ) {
            rotateUp();
        } else if (rotateR.getPosition() == rotateRightUp

//             &&   rotateL.getPosition() == rotateLeftUp
                ) {
            rotateDown();
        }
        while (timer.milliseconds() < rotateTimer) {

        }
    }

    public void clawOpen() {
        Claw.setPosition(clawOpen);
    }

    public void clawClosed() {
        Claw.setPosition(clawClosed);
    }
    public void clawToggle() {
        if (Claw.getPosition() == clawClosed) {
            Claw.setPosition(clawOpen);
        } else if (Claw.getPosition() == clawOpen) {
            Claw.setPosition(clawClosed);
        }
    }


    public void pickUp() {
        slideSet(slideDown);
        clawOpen();
//        rotateSet(-1); // set it to rotate down
        rotateDown();
        clawClosed();
//        rotateSet(1); // set it to rotate up
        rotateUp();
    }

    public void topBarDeposit() {
        slideSet(HIGH_BAR_TOP);
        while (timer.milliseconds() < 200) {

        }
        slideSet(HIGH_BAR_BOTTOM);
        while (timer.milliseconds() < 50) {

        }
        clawOpen();
    }
}
