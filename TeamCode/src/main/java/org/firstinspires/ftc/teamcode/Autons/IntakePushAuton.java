package org.firstinspires.ftc.teamcode.Autons;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Functions;
import org.firstinspires.ftc.teamcode.MecanumDrive;


import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
@Autonomous(name = "HAHAH", group = "Autonomous")
public class IntakePushAuton extends LinearOpMode {
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
                sleep(500);
                return false;
            }
        }
        public Action pulldown() {
            return new PullDown();
        }

        public class init implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.init();

                return false;
            }
        }
        public Action ini() {
            return new init();
        }

        public class intakeOut implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.intakeout();

                return false;
            }
        }
        public Action intakeout() {
            return new intakeOut();
        }

        public class intakeIn implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.intakein();

                return false;
            }
        }
        public Action intakein() {
            return new intakeIn();
        }

    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, -58, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Func rb = new Func(hardwareMap);

        // vision here that outputs position


        TrajectoryActionBuilder score1 = drive.actionBuilder(initialPose)
                .lineToY(-24);




        TrajectoryActionBuilder go1 = drive.actionBuilder(new Pose2d(0, -24, Math.toRadians(-90)))
                .splineTo(new Vector2d(35,-30),Math.toRadians(50));

        TrajectoryActionBuilder push1 = drive.actionBuilder(new Pose2d(35, -30, Math.toRadians(50)))
                .turn(Math.toRadians(-90));

        TrajectoryActionBuilder go2 = drive.actionBuilder(new Pose2d(35, -30, Math.toRadians(-40)))
                .splineTo(new Vector2d(40,-30),Math.toRadians(50));
        TrajectoryActionBuilder push2 = drive.actionBuilder(new Pose2d(40, -30, Math.toRadians(50)))
                .turn(Math.toRadians(-90));
        TrajectoryActionBuilder accept2= drive.actionBuilder(new Pose2d(40, -30, Math.toRadians(-40)))
                .splineTo(new Vector2d(35,-58),Math.toRadians(-90));
        TrajectoryActionBuilder acceptall= drive.actionBuilder(new Pose2d(0, -30, Math.toRadians(-90)))
                .strafeTo(new Vector2d(35,-58));
        TrajectoryActionBuilder scorall= drive.actionBuilder(new Pose2d(35, -58, Math.toRadians(-90)))
                .strafeTo(new Vector2d(0,-30));
        Action fin = score1.endTrajectory().fresh()
                .strafeTo(new Vector2d(35,-58))
                .build();

        // actions that need to happen on init; for instance, a claw tightening.

        Actions.runBlocking(rb.ini());




        waitForStart();

        if (isStopRequested()) return;




        Actions.runBlocking(
                new SequentialAction(
                        rb.score(),
                        score1.build(),
                        rb.pulldown(),//score 1
                        rb.start(),
                        go1.build(),
                        rb.intakeout(),
                        push1.build(),
                        go2.build(),
                        push2.build(),
                        accept2.build(),
                        rb.score(),
                        scorall.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        scorall.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        scorall.build(),
                        rb.pulldown(),
                        rb.start(),
                        fin




                )

        );

    }
}