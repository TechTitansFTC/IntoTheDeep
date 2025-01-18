package org.firstinspires.ftc.teamcode.Autons;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Functions;

@Config
@Autonomous(name = "NIGGESH DOND PLAY THE FOOL WITH ME", group = "Autonomous")
public class MotorPowerAuton extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        Functions r = new Functions(hardwareMap);
        MotorPower m = new MotorPower(hardwareMap);
        r.autoStart();
        r.closeClaw();
        waitForStart();

        if (isStopRequested()) return;
        r.autoScore();
        m.move(-0.3,1600);
        sleep(300);
        r.autoPullDown();
        sleep(1000);
        r.autoStart();
        m.move(0.5,150);
        m.strafe(-1,550);
        m.move(-1,400 );
        m.strafe(-1,300);
        m.move(1,750);
        sleep(500);
        m.turnP(-1,100);
        m.move(-1,200);
        sleep(3000);
        m.move(1,330);
        sleep(1000);
        r.autoScore();
        m.move(-1,100);
        m.strafe(1,900);
        m.move(-0.3, 1100);
        r.autoPullDown();
        sleep(100);
        r.autoStart();
        m.turnP(1,100);
        m.move(1,300);
        m.strafe(-1,800);
        sleep(3000);
        m.move(.7,300);
        sleep(1000);
        r.autoScore();
        m.strafe(1,700);
        m.move(-.3,2000);
        r.autoPullDown();
        sleep(100);
        r.autoStart();
        m.move(1,600);
        m.strafe(-1,700);



    }

}
