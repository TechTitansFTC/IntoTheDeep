package org.firstinspires.ftc.teamcode.Autonomous.MotorPowerAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Functions.*;

@Autonomous(name = "A_4_AUTON_SPECIMEN")
public class A_4_AUTON_SPECIMEN extends LinearOpMode {
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
        MotorPower m = new MotorPower(hardwareMap);
        timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;
        outtake.init();
        intake.init();

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
            m.move(-1.0,800);
            m.move(1,90);
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
//            m.turnP(1.0,500);
//            m.move(-1,350);
//            m.strafe(1.0, 650);
//            sleep(1000);
//            m.move(0.5, 200);
//            sleep(1000);
//            outtake.rotateUp();
//            outtake.clawOpen();
//            m.move(-0.5, 200);
//            sleep(1000);
//            outtake.clawClosed();
//            m.move(0.5, 550);
//            outtake.clawOpen();
//            sleep(1000);
//            m.move(-0.5, 300);
//            outtake.clawClosed();
//
//
//            //Go back to set position for score
//            m.strafeL(1, 300);
//            m.turnP(1.0,500);
//
//            //lift slides
//            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            outtake.getSlidesR().setPower(1);
//            outtake.getSlidesL().setPower(1);
//            sleep(1230);
//            outtake.getSlidesR().setPower(0);
//            outtake.getSlidesL().setPower(0);
//
//            //strafe to pos
//            m.strafe(1.0, 950);
//
//            //Everything below this is a copy of previous values so if u update something above also update it here
//            //move to bar
//            m.move(-1.0,1050);
//            m.move(1,80);
//            sleep(250);
//
//            //lift slides down and score
//            outtake.getSlidesR().setPower(-1);
//            outtake.getSlidesL().setPower(-1);
//            sleep(1230);
//            outtake.getSlidesR().setPower(0);
//            outtake.getSlidesL().setPower(0);
//            outtake.clawOpen();
//            outtake.rotateDown();



            //Pick up 3 and 4
            m.move(1,200);
            m.strafeL(1,500);
            m.move(-1.0,500);
            m.strafeL(1, 250);
            m.move(1.0,850);
            m.turnP(1.0,30);
            sleep(500);
            m.move(-1.0,750);
            m.strafeL(1,300);
            m.move(1, 950);
            m.move(-0.5, 300);
            m.turnP(1.0,600);
            sleep(1000);


            outtake.rotateUp();
            outtake.clawOpen();
            m.move(-0.5,300);
            outtake.clawClosed();

            //slides up
            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setPower(1);
            outtake.getSlidesL().setPower(1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);

            //go to score
            m.move(.5,300);
            m.turnP(1.0,500);
            m.strafe(1.0, 950);
            m.move(-1.0,950);
            m.move(.5, 90);

            //slides down
            outtake.getSlidesR().setPower(-1);
            outtake.getSlidesL().setPower(-1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);
            outtake.clawOpen();
            sleep(250);
            outtake.clawClosed();
            outtake.rotateDown();

            //score 2 ,3, and 4
            m.move(1, 300);
            m.turnP(1.0, 500);
            m.move(-1, 500);
            m.strafe(1, 650);
            m.move(-1, 200);
            outtake.clawOpen();
            sleep(1000);
            m.move(-1, 200);
            outtake.clawClosed();
            m.strafeL(1, 650);
            m.move(1, 500);
            m.turnP(1, 500);

                //slides up
            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setPower(1);
            outtake.getSlidesL().setPower(1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);

            //score
            m.move(-1, 300);
            m.move(1, 90);

            //slides down
            outtake.getSlidesR().setPower(-1);
            outtake.getSlidesL().setPower(-1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);
            outtake.clawOpen();
            sleep(250);
            outtake.clawClosed();
            outtake.rotateDown();

            //go back
            m.move(1.0, 300);
            m.strafe(1.0,650 );
            m.turnP(1.0,500);









        }
    }
}

