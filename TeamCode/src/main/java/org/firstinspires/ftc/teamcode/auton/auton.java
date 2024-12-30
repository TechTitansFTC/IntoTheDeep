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

    }



    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(10, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Func rb = new Func(hardwareMap);
        Vector2d entry = new Vector2d(38,-36);
        Vector2d s1 = new Vector2d(50,-10);
        Vector2d s2 = new Vector2d(58,-10);
        Vector2d s3 = new Vector2d(65,-10);
        Vector2d end = new Vector2d(60,-60);
        Vector2d accept = new Vector2d(35,-62);
        Vector2d target2 =   new Vector2d(7,-33);
        Vector2d target3 =   new Vector2d(4,-33);
        Vector2d target4 =   new Vector2d(1,-33);
        Vector2d target5 =   new Vector2d(-2,-33);

        // vision here that outputs position


        TrajectoryActionBuilder score1 = drive.actionBuilder(initialPose)
                .lineToY(-33)
                .waitSeconds(0.5);
        TrajectoryActionBuilder accept2 = drive.actionBuilder(initialPose)
                
                .strafeTo(entry)
                .waitSeconds(0.25)
                .lineToY(-10)
                .waitSeconds(0.25)
                .strafeTo(s1)
                .waitSeconds(0.25)
                .lineToY(-60)
                .waitSeconds(0.1)
                .lineToY(-10)
                .waitSeconds(0.25)
                .strafeTo(s2)
                .waitSeconds(0.25)
                .lineToY(-60)
                .waitSeconds(0.1)
                .lineToY(-10)
                .waitSeconds(0.25)
                .strafeTo(s3)
                .waitSeconds(0.25)
                .lineToY(-60)
                .waitSeconds(0.1)
                .lineToY(-40)
                .waitSeconds(0.25)
                .strafeTo(accept);
        TrajectoryActionBuilder score2 = drive.actionBuilder(initialPose)
                .strafeTo(target2)
                .waitSeconds(0.5);
        TrajectoryActionBuilder acceptall= drive.actionBuilder(initialPose)
                .strafeTo(accept)
                .waitSeconds(0.5);
        TrajectoryActionBuilder score3 = drive.actionBuilder(initialPose)
                .strafeTo(target3)
                .waitSeconds(0.5);
        TrajectoryActionBuilder score4 = drive.actionBuilder(initialPose)
                .strafeTo(target4)
                .waitSeconds(0.5);
        TrajectoryActionBuilder score5 = drive.actionBuilder(initialPose)
                .strafeTo(target5)
                .waitSeconds(0.5);
        Action fin = score1.endTrajectory().fresh()
                .strafeTo(end)
                .build();

        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(rb.start());



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
                        score3.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        score4.build(),
                        rb.pulldown(),
                        rb.start(),
                        acceptall.build(),
                        rb.score(),
                        score5.build(),
                        rb.pulldown(),
                        rb.start(),
                        fin
                )
        );
    }
}
