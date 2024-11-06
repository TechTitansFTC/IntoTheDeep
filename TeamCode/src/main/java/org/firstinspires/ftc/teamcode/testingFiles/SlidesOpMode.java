package org.firstinspires.ftc.teamcode.testingFiles;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
class slidesPIDTesting {
    public static int targetPos = 5000;
    public static double kP = 0.001;
    public static double kG = 0.01;

    private DcMotorEx motorL;
    private DcMotorEx motorR;

    public slidesPIDTesting (HardwareMap hardwareMap) {
        this.motorL = hardwareMap.get(DcMotorEx.class, "outtakeL");
        this.motorR = hardwareMap.get(DcMotorEx.class, "outtakeR");
    }

    public void setPower (double p) {
        motorR.setPower(p + kG);
        motorL.setPower(p + kG);
    }

    public double getkP () {
        return kP;
    }

    public int getTargetPos () {
        return targetPos;
    }

    public int getPositionL () {
        return motorL.getTargetPosition();
    }

    public int getPositionR () {
        return motorR.getTargetPosition();
    }
}

public class SlidesOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        slidesPIDTesting pid = new slidesPIDTesting(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double slidePos = (pid.getPositionL() + pid.getPositionR()) / 2.0;
            double slideError = pid.getTargetPos() - slidePos;

            double p = slideError * pid.getkP();
            pid.setPower(p);
            
            telemetry.addData("target:", pid.getTargetPos());
            telemetry.addData("pos:", slidePos);
            telemetry.update();
        }
    }
}
