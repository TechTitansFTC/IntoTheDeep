//package org.firstinspires.ftc.teamcode.Autons;
//import androidx.annotation.NonNull;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.SleepAction;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.TranslationalVelConstraint;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Functions;
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//@Config
//@Autonomous(name = "intakepush", group = "Autonomous")
//public class IntakePushAuton extends LinearOpMode {
//    public class Func {
//        private Functions bot;
//
//        public Func(HardwareMap hardwareMap) {
//            bot=new Functions(hardwareMap);
//        }
//
//        public class Score implements Action {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                bot.autoScore();
//                sleep(1000);
//                return false;
//            }
//        }
//        public Action score() {
//            return new Score();
//        }
//
//        public class Start implements Action {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                bot.autoStart();
//
//                return false;
//            }
//        }
//        public Action start() {
//            return new Start();
//        }
//
//        public class PullDown implements Action {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                bot.autoPullDown();
//                sleep(500);
//                return false;
//            }
//        }
//        public Action pulldown() {
//            return new PullDown();
//        }
//
//        public class init implements Action {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                bot.init();
//
//                return false;
//            }
//        }
//        public Action ini() {
//            return new init();
//        }
//
//        public class intakeOut implements Action {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                bot.intakeout();
//
//                return false;
//            }
//        }
//        public Action intakeout() {
//            return new intakeOut();
//        }
//
//        public class intakeIn implements Action {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                bot.intakein();
//
//                return false;
//            }
//        }
//        public Action intakein() {
//            return new intakeIn();
//        }
//
//    }
//
//    @Override
//    public void runOpMode() {
//        Pose2d initialPose = new Pose2d(-10, -60, Math.toRadians(-90));
//        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
//        IntakePushAuton.Func rb = new IntakePushAuton.Func(hardwareMap);
//
//        Vector2d end = new Vector2d(60,-60);
//        Vector2d chamberLoc = new Vector2d(10,-40);
//        // vision here that outputs position
//
//
//        TrajectoryActionBuilder start = drive.actionBuilder(initialPose)
//                .lineToY(-33)
//                .waitSeconds(0.0001)
//                .lineToY(-40)
//                .waitSeconds(0.0001);
//
//        TrajectoryActionBuilder score = drive.actionBuilder(new Pose2d(0, -24, Math.toRadians(-90)))
//                . strafeTo(chamberLoc)
//                .waitSeconds(0.0001)
//                .lineToY(-34);
//
//        TrajectoryActionBuilder accept = drive.actionBuilder(new Pose2d(64, -59, Math.toRadians(-90)))
//                .lineToY(-40)
//                .strafeTo(spot)
//                .waitSeconds(0.0001)
//                .lineToY(-65);
//
//
//        Action fin = push.endTrajectory().fresh()
//                .strafeTo(new Vector2d(60,-60))
//                .build();
//
//        // actions that need to happen on init; for instance, a claw tightening.
//        Actions.runBlocking(rb.init());
//
//
//
//
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//
//
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        push.build(),
//                        rb.score(),
//                        score.build(),
//                        rb.pulldown(),
//                        rb.start(),
//                        accept.build(),
//                        rb.score(),
//                        score.build(),
//                        rb.pulldown(),
//                        rb.start(),
//                        accept.build(),
//                        rb.score(),
//                        score.build(),
//                        rb.pulldown(),
//                        rb.start(),
//                        accept.build(),
//                        rb.score(),
//                        score.build(),
//                        rb.pulldown(),
//                        rb.start(),
//                        fin
//                )
//
//
//        );
//
//
//    }
//}