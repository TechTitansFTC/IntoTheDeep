package org.firstinspires.ftc.teamcode.old.m1.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.tuningVal.MecanumDrive;

@Config
@Autonomous(name = "PSUHBOTRS", group = "Autonomous")
public class PUSHBOT_RS extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-10, -61, Math.toRadians(125));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Vector2d basket = new Vector2d(-55,-55);
        Vector2d spawn2 = new Vector2d(-55,-15);
        Vector2d spawn3 = new Vector2d(-62,-15);
        TrajectoryActionBuilder pbr = drive.actionBuilder(initialPose)
                .lineToY(-10)
                .waitSeconds(0.0001)
                .turn(Math.toRadians(145))
                .waitSeconds(0.0001)
                .splineTo(basket,Math.toRadians(-135))
                .waitSeconds(0.0001)
                .lineToX(-30)
                .waitSeconds(0.0001)
                .splineTo(spawn2,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .splineTo(basket,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .splineTo(new Vector2d(-42, -10), Math.toRadians(180))
                .waitSeconds(0.0001)
                .splineTo(spawn3,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .lineToY(-52)
                .waitSeconds(0.0001)
                .splineTo(new Vector2d(-20,-15),Math.toRadians(0));

        waitForStart();

        if (isStopRequested()) return;

        Action trajRunner = pbr.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajRunner
                )
        );
    }
}