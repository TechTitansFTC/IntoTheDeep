package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Outtake;
@TeleOp(group = "testing")
public class OuttakeTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Servo shoulderL = hardwareMap.get(Servo.class, "rotateML"); // main U-D
        Servo shoulderR = hardwareMap.get(Servo.class, "rotateMR"); // main U-D
        Servo elbow = hardwareMap.get(Servo.class, "elbow"); // subset U-D
        Servo wrist = hardwareMap.get(Servo.class, "wrist"); // L-R
        Servo claw = hardwareMap.get(Servo.class, "claw");
        ElapsedTime timer = new ElapsedTime();
        Outtake out = new Outtake(hardwareMap);
        String[] servos = {"Shoulders", "Elbow", "Wrist", "Claw"};


        waitForStart();
        if (isStopRequested()) {
            return;
        }


//        out.init();
//        while(opModeIsActive()){
//            if(gamepad1.a){
//                out.shoulderStart();
//            }
//            if(gamepad1.b){
//                out.elbowStart();
//            }
//            if(gamepad1.x){
//                out.elbowScore();
//            }
//            if(gamepad1.y){
//                out.shoulderScore();
//            }
//            if(gamepad1.left_bumper){
//                out.clawOpen();
//            }
//            if(gamepad1.right_bumper){
//                out.clawClose();
//            }
//            if(gamepad1.dpad_up){
//                out.wristScore();
//            }
//            if(gamepad1.dpad_down){
//                out.wristStart();
//            }
//            if(gamepad1.left_trigger >0){
//                out.shoulderPullDown();
//            }
//        }


        timer.reset();


        int testServoNum = 0;
        while (opModeIsActive()) {
            // 0 - shoulders, 1 - elbows, 2 - wrist, 3 - claw
            // need to check whether l and r will move in same way - i coded in a way it should work

            if (timer.milliseconds() > 300) {
                switch (testServoNum) {
                    case 0:
                        if (gamepad1.dpad_up) {
                            shoulderL.setPosition(shoulderL.getPosition() + 0.05);
                            shoulderR.setPosition(shoulderR.getPosition() - 0.05);
                        } else if (gamepad1.dpad_down) {
                            shoulderL.setPosition(shoulderL.getPosition() - 0.05);
                            shoulderR.setPosition(shoulderR.getPosition() + 0.05);
                        } else if (gamepad1.dpad_right) {
                            shoulderL.setPosition(shoulderL.getPosition() + 0.01);
                            shoulderR.setPosition(shoulderR.getPosition() - 0.01);
                        } else if (gamepad1.dpad_left) {
                            shoulderL.setPosition(shoulderL.getPosition() - 0.01);
                            shoulderR.setPosition(shoulderR.getPosition() + 0.01);
                        }
                        break;
                    case 1:
                        if (gamepad1.dpad_up) {
                            elbow.setPosition(elbow.getPosition() + 0.05);
                        } else if (gamepad1.dpad_down) {
                            elbow.setPosition(elbow.getPosition() - 0.05);
                        } else if (gamepad1.dpad_right) {
                            elbow.setPosition(elbow.getPosition() + 0.01);

                        } else if (gamepad1.dpad_left) {
                            elbow.setPosition(elbow.getPosition() - 0.01);

                        }
                        break;
                    case 2:
                        if (gamepad1.dpad_up) {
                            wrist.setPosition(wrist.getPosition() + 0.05);
                        } else if (gamepad1.dpad_down) {
                            wrist.setPosition(wrist.getPosition() - 0.05);
                        } else if (gamepad1.dpad_right) {
                            wrist.setPosition(wrist.getPosition() + 0.01);
                        } else if (gamepad1.dpad_left) {
                            wrist.setPosition(wrist.getPosition() - 0.01);
                        }
                        break;
                    case 3:
                        if (gamepad1.dpad_up) {
                            claw.setPosition(claw.getPosition() + 0.05);
                        } else if (gamepad1.dpad_down) {
                            claw.setPosition(claw.getPosition() - 0.05);
                        } else if (gamepad1.dpad_right) {
                            claw.setPosition(claw.getPosition() + 0.01);
                        } else if (gamepad1.dpad_left) {
                            claw.setPosition(claw.getPosition() - 0.01);
                        }
                        break;
                }
                timer.reset();
            }


            telemetry.addData("Testing Servo", servos[testServoNum]);

            telemetry.addData("pos shoL", shoulderL.getPosition());
            telemetry.addData("pos shoR", shoulderR.getPosition());
            telemetry.addData("pos elbL", elbow.getPosition());
            telemetry.addData("pos wr", wrist.getPosition());
            telemetry.addData("pos cla", claw.getPosition());
            telemetry.update();


            if (gamepad1.right_bumper) {
                timer.reset();
                testServoNum += 1;
                while (timer.milliseconds() < 250) {

                }
            } else if (gamepad1.left_bumper) {
                timer.reset();
                testServoNum -= 1;
                while (timer.milliseconds() < 250) {

                }
            }
            if (testServoNum >= servos.length) {
                testServoNum = 0;

            }

        }
    }
}
