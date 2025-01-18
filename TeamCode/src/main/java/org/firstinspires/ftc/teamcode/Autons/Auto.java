package org.firstinspires.ftc.teamcode.Autons;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Functions;

@Config
@Autonomous(name = "auto", group = "Autonomous")
public class Auto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        Functions r = new Functions(hardwareMap);
        MotorPower m = new MotorPower(hardwareMap);
        r.autoStart();
        r.closeClaw();

        waitForStart();

        if (isStopRequested()) return;
        m.move(-.5,300);
        r.autoScore();
        m.move(-.4,1000);
        sleep(500);

        r.autoPullDown();//1
        sleep(1000);
        r.autoStart();
        m.move(0.7,500);
        m.strafe(-0.7,1000);

        sleep(1000);
        m.move(.3,1000);
        r.autoScore();
        sleep(500);
        m.strafe(.7,1300);
        m.move(-.5,1000);
        sleep(1000);
        m.move(-.5,100);
        r.autoPullDown();//2
        sleep(1000);
        r.autoStart();
        sleep(300);
        m.move(.5,800);

        sleep(500);
        m.turnP(.7,800);
        m.strafe(1,1200);
        sleep(500);

        m.move(-.7,800);







    }

}
