package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

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


    public MotorPower(HardwareMap map) {
        this.FL = (DcMotor) map.get("frontLeftMotor");
        this.FR = (DcMotor) map.get("frontRightMotor");
        this.BR = (DcMotor) map.get("backRightMotor");
        this.BL = (DcMotor) map.get("backLeftMotor");
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void strafe(double pwr, int t){
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
        time.reset();
        while (time. milliseconds() < 100){

        }
    }



    public void turnP(double pwr, int t){
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
        time.reset();
        while (time.milliseconds() < 100){

        }
    }

    public void move(double pwr, int t){
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
        time.reset();
        while (time.milliseconds() < 100){

        }
    }

}
