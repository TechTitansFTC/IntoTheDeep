package org.firstinspires.ftc.teamcode.MotorPowerAuton;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class MotorPower  {
    private  ElapsedTime time = new ElapsedTime();
    private  DcMotor FL;//0
    private  DcMotor BL;//1
    private  DcMotor BR;//2
    private  DcMotor FR;//3
    private final double MS_PER_DEG = 100;

    public MotorPower(HardwareMap map) {
        this.FL = (DcMotor) map.get("frontLeft");
        this.FR = (DcMotor) map.get("frontLeft");
        this.BR = (DcMotor) map.get("frontLeft");
        this.BL = (DcMotor) map.get("frontLeft");
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void back(double pwr, int t){
        time.reset();
        FR.setPower(-pwr);
        BR.setPower(-pwr);
        FL.setPower(-pwr);
        BL.setPower(-pwr);
        while (time.milliseconds() < t){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void strafeR(double pwr, int t){
        time.reset();
        FR.setPower(-pwr);
        BR.setPower(pwr);
        FL.setPower(pwr);
        BL.setPower(-pwr);
        while (time.milliseconds() < t){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void strafeL(double pwr, int t){
        time.reset();
        FR.setPower(pwr);
        BR.setPower(-pwr);
        FL.setPower(-pwr);
        BL.setPower(pwr);
        while (time.milliseconds() < t){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void turnL(double pwr, int t){
        time.reset();
        FR.setPower(pwr);
        BR.setPower(pwr);
        FL.setPower(-pwr);
        BL.setPower(-pwr);
        while (time.milliseconds() < t){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void turnR(double pwr, int t){
        time.reset();
        FR.setPower(-pwr);
        BR.setPower(-pwr);
        FL.setPower(pwr);
        BL.setPower(pwr);
        while (time.milliseconds() < t){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void forw(double pwr, int t){
        time.reset();
        FR.setPower(pwr);
        BR.setPower(pwr);
        FL.setPower(pwr);
        BL.setPower(pwr);
        while (time.milliseconds() < t){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void turnRD(double pwr,double deg){
        time.reset();
        FR.setPower(-pwr);
        BR.setPower(-pwr);
        FL.setPower(pwr);
        BL.setPower(pwr);
        while (time.milliseconds() < deg * MS_PER_DEG){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }
    public void turnLD(double pwr,double deg){
        time.reset();
        FR.setPower(pwr);
        BR.setPower(pwr);
        FL.setPower(-pwr);
        BL.setPower(-pwr);
        while (time.milliseconds() < deg * MS_PER_DEG){

        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }


}
