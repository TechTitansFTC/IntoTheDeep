package org.firstinspires.ftc.teamcode.Autonomous.Functions;

import com.qualcomm.robotcore.hardware.CRServo;
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

    //TODO: find these values
    private final double slidesOut = 0.75;
    private final double slidesIn = 0.3;

    private final double leftRotateDown = 0.85;
    private final double leftRotateUp = 0.43;

    private final double rightRotateDown = 0.32;
    private final double rightRotateUp = 0.73;

    private final double leftRotateSteady = 0.75;
    private final double rightRotateSteady = 0.42;


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
    }

    public void slideToggle() {
        if (slidesL.getPosition() == slidesIn && slidesR.getPosition() == slidesIn) {
            slidesR.setPosition(slidesOut);
            slidesL.setPosition(slidesOut);
        } else if (slidesL.getPosition() == slidesOut && slidesR.getPosition() == slidesOut) {
            slidesL.setPosition(slidesIn);
            slidesR.setPosition(slidesIn);
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
        while (timer.milliseconds() < 250) {

        }
    }

    public void geckoPos (int power) {
        timer.reset();
        geckoR.setPower(power);
        geckoL.setPower(power);
        while (timer.milliseconds() < 150) {

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
