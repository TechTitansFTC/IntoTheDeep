package org.firstinspires.ftc.teamcode.v2;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
public class Hang {
    private DcMotorEx leftUp;
    private DcMotorEx rightUp;
    private Servo hookL;
    private Servo hookR;

    private final double RAM_L = 0;
    private final double RAM_R = 0;
    private final double INIT_L = 0;
    private final double INIT_R = 0;
    private final double HANG_L = 0;
    private final double HANG_R = 0;

    public Hang(HardwareMap m){
        this.leftUp = (DcMotorEx) m.get("leftActuator");
        this.rightUp = (DcMotorEx) m.get("rightActuator");
        this.hookL = m.servo.get("hookL"); //port 0 CH
        this.hookR = m.servo.get("hookR"); //port 6 EH
    }

    public void init () {
        hookR.setPosition(INIT_R);
        hookL.setPosition(INIT_L);
        leftUp.setPower(0);
        rightUp.setPower(0);
    }

    public void hookRam() {
        hookR.setPosition(RAM_R);
        hookL.setPosition(RAM_L);
    }

    public void hookHang() {
        hookR.setPosition(HANG_R);
        hookL.setPosition(HANG_L);
    }

    public void motorOn() {
        leftUp.setPower(1);
        rightUp.setPower(1);
    }

    public void motorOff() {
        leftUp.setPower(0);
        rightUp.setPower(0);
    }

    public void motorReverse() {
        leftUp.setPower(-1);
        rightUp.setPower(-1);
    }
}
