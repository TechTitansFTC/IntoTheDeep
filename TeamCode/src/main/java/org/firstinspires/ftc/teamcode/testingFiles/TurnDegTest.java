package org.firstinspires.ftc.teamcode.testingFiles;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.Functions.MotorPower;

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
               m.strafe(0.75,500);
            }
            if(gamepad1.b){
                m.strafe(-0.75,500);
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