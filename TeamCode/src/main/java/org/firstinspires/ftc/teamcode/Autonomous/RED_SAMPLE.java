package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.tuningVal.MecanumDrive;

@Config
@Autonomous(name = "RED_SAMPLE_AUTO", group = "Autonomous")
public class RED_SAMPLE extends LinearOpMode {
    
    public class Intake {
        private CRServo servoInGeckoL;
        private CRServo servoInGeckoR;
        private CRServo servoInRoller;
        private DcMotorEx intakeSlidesMotor;
        private ElapsedTime timer;

        private ColorSensor color;

        private final int ROLL_ON = 1;
        private final int ROLL_OFF = 0;
        private final int IN_SLIDES_OUT = 4000;
        private final int IN_SLIDES_IN = 0;
        private final double IN_SLIDES_TIMER = 10.0;

        public Intake(HardwareMap hardwareMap) {
            intakeSlidesMotor = (DcMotorEx) hardwareMap.dcMotor.get("intake");
            intakeSlidesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            intakeSlidesMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            servoInRoller = (CRServo) hardwareMap.servo.get("inRoll");
            servoInGeckoL = (CRServo) hardwareMap.servo.get("geckoL");
            servoInGeckoR = (CRServo) hardwareMap.servo.get("geckoR");

            timer = new ElapsedTime();
            color = hardwareMap.get(ColorSensor.class, "Color");

            servoInGeckoL.setPower(ROLL_OFF);
            servoInGeckoR.setPower(ROLL_OFF);
            servoInRoller.setPower(ROLL_OFF);
        }
        
        public class IntakeSlidesOut implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                intakeSlidesMotor.setTargetPosition(IN_SLIDES_OUT);
                while (timer.milliseconds() < IN_SLIDES_TIMER) {

                }
                return false;
            }
        }
        public Action slidesOut() {return new IntakeSlidesOut();}

        public class IntakeWheelRun implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                servoInRoller.setPower(ROLL_ON);
                servoInGeckoL.setPower(ROLL_ON);
                servoInGeckoR.setPower(ROLL_ON);
                return false;
            }
        }
        public Action wheelsOn() {return new IntakeWheelRun();}

        public class IntakeWheelOff implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                servoInRoller.setPower(ROLL_OFF);
                servoInGeckoL.setPower(ROLL_OFF);
                servoInGeckoR.setPower(ROLL_OFF);
                return false;
            }
        }
        public Action wheelsOff() {return new IntakeWheelOff();}

        public class IntakeSlidesIn implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                intakeSlidesMotor.setTargetPosition(IN_SLIDES_IN);
                while (timer.milliseconds() < IN_SLIDES_TIMER) {

                }
                return false;
            }
        }
        public Action slidesIn() {return new IntakeSlidesIn();}

        public class IntakeRunner implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                slidesOut();
                wheelsOn();
                while ((((DistanceSensor) color).getDistance(DistanceUnit.CM)) > 0.1) {

                }
                wheelsOff();
                slidesIn();
                return false;
            }
        }
        public Action run() {return new IntakeRunner();}
    }

    public class Outtake {
        private DcMotorEx leftSlidesOuttakeMotor;
        private DcMotorEx rightSlidesOuttakeMotor;
        private Servo servoOutClaw;
        private Servo servoOutRotate;

        private ElapsedTime timer;

        final double CLAW_REST = 0.2;
        final double CLAW_CLOSED = 0.8;
        final double ROTATE_UP = 0.2;
        final double ROTATE_DOWN = 0.8;

        private final int OUT_SLIDES_BASE = 5000;
        private final int OUT_SLIDES_DOWN = 0;
        private final int OUT_SLIDES_SAMPLE = 4800;
        private final int OUT_SLIDES_SPECIMEN_UP = 4000;
        private final int OUT_SLIDES_SPECIMEN_DOWN = 3800;

        private final double OUT_BASE_TIMER = 12.0;
        private final double OUT_SAMPLE_TIMER = 10.0;
        private final double OUT_SPECIMEN_UP_TIMER = 8.0;
        private final double OUT_SPECIMEN_DOWN_TIMER = 2.0;

        public Outtake(HardwareMap hardwareMap) {
            leftSlidesOuttakeMotor = (DcMotorEx) hardwareMap.dcMotor.get("outtakeLeft");
            rightSlidesOuttakeMotor = (DcMotorEx) hardwareMap.dcMotor.get("outtakeRight");

            leftSlidesOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // vertical - 5000 ticks
            rightSlidesOuttakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            servoOutClaw = hardwareMap.servo.get("outClaw");
            servoOutRotate = hardwareMap.servo.get("outRotate");
            
            timer = new ElapsedTime();
        }

        public class OuttakeReset implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                leftSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_BASE);
                rightSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_BASE);
                while (timer.milliseconds() < OUT_BASE_TIMER) {

                }
                clawOpen();
                rotateUp();
                return false;
            }
        }
        public Action reset() {return new OuttakeReset();}

        public class OuttakeSlidesDown implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                leftSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_DOWN);
                rightSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_DOWN);
                while (timer.milliseconds() < OUT_BASE_TIMER) {

                }
                return false;
            }
        }
        public Action slidesDown() {return new OuttakeSlidesDown();}

        public class OuttakeSlidesSample implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                leftSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_SAMPLE);
                rightSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_SAMPLE);
                while (timer.milliseconds() < OUT_SAMPLE_TIMER) {

                }
                return false;
            }
        }
        public Action slidesSample() {return new OuttakeSlidesSample();}

        public class OuttakeSlidesSpecimen implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                leftSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_SPECIMEN_UP);
                rightSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_SPECIMEN_UP);
                while (timer.milliseconds() < OUT_SPECIMEN_UP_TIMER) {

                }

                timer.reset();
                leftSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_SPECIMEN_DOWN);
                rightSlidesOuttakeMotor.setTargetPosition(OUT_SLIDES_SPECIMEN_DOWN);
                while (timer.milliseconds() < OUT_SPECIMEN_DOWN_TIMER) {

                }

                clawOpen();

                return false;
            }
        }
        public Action slidesSpecimen() {return new OuttakeSlidesSpecimen();}

        public class ClawOpen implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                servoOutClaw.setPosition(CLAW_REST);
                return false;
            }
        }
        public Action clawOpen() {return new ClawOpen();}

        public class ClawClosed implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                servoOutClaw.setPosition(CLAW_CLOSED);
                return false;
            }
        }
        public Action clawClosed() {return new ClawClosed();}

        public class RotateDown implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                servoOutRotate.setPosition(ROTATE_UP);
                return false;
            }
        }
        public Action rotateDown() {return new RotateDown();}

        public class RotateUp implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                servoOutRotate.setPosition(ROTATE_DOWN);
                return false;
            }
        }
        public Action rotateUp() {return new RotateUp();}

        public class OuttakeLoad implements Action {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                rotateDown();
                slidesDown();
                clawClosed();
                rotateUp();
                return false;
            }
        }
        public Action load() {return new OuttakeLoad();}


    }


    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-17.9, -61, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        
        int visionOutputPosition =1;

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .lineToX(-48)
                .turn(Math.toRadians(90))
                .lineToY(-28)
                .waitSeconds(1)
                .lineToY(-48)
                .lineToYSplineHeading(-52,Math.toRadians(235))
                .strafeTo(new Vector2d(-58,-60))
                .waitSeconds(1)
                .turn(Math.toRadians(220))
                .strafeTo(new Vector2d(-58,-28))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-58,-60))
                .turn(Math.toRadians(120))
                .waitSeconds(1)
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-58,-28))
                .turn(Math.toRadians(90))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-58,-60))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-20,0))
                .turn(Math.toRadians(-90))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-58,-60));


        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Position during Init", position);
            telemetry.update();
        }
        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;
        
        Action trajRunner = tab1.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajRunner
                )
        );
    }
}
