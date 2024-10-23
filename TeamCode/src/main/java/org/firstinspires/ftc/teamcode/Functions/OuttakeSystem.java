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
    final private int slideDown = 1000;
    final private int slideUp = 1001;

    //TODO: FIND THE CLAW SERVO VALS
    final private double clawOpen = 0.3;
    final private double clawClosed = 0.7;

    //TODO: FIND THE ROTATE SERVO VALS
    final private double rotateLeftUp = 0.01;
    final private double rotateLeftDown = 0.00;
    final private double rotateRightUp = 0.00;
    final private double rotateRightDown = 0.01;

    //TODO: FIND TIMER VALUES
    final private int slideTimer = 350;
    final private int rotateTimer = 250;
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

        rotateL.setPosition(rotateLeftDown);
        rotateR.setPosition(rotateRightDown);
    }

    public void slideToggle() {
        timer.reset();
        if (slidesL.getTargetPosition() == slideDown && slidesR.getTargetPosition() == slideDown) {
            slidesL.setTargetPosition(slideUp);
            slidesR.setTargetPosition(slideUp);
        } else if (slidesL.getTargetPosition() == slideUp && slidesR.getTargetPosition() == slideUp) {
            slidesL.setTargetPosition(slideDown);
            slidesR.setTargetPosition(slideDown);
        }
        while (timer.milliseconds() < slideTimer) {

        }
    }

    public void rotateToggle(int UD) {
        timer.reset();
        if (UD > 0 && rotateR.getPosition() == rotateRightDown && rotateL.getPosition() == rotateLeftDown) {
            rotateR.setPosition(rotateRightUp);
            rotateL.setPosition(rotateLeftUp);
        } else if (UD < 0 && rotateR.getPosition() == rotateRightUp && rotateL.getPosition() == rotateLeftUp) {
            rotateR.setPosition(rotateRightDown);
            rotateL.setPosition(rotateLeftDown);
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

    public void pickUp() {
        if (slidesL.getTargetPosition() != slideDown || slidesR.getTargetPosition() != slideDown) {
            slideToggle();
        }
        clawOpen();
        rotateToggle(-1); // set it to rotate down
        clawClosed();
        rotateToggle(1); // set it to rotate up
    }
}
