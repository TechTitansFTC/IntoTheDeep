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

    //TODO: FIND THE CLAW SERVO VALS
    final public double clawOpen = 0.3;
    final public double clawClosed = 0.7;

    //TODO: FIND THE ROTATE SERVO VALS
    final public double rotateLeftUp = 0.01;
    final public double rotateLeftDown = 0.00;
    final public double rotateRightUp = 0.00;
    final public double rotateRightDown = 0.01;

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

        rotateL.setPosition(rotateLeftDown);
        rotateR.setPosition(rotateRightDown);
    }


    public void slideSet(int position) { // FOR TELEOP
        if (slidesL.getTargetPosition() != position && slidesR.getTargetPosition() != position) {
            slidesL.setTargetPosition(position);
            slidesR.setTargetPosition(position);
        }
    }

    public void rotateSet(int UD) {
        timer.reset();
        if (UD > 0 && rotateR.getPosition() == rotateRightDown && rotateL.getPosition() == rotateLeftDown) {
            rotateR.setPosition(rotateRightUp);


            }
        }

    public void rotateToggle() {
        timer.reset();
        if (rotateR.getPosition() == rotateRightDown && rotateL.getPosition() == rotateLeftDown) {
            rotateR.setPosition(rotateRightUp);
            rotateL.setPosition(rotateLeftUp);
        } else if (rotateR.getPosition() == rotateRightUp && rotateL.getPosition() == rotateLeftUp) {
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
        rotateSet(-1); // set it to rotate down
        clawClosed();
        rotateSet(1); // set it to rotate up
    }
}
