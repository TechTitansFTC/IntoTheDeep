package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeSystem {

    private Servo slidesL;
    private Servo slidesR;
    private CRServo geckoL;
    private CRServo geckoR;
    private Servo rotateL;
//    private Servo rotateR;

    ElapsedTime timer = new ElapsedTime();

    public final double leftSlidesOut = 0.15;// - this is the correct value
    public final double leftSlidesIn = 0.55;// - this is the correct value
    public final double rightSlidesOut = 0.4;// - this is the correct value
    public final double rightSlidesIn = 0; //- this is the correct value

    private final double leftRotateDown = 0.8; //tested correct
    private final double leftRotateUp = 0.4; // tested correctly
    private final double leftRotateSteady = 0.7;



    //TODO: find timer values
    private final int rotateTimer = 250;
    private final int geckoTimer = 150;
    private final int slideTimer = 350;

    public IntakeSystem (HardwareMap map) {
        this.slidesL = map.servo.get("inSlideL");
        this.slidesR = map.servo.get("inSlideR");
        this.geckoL = map.crservo.get("geckoL");
        this.geckoR = map.crservo.get("geckoR");
        this.rotateL = map.servo.get("inRotateL");
//        this.rotateR = map.servo.get("inRotateR");
    }

    public void init () {
        slidesR.setPosition(rightSlidesIn);
        slidesL.setPosition(leftSlidesIn);
        geckoR.setPower(0);
        geckoL.setPower(0);
//        rotateR.setPosition(rightRotateUp);
        rotateL.setPosition(leftRotateUp);
        this.geckoL.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void slideToggle() {
        timer.reset();
        if (slidesL.getPosition() == leftSlidesIn && slidesR.getPosition() == rightSlidesIn) {
            slidesR.setPosition(rightSlidesOut);
            slidesL.setPosition(leftSlidesOut);
        } else if (slidesL.getPosition() == leftSlidesOut && slidesR.getPosition() == rightSlidesOut) {
            slidesL.setPosition(leftSlidesIn);
            slidesR.setPosition(rightSlidesIn);
        }
        while (timer.milliseconds() < slideTimer) {

        }
    }

    public void slideIncrement(double amt) {
        double rightPos = slidesR.getPosition() + amt;
        double leftPos = slidesL.getPosition() - amt;

        if ( (rightPos >= rightSlidesIn && rightPos <= rightSlidesOut) &&
                (leftPos <= leftSlidesIn && leftPos >= leftSlidesOut) ) {
            slidesR.setPosition(rightPos);
            slidesL.setPosition(leftPos);
        }
    }

    public void slideSet(boolean O_I) {
        if (O_I) {
            slidesL.setPosition(leftSlidesOut);
            slidesR.setPosition(rightSlidesOut);
        } else {
            slidesL.setPosition(leftSlidesIn);
            slidesR.setPosition(rightSlidesIn);
        }
    }

    public void rotate(int USD) {
        timer.reset();
        if (USD > 0) {
            rotateL.setPosition(leftRotateUp);
//            rotateR.setPosition(rightRotateUp);
        } else if (USD < 0) {
            rotateL.setPosition(leftRotateDown);
//            rotateR.setPosition(rightRotateDown);
        } else {
            rotateL.setPosition(leftRotateSteady);
//            rotateR.setPosition(rightRotateSteady);
        }
        while (timer.milliseconds() < rotateTimer) {

        }
    }

    public void rotateToggle() {
        timer.reset();
//        if (rotateL.getPosition() == leftRotateDown && rotateR.getPosition() == rightRotateDown) {
        if (rotateL.getPosition() == leftRotateDown) {
//            rotateR.setPosition(rightRotateUp);
            rotateL.setPosition(leftRotateUp);
        } else if (rotateL.getPosition() == leftRotateUp) {
//        else if (rotateL.getPosition() == leftRotateUp && rotateR.getPosition() == rightRotateUp) {
//            rotateR.setPosition(rightRotateDown);
            rotateL.setPosition(leftRotateDown);
        }
    }

    public void intakeOnToggle() {
        if (geckoL.getPower() != 0 && geckoR.getPower() != 0) {
            geckoR.setPower(0);
            geckoL.setPower(0);
        } else if (geckoL.getPower() != 1 && geckoR.getPower() != 1) {
            geckoR.setPower(1);
            geckoL.setPower(1);
        }
    }

    public void reverseIntakeOnToggle() {
        if (geckoL.getPower() != 0 && geckoR.getPower() != 0) {
            geckoR.setPower(0);
            geckoL.setPower(0);
        } else if (geckoL.getPower() != -1 && geckoR.getPower() != -1) {
            geckoR.setPower(-1);
            geckoL.setPower(-1);
        }
    }

    public void geckoPos (int power) {
        timer.reset();
        geckoR.setPower(power);
        geckoL.setPower(power);
        while (timer.milliseconds() < geckoTimer) {

        }
    }

    public void ejectSample() {
        rotate(0);
        geckoPos(-1);
        rotate(1);
    }

    public void collectSample() {
        rotate(-1);
        geckoPos(1);
        rotate(1);
    }
}
