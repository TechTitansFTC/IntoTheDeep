package org.firstinspires.ftc.teamcode.Autonomous;

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

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;
import org.firstinspires.ftc.teamcode.tuningVal.MecanumDrive;

@Config
@Autonomous(name = "DO_NOT_RUN", group = "Autonomous")
public class RED_SPECIMEN extends LinearOpMode {
    // need to create action classes

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        OuttakeSystem out = new OuttakeSystem(hardwareMap);
        Vector2d entry = new Vector2d(35,-36);
        Vector2d s1 = new Vector2d(48,-10);
        Vector2d s2 = new Vector2d(58,-10);
        Vector2d s3 = new Vector2d(62,-10);
        Vector2d end = new Vector2d(60,-60);
        Vector2d accept = new Vector2d(35,-50);
        Vector2d target = new Vector2d(10,-40);
        Vector2d target2 =   new Vector2d(7,-40);
        Vector2d target3 =   new Vector2d(4,-40);
        Vector2d target4 =   new Vector2d(1,-40);
        Vector2d target5 =   new Vector2d(-2,-40);
        // vision here that outputs position
        int visionOutputPosition = 1;


        TrajectoryActionBuilder startToBar = drive.actionBuilder(initialPose)
                .lineToY(-33)
                .waitSeconds(1);
        TrajectoryActionBuilder barToPushToAccept = drive.actionBuilder(initialPose)
                .lineToY(-36)
                .waitSeconds(0.01)
                .splineTo(entry,Math.toRadians(90))
                .waitSeconds(0.01)
                .lineToY(-10)
                .waitSeconds(0.01)
                .strafeTo(s1)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.01)
                .lineToY(-10)
                .waitSeconds(0.01)
                .strafeTo(s2)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.01)
                .lineToY(-10)
                .waitSeconds(0.01)
                .strafeTo(s3)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.01)
                .lineToY(-50)
                .waitSeconds(0.01)
                .strafeTo(accept)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(1);
        TrajectoryActionBuilder acceptToTarget = drive.actionBuilder(initialPose)
                .splineTo(target2,Math.toRadians(-90))
                .waitSeconds(0.01)
                .lineToY(-33)
                .waitSeconds(0.8);
        TrajectoryActionBuilder targetToAccept = drive.actionBuilder(initialPose)
                .lineToY(-40)
                .waitSeconds(0.01)
                .splineTo(accept,Math.toRadians(90))
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.8);
        TrajectoryActionBuilder acceptToTarget2 = drive.actionBuilder(initialPose)
                .lineToY(-50)
                .waitSeconds(0.01)
                .splineTo(target3,Math.toRadians(-90))
                .waitSeconds(0.01)
                .lineToY(-33)
                .waitSeconds(0.8);
        TrajectoryActionBuilder acceptToTarget3 = drive.actionBuilder(initialPose)
                .lineToY(-50)
                .waitSeconds(0.01)
                .splineTo(target4,Math.toRadians(-90))
                .waitSeconds(0.01)
                .lineToY(-33)
                .waitSeconds(0.8);
        TrajectoryActionBuilder acceptToTarget4 = drive.actionBuilder(initialPose)
                .lineToY(-50)
                .waitSeconds(0.01)
                .splineTo(target5,Math.toRadians(-90))
                .waitSeconds(0.01)
                .lineToY(-33)
                .waitSeconds(0.8)
                .lineToY(-40)
                .waitSeconds(0.01);
        Action trajectoryActionCloseOut = startToBar.fresh()
                .strafeTo(end)
                .build();

        // actions that need to happen on init; for instance, a claw tightening.
        out.init();






        Actions.runBlocking(
                new SequentialAction(
                        //bar part 1
                       startToBar.build(),
                        //bar part 2
                        //accept part1
                        barToPushToAccept.build(),
                        //accept part 2
                        //bar part 1
                        acceptToTarget.build(),
                        //bar part 2
                        //accept part 1
                        targetToAccept.build(),
                        //accept part 2
                        //bar part 1
                        acceptToTarget2.build(),
                        //bar part 2
                        //accept part1
                        targetToAccept.build(),
                        //accept part 2
                        //bar part 1
                        acceptToTarget3.build(),
                        //bar part 2
                        //accept part1
                        targetToAccept.build(),
                        //accept part 2
                        //bar part 1
                        acceptToTarget4.build(),
                        //bar part 2
                        //accept part1
                        trajectoryActionCloseOut
                )
        );
    }
}