package org.firstinspires.ftc.teamcode.TeleOp;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;
import org.firstinspires.ftc.teamcode.MotorPowerAuton.MotorPower;

import java.util.concurrent.TimeUnit;

@TeleOp
public class TurnDegTest extends LinearOpMode {



int p = 370;
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        MotorPower m = new MotorPower(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.x){
               m.turn(0.75,90);
            }
            if(gamepad1.b){
                m.turnP(0.75,p);
            }
            if(gamepad1.dpad_up){
                p+=10;
                sleep(100);
            }
            if(gamepad1.dpad_down){
                p-=10;
                sleep(100);
            }
            telemetry.addData("curr t",p);
            telemetry.update();
        }
    }
}