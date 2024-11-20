package org.firstinspires.ftc.teamcode.Autonomous.MotorPowerAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Functions.*;

@Autonomous(name = "A_A_A_AUTON_WORKING_SPECIMEN")
public class A_A_A_AUTON_WORKING_SPECIMEN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    OuttakeSystem outtake;
    IntakeSystem intake;
    ElapsedTime timer;


    @Override
    public void runOpMode() throws InterruptedException {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft"); //CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight"); //CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft"); //CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight"); //CH 3
        outtake = new OuttakeSystem(hardwareMap);
        intake = new IntakeSystem(hardwareMap);
        MotorPower m = new MotorPower(hardwareMap);
        timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;
        outtake.init();
        intake.init();


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



        m.move(1,300);
        m.turnP(1,270);
        m.move(1,550);
        m.turnP(1,270);
        outtake.clawOpen();
        outtake.rotateUp();
        sleep(3000);
        m.move(-1, 350);
        sleep(250);
        outtake.clawClosed();
        sleep(500);


        //lift slides up
        outtake.getSlidesR().setPower(1);
        outtake.getSlidesL().setPower(1);
        sleep(450);
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);
        sleep(250);
        outtake.clawClosed();


        m.move(1,300);
        m.turnP(1,270);
        m.move(1,800);
        m.turnP(1,250);

        //lift slides
        outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtake.getSlidesR().setPower(1);
        outtake.getSlidesL().setPower(1);
        sleep(880);
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);

        m.move(-0.5,900);

        //lift slides down and score
        outtake.getSlidesR().setPower(-1);
        outtake.getSlidesL().setPower(-1);
        sleep(1230);
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);
        outtake.clawOpen();
        sleep(250);
        outtake.clawClosed();
        outtake.rotateUp();

        m.move(1,300);
        m.turnP(1,250);
        m.move(1,1000);
        m.turnP(1,300);
        outtake.rotateDown();
        m.move(-0.5,2000);











    }
}


