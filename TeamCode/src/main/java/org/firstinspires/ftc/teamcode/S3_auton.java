package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name = "A_4_AUTON_SPECIMEN")
public class S3_auton extends LinearOpMode {

    ElapsedTime timer;
    Functions r;
    MotorPower m;

    @Override
    public void runOpMode() throws InterruptedException {
        m = new MotorPower(hardwareMap);
        r = new Functions(hardwareMap);
        timer = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return;
        r.init();

        if (opModeIsActive()) {
            sleep(1000);
            m.move(0.5,1000);
            sleep(700);
            r.autoScore();
            sleep(700);
//            m.move(0.7,700);
            sleep(1700);
            r.autoPullDown();
            sleep(500);
//            r.autoStart();
//            sleep(500);
//            m.move(-0.7,100);
//            sleep(500);
//            m.strafe(0.7,500);
//            sleep(500);
//            m.move(0.7,500);
//            sleep(500);
//            m.strafe(0.7,400);
//            sleep(500);
//            m.move(-0.7,1000);
//            sleep(500);
//            m.move(0.7,1000);
//            sleep(500);
//            m.strafe(0.7,300);
//            sleep(500);
//            m.move(-0.7,1000);
//            sleep(500);
//            m.move(0.7,500);
//            sleep(500);
//            m.move(-0.7,500);
//            sleep(500);
//            r.autoScore();
//            m.strafe(-0.7,800);
//            sleep(500);
//            m.move(0.7,700);
//            sleep(500);
//            r.autoPullDown();
//            sleep(500);
//            r.autoStart();
//            m.move(-0.7,100);
//            sleep(500);
//            m.strafe(0.7,500);
//            sleep(500);
//            m.move(-0.7,800);
//            sleep(500);
//            r.autoScore();
//            m.strafe(-0.7,500);
//            sleep(500);
//            m.move(0.7,900);
//            r.autoPullDown();
//            sleep(500);
//            r.autoStart();
//            m.move(-0.7,100);
//            sleep(500);
//            m.strafe(0.7,500);
//            sleep(500);
//            m.move(-0.7,800);
//            r.autoScore();
//            m.strafe(-0.7,500);
//            sleep(500);
//            m.move(0.7,900);
//            sleep(500);
//            r.autoPullDown();
//            sleep(500);
//            r.start();
//            m.move(-0.7,100);
//            sleep(500);
//            m.strafe(0.7,500);
//            sleep(500);
//            m.move(-0.7,800);
//            sleep(500);
//            r.autoScore();
//            m.strafe(-0.7,500);
//            sleep(500);
//            m.move(0.7,900);
//            sleep(500);
//            r.autoPullDown();
//            sleep(500);
//            r.autoStart();
//            sleep(500);
//            m.move(-0.7,100);
//            sleep(500);
//            m.strafe(0.7,1500);
//            sleep(500);
//            m.move(-0.7,1000);

        }
    }
}

