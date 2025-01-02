package org.firstinspires.ftc.teamcode.auton;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Functions;
import org.firstinspires.ftc.teamcode.tuningVal.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "NIKESH DOND PLAY THE FOOL WITH ME", group = "Autonomous")
public class auton extends LinearOpMode {
    public class Func {
        private Functions bot;

        public Func(HardwareMap hardwareMap) {
            bot=new Functions(hardwareMap);
        }

        public class Score implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bot.autoScore();
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
        Vector2d entry = new Vector2d(38,-36);
        Vector2d s1 = new Vector2d(52,-10);
        Vector2d s2 = new Vector2d(62,-10);
        Vector2d s3 = new Vector2d(72,-10);
        Vector2d end = new Vector2d(60,-60);
        Vector2d accept = new Vector2d(40,-60);
        Vector2d target2 =   new Vector2d(7,-30);


        // vision here that outputs position


        TrajectoryActionBuilder score1 = drive.actionBuilder(initialPose)
                .lineToY(-30);
       TrajectoryActionBuilder accept2 = drive.actionBuilder(new Pose2d(10, -30, Math.toRadians(90)))
                .lineToY(-40)
                .strafeTo(entry)
                .strafeTo(s1)
               .waitSeconds(0.01)
                .lineToY(-60)
               .waitSeconds(0.01)
               .strafeTo(s2)
               .waitSeconds(0.01)
                .lineToY(-60)
               .waitSeconds(0.01)
                .strafeTo(s3)
               .waitSeconds(0.01)
                .lineToY(-60)
                .lineToY(-40)
                .strafeTo(accept)
                .waitSeconds(1);
        TrajectoryActionBuilder score2 = drive.actionBuilder(new Pose2d(35, -60, Math.toRadians(90)))
                .strafeTo(target2)
                .waitSeconds(1);
        TrajectoryActionBuilder acceptall= drive.actionBuilder(new Pose2d(7, -33, Math.toRadians(90)))
                .strafeTo(accept)
                .waitSeconds(1);
        Action fin = score1.endTrajectory().fresh()
                .strafeTo(end)
                .build();

        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(rb.ini());




        waitForStart();

        if (isStopRequested()) return;




        Actions.runBlocking(
                new SequentialAction(
                        rb.score(),
                        score1.build(),
                        rb.pulldown(),
                        rb.start(),
                        accept2.build(),
                        rb.score(),
                        score2.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        score2.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        score2.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        score2.build(),
                        rb.pulldown(),
                        rb.start(),
                        fin
                )
        );
    }
}
