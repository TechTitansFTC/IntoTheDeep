package org.firstinspires.ftc.teamcode.Autons;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Functions;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "A4Auton", group = "Autonomous")
public class A4Auton extends LinearOpMode {
    public class Func {
        private Functions bot;

        public Func(HardwareMap hardwareMap) {
            bot=new Functions(hardwareMap);
        }

        public class Score implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.autoScore();
                sleep(700);
                return false;
            }
        }


public Action score() {
    return new Score();
}

public class Start implements Action {

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        bot.autoStart();

        return false;
    }
}
public Action start() {
    return new Start();
}

public class PullDown implements Action {

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        bot.autoPullDown();
        sleep(700);
        return false;
    }
}
public Action pulldown() {
    return new PullDown();
}

public class closeclaw implements Action {

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        bot.init();

        return false;
    }
}
public Action clawclose() {
    return new closeclaw();
}



    }

@Override
public void runOpMode() {
    Pose2d initialPose = new Pose2d(0, -58, Math.toRadians(-90));
    MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
    Func rb = new Func(hardwareMap);
    Vector2d entry = new Vector2d(38,-36);
    Vector2d s1 = new Vector2d(50,0);
    Vector2d s2 = new Vector2d(58,-5);
    Vector2d end = new Vector2d(60,-55);
    Vector2d accept = new Vector2d(45,-50);
    Vector2d target2 =   new Vector2d(0,-26);
    // vision here that outputs position


    TrajectoryActionBuilder score1 = drive.actionBuilder(initialPose)
            .lineToY(-24, new TranslationalVelConstraint(30));
    TrajectoryActionBuilder accept2 = drive.actionBuilder(new Pose2d(0, -24, Math.toRadians(-90)))
            .lineToY(-36)
            .strafeTo(entry)
            .waitSeconds(0.0001)
            .lineToY(-10)
            .strafeTo(s1)
            .waitSeconds(0.0001)
            .lineToY(-45)
            .lineToY(-10)
            .strafeTo(new Vector2d(60,0))
            .waitSeconds(0.0001)
            .lineToY(-45)
            .lineToY(-40)
            .lineToY(-59, new TranslationalVelConstraint(20));
    TrajectoryActionBuilder score2 = drive.actionBuilder(new Pose2d(64, -59, Math.toRadians(-90)))
            .strafeTo(new Vector2d(7,-26));





    TrajectoryActionBuilder score3 = drive.actionBuilder(new Pose2d(45, -59, Math.toRadians(-90)))
            .strafeTo(new Vector2d(-3,-26));



    TrajectoryActionBuilder score4 = drive.actionBuilder(new Pose2d(45, -59, Math.toRadians(-90)))
            .strafeTo(new Vector2d(-5,-26))
    .lineToY(-22);





    TrajectoryActionBuilder acceptall= drive.actionBuilder(new Pose2d(7, -26, Math.toRadians(-90)))
            .lineToY(-30)
            .strafeTo(accept)
            .waitSeconds(0.0001)
            .lineToY(-59,new TranslationalVelConstraint(30));

    TrajectoryActionBuilder accept3= drive.actionBuilder(new Pose2d(-3, -26, Math.toRadians(-90)))
            .lineToY(-30)
            .strafeTo(accept)
            .waitSeconds(0.0001)
            .lineToY(-59,new TranslationalVelConstraint(30));
    Action fin = score1.endTrajectory().fresh()
            .strafeTo(new Vector2d( 40,-60))
            .build();

    // actions that need to happen on init; for instance, a claw tightening.

    Actions.runBlocking(rb.clawclose());

    waitForStart();

    if (isStopRequested()) return;

    Actions.runBlocking(
            new SequentialAction(
                    rb.score(),
                    score1.build(),
                    rb.pulldown(),//score 1
                    rb.start(),
                    accept2.build()
            )


    );

    Actions.runBlocking(new SequentialAction(
                    rb.score(),
                    score2.build(),
                    rb.pulldown(),//score 2
                    rb.start(),
                    acceptall.build()
            )
    );

    Actions.runBlocking(new SequentialAction(
            rb.score(),
            score3.build(),
            rb.pulldown(),//score3
            rb.start(),
            accept3.build()

            )
    );

    Actions.runBlocking(new SequentialAction(
            rb.score(),
                    score4.build(),
                    rb.pulldown(),//score 4
                    rb.start(),
                    fin
            )
    );

}
}