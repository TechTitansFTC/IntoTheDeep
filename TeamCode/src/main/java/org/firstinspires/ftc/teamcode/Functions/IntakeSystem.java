package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class IntakeSystem {

    private Servo slidesL;
    private Servo slidesR;
    private CRServo geckoL;
    private CRServo geckoR;
    private Servo rotateL;
    private Servo rotateR;

    ElapsedTime timer = new ElapsedTime();

    //TODO: find the slide values
    private final double slidesOut = 0.75;
    private final double slidesIn = 0.3;

    private final double leftRotateDown = 0.85;
    private final double leftRotateUp = 0.43;

    private final double rightRotateDown = 0.32;
    private final double rightRotateUp = 0.73;

    private final double leftRotateSteady = 0.75;
    private final double rightRotateSteady = 0.42;


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
        this.rotateR = map.servo.get("inRotateR");
    }

    public void init () {
        slidesR.setPosition(slidesIn);
        slidesL.setPosition(slidesIn);
        geckoR.setPower(0);
        geckoL.setPower(0);
        rotateR.setPosition(rightRotateUp);
        rotateL.setPosition(leftRotateUp);
        this.geckoL.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void slideToggle() {
        timer.reset();
        if (slidesL.getPosition() == slidesIn && slidesR.getPosition() == slidesIn) {
            slidesR.setPosition(slidesOut);
            slidesL.setPosition(slidesOut);
        } else if (slidesL.getPosition() == slidesOut && slidesR.getPosition() == slidesOut) {
            slidesL.setPosition(slidesIn);
            slidesR.setPosition(slidesIn);
        }
        while (timer.milliseconds() < slideTimer) {

        }
    }

    public void rotate(int USD) {
        timer.reset();
        if (USD > 0) {
            rotateL.setPosition(leftRotateUp);
            rotateR.setPosition(rightRotateUp);
        } else if (USD < 0) {
            rotateL.setPosition(leftRotateDown);
            rotateR.setPosition(rightRotateDown);
        } else {
            rotateL.setPosition(leftRotateSteady);
            rotateR.setPosition(rightRotateSteady);
        }
        while (timer.milliseconds() < rotateTimer) {

        }
    }

    public void rotateToggle() {
        timer.reset();
        if (rotateL.getPosition() == leftRotateDown && rotateR.getPosition() == rightRotateDown) {
            rotateR.setPosition(rightRotateUp);
            rotateL.setPosition(leftRotateUp);
        } else if (rotateL.getPosition() == leftRotateUp && rotateR.getPosition() == rightRotateUp) {
            rotateR.setPosition(rightRotateDown);
            rotateL.setPosition(leftRotateDown);
        }
        while (timer.milliseconds() < rotateTimer) {

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
