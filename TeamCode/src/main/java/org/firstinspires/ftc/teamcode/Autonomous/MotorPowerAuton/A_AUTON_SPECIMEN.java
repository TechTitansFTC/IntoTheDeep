package org.firstinspires.ftc.teamcode.Autonomous.MotorPowerAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Functions.MotorPower;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;

@Autonomous(name = "A_AUTON_SPECIMEN")
public class A_AUTON_SPECIMEN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    OuttakeSystem outtake;


    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft"); //CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight"); //CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft"); //CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight"); //CH 3
        outtake = new OuttakeSystem(hardwareMap);
        MotorPower m = new MotorPower(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;
        outtake.init();

        if (opModeIsActive()) {
            //lift slides
            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setPower(1);
            outtake.getSlidesL().setPower(1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);

            //move to bar
            m.move(-0.5,1700);
            m.move(0.5,90);
            sleep(250);

            //lift slides down and score
            outtake.getSlidesR().setPower(-1);
            outtake.getSlidesL().setPower(-1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);
            outtake.clawOpen();
            sleep(250);
            outtake.clawClosed();
            outtake.rotateDown();

            //Go into park for 2nd pickup
            m.move(0.5,300);
            m.turn(180);
            m.move(-0.5,1200);
            m.strafeR(1,1200);
            sleep(1000);
            m.move(0.5, 200);
            sleep(1000);
            outtake.rotateUp();
            outtake.clawOpen();
            m.move(0.5, -200);
            sleep(1000);
            outtake.clawClosed();
            m.move(0.5, 200);
            m.strafeL(0.5, 200);

            //Go back to set position for score
            m.turn(180);

            //lift slides
            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setPower(1);
            outtake.getSlidesL().setPower(1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);

            //strafeR to pos
            m.strafeR(1,900);

            //Everything below this is a copy of previous values so if u update something above also update it here
            //move to bar
            m.move(-0.5,1700);
            m.move(0.5,80);
            sleep(250);

            //lift slides down and score
            outtake.getSlidesR().setPower(-1);
            outtake.getSlidesL().setPower(-1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);
            outtake.clawOpen();
            outtake.rotateDown();

            //Park
            m.move(0.5,300);
            m.turnP(0.5,1050);
            m.move(-0.5,1200);
            m.strafeR(1,1200);
        }
    }
}


