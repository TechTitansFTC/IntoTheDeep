package org.firstinspires.ftc.teamcode.Autons;

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
        this.FL = (DcMotor) map.get("leftFront");
        this.FR = (DcMotor) map.get("rightFront");
        this.BR = (DcMotor) map.get("rightBack");
        this.BL = (DcMotor) map.get("leftBack");
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);

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
