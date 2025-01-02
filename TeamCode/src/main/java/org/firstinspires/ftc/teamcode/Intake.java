package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo inClaw;
    private Servo lRotate;
    private DcMotorEx inSlides;
    private Servo rRotate;

    //TODO: find intake servo values
    //TODO: finalize intake PIVs

    public final double IN_L_START = 0.35;
    public final double IN_L_PICK = 0.7;
    public final double IN_R_START = 0.65;
    public final double IN_R_PICK = 0.32;
    public final double WHEEL_IN = 0.32;
    public final double WHEEL_OUT = 0.32;

    public Intake (HardwareMap map) {
        this.inClaw = (Servo) map.get("inClaw");
        this.lRotate = (Servo) map.get("inL");
        this.rRotate = (Servo) map.get("inR");
        this.inSlides = (DcMotorEx) map.get("inSlides");
    }

    public void init () {
        inClaw.setPosition(WHEEL_OUT);
        lRotate.setPosition(IN_L_START);
        rRotate.setPosition(IN_R_START);
        inSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void wheelIn() {inClaw.setPosition(WHEEL_IN);}
    public void wheelOut() {inClaw.setPosition(WHEEL_OUT);}

    public void armUp() {
        lRotate.setPosition(IN_L_START);
        rRotate.setPosition(IN_R_START);
    }

    public void armOut() {
        lRotate.setPosition(IN_L_PICK);
        rRotate.setPosition(IN_R_PICK);
    }

    public void slidesPos(int pos){

        inSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if(inSlides.getCurrentPosition() >= 0 && inSlides.getCurrentPosition() <= 1440  ){
            inSlides.setTargetPosition(pos);
        } else if (inSlides.getCurrentPosition() >= 1440) {
            inSlides.setTargetPosition(1440);
        }else{
            inSlides.setTargetPosition(0);
        }
        inSlides.setPower(0.8);


    }



    public void manualSlides(double power){
        inSlides.setPower(power/2);
    }
}
