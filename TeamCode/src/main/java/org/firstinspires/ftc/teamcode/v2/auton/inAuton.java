package org.firstinspires.ftc.teamcode.v2.auton;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.v2.Functions;
import org.firstinspires.ftc.teamcode.tuningVal.MecanumDrive;

import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
@Autonomous(name ="compauton", group = "Autonomous")
public class inAuton extends LinearOpMode {
    public class Func {
        private Functions bot;

        public Func(HardwareMap hardwareMap) {
            bot=new Functions(hardwareMap);
        }

        public class Score implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.autoScore();
                sleep(1000);
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
                sleep(1000);
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
                sleep(1000);
                return false;
            }
        }
        public Action pulldown() {
            return new PullDown();
        }

        public class init implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.sinit();

                return false;
            }
        }
        public Action ini() {
            return new init();
        }

    }



    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(10, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Func rb = new Func(hardwareMap);
        Vector2d entry = new Vector2d(40,-36);
        Vector2d s1 = new Vector2d(55,-10);
        Vector2d s2 = new Vector2d(64,-10);
        Vector2d end = new Vector2d(60,-60);
        Vector2d accept = new Vector2d(45,-56);
        Vector2d target2 =   new Vector2d(7,-28);
        // vision here that outputs position


        TrajectoryActionBuilder score1 = drive.actionBuilder(initialPose)
                .lineToY(-28)
                .waitSeconds(0.01);

        TrajectoryActionBuilder accept2 = drive.actionBuilder(new Pose2d(10, -28, Math.toRadians(90)))
                .lineToY(-40)
                .waitSeconds(0.0001)
                .strafeTo(entry)
                .waitSeconds(0.0001)
                .lineToY(-10)
                .waitSeconds(0.0001)
                .strafeTo(s1)
                .waitSeconds(0.0001)
                .lineToY(-50)
                .lineToY(-10)
                .waitSeconds(0.0001)
                .strafeTo(s2)
                .waitSeconds(0.0001)
                .lineToY(-50)
                .lineToY(-40)
                .lineToY(-61);


        TrajectoryActionBuilder score = drive.actionBuilder(new Pose2d(64, -61, Math.toRadians(90)))
                .splineTo(target2,Math.toRadians(90))
                .waitSeconds(0.5);
        TrajectoryActionBuilder score2 = drive.actionBuilder(new Pose2d(45, -56, Math.toRadians(90)))
                .splineTo(target2,Math.toRadians(90))
                .waitSeconds(0.5);
        TrajectoryActionBuilder acceptall= drive.actionBuilder(new Pose2d(7, -28, Math.toRadians(90)))
                .splineTo(accept,Math.toRadians(90));
        Action fin = score1.endTrajectory().fresh()
                .strafeTo(end)
                .build();

        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(rb.ini());




        waitForStart();

        if (isStopRequested()) return;




        Actions.runBlocking(
                new SequentialAction(
                        score1.build(),rb.score(),
                        rb.pulldown(),//score 1
                        rb.start(),
                        accept2.build()
                )


        );
        Actions.runBlocking(new SleepAction(0.5));
        Actions.runBlocking(new SequentialAction(
                        rb.score(),
                        score.build(),
                        rb.pulldown(),//score 2
                        rb.start(),
                        acceptall.build()
                )
        );
        Actions.runBlocking(new SleepAction(0.5));
        Actions.runBlocking(new SequentialAction(
                        rb.score(),
                        score2.build(),
                        rb.pulldown(),//score3
                        rb.start(),
                        acceptall.build()
                )
        );
        Actions.runBlocking(new SleepAction(0.5));
        Actions.runBlocking(new SequentialAction(
                        rb.score(),
                        score2.build(),
                        rb.pulldown(),//score 4
                        rb.start(),
                        fin
                )
        );

    }
}
