package org.firstinspires.ftc.teamcode.Autonomous.MotorPowerAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Functions.MotorPower;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;

@Autonomous(name = "A_3_AUTON_SPECIMEN")
public class A_3_AUTON_SPECIMEN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    OuttakeSystem outtake;
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

        if (opModeIsActive()) {
            //lift slides
            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            timer.reset();
            outtake.getSlidesR().setPower(1);
            outtake.getSlidesL().setPower(1);
            if (timer.milliseconds() > 1230) {
                outtake.getSlidesR().setPower(0);
                outtake.getSlidesL().setPower(0);
            }

            //move to bar
            m.move(-0.5,1700);
            m.move(0.5,90);

            //lift slides down and score
            outtake.getSlidesR().setPower(-1);
            outtake.getSlidesL().setPower(-1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);
            outtake.clawOpen();
            sleep(100);
            outtake.clawClosed();
            outtake.rotateDown();

            //Move to observation zone
            //TODO: Adjust the next 4 lines to be a turn and diagonal movement
            //TODO: Will need to adjust the motor power to make it diagonally move and turn together
            m.move(0.5,300);
            m.turnP(0.5,1150);
            m.move(-0.5,1100);
            m.strafe(1,1100);

            //go back for pickup
            sleep(1000);
            m.move(0.5, 200);
            sleep(1000);
            outtake.rotateUp();
            outtake.clawOpen();
            m.move(0.5, -200);
            sleep(1000);
            outtake.clawClosed();
            m.move(0.5, 350);
            outtake.clawOpen();
            sleep(1000);
            m.move(-0.5, 300);
            outtake.clawClosed();


            //Go back to set position for score
            m.strafel(0.5, 300);
            m.turnP(-0.5,1150);

            //lift slides
            outtake.getSlidesL().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtake.getSlidesR().setPower(1);
            outtake.getSlidesL().setPower(1);
            sleep(1230);
            outtake.getSlidesR().setPower(0);
            outtake.getSlidesL().setPower(0);

            //strafe to pos
            m.strafe(1,1000);

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
            m.turnP(0.5,1150);
            m.move(-0.5,1100);
            m.strafe(1,1000);

            //Pick up 3
            m.move(0.5, 2000);
            m.turnP(0.5, 1150);
            m.strafel(0.5, 200);
            m.move(0.5, 1700);
            m.move(-0.5, 300);
            m.turnP(0.5, 1150);
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
            m.turnP(.5,1150);
            m.strafe(.5,1000);
            m.move(-.5, 1100);
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

            //park
            m.move(0.5,300);
            m.turnP(0.5,1150);
            m.move(-0.5,1100);
            m.strafe(1,1200);
        }
    }
}


