package org.firstinspires.ftc.teamcode.TeleOp;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class FOTeleOp extends OpMode {

    IMU imu;

    //Drivetrain Motors
    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    //Other Motors
    DcMotorEx motorOuttakeSlidesL;
    DcMotorEx motorOuttakeSlidesR;
//    DcMotorEx rigSlidesMotor;
    DcMotorEx motorTurret;

  
    ColorSensor color;
    
    // (outtake servo) - one claw, one rotate claw,
    // (intake servo) - 2 gecko wheels, one roller
  
    //Outtake Servos
    Servo servoOutClaw;
    Servo servoOutRotate;
    //Intake Servos
    CRServo servoInGeckoL;
    CRServo servoInGeckoR;
    CRServo servoInRoller;
    Servo servoInRotate;
    //Intake Slides Servos
    Servo servoIntakeSlidesR;
    Servo servoIntakeSlidesL;

    //Variables
    double y = 0;
    double x = 0;
    double rx = 0;

    double intakeSlidesPosL = 0;
    double intakeSlidesPosR = 0;
    int position = 0;
    int prevposition = 0;
    boolean intakeSlides = false;

    // VALUES

    // Outtake
    final double CLAW_REST = 0.4;
    final double ROTATE_REST = 0.4;

    // Intake
    final double IN_ROTATE_ENGAGE = 0.6;
    final double IN_ROTATE_RETRACT = 0.3;

    final int ROLL_ON = 1;
    final int ROLL_OFF = 0;
    final int ROLL_OUT = -1;

    final double IN_SLIDES_TIMER = 10.0;
    final double IN_SLIDES_REST = 0.3;

    final double wristdown = 0.0;
    final  double wristntr = 0.5;


    //Intake State
    public enum IntakeState {
        intakeIn,
        intakeMove,
        intakeRotate,
        intakeRun
    };

    public enum OuttakeState {
        outtakeLift,
        outtakeBucket1,
        outtakeBucket2,
        outtakeSpec1,
        outtakeSpec2,
        outtakeDrop
    }

    ElapsedTime timer = new ElapsedTime();;
    IntakeState intakeState = IntakeState.intakeIn;
    OuttakeState outtakeState = OuttakeState.outtakeLift;

    @Override
    public void init() {
        motorFrontLeft = hardwareMap.dcMotor.get("frontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("frontRight");
        motorBackLeft = hardwareMap.dcMotor.get("backLeft");
        motorBackRight = hardwareMap.dcMotor.get("backRight");
        motorOuttakeSlidesL = (DcMotorEx) hardwareMap.dcMotor.get("outtakeLeft");
        motorOuttakeSlidesR = (DcMotorEx) hardwareMap.dcMotor.get("outtakeRight");
//        rigSlidesMotor = (DcMotorEx) hardwareMap.dcMotor.get("intake");
        motorTurret = (DcMotorEx) hardwareMap.dcMotor.get("turret");

        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorOuttakeSlidesL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorOuttakeSlidesR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rigSlidesMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorTurret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoOutClaw = hardwareMap.servo.get("outClaw");
        servoOutRotate = hardwareMap.servo.get("outRotate");

        servoInRoller = (CRServo) hardwareMap.servo.get("inRoll");
        servoInGeckoL = (CRServo) hardwareMap.servo.get("geckoL");
        servoInGeckoR = (CRServo) hardwareMap.servo.get("geckoR");
        servoInGeckoR.setDirection(DcMotorSimple.Direction.REVERSE);
      
        servoInRotate = hardwareMap.servo.get("inRotate");
        servoIntakeSlidesR = hardwareMap.servo.get("inSlideR");
        servoIntakeSlidesL = hardwareMap.servo.get("inSlideL");

        color = hardwareMap.get(ColorSensor.class, "Color");
        
        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        timer.reset();

    }

    @Override
    public void start() {
        servoOutClaw.setPosition(0.3); //0.5
        servoOutRotate.setPosition(0.15); //0.85
        servoInGeckoL.setPower(ROLL_OFF); //1
        servoInGeckoR.setPower(ROLL_OFF); //1
        servoInRoller.setPower(ROLL_OFF); //1
        servoInRotate.setPosition(IN_ROTATE_RETRACT); //0.6
        servoIntakeSlidesL.setPosition(IN_SLIDES_REST); //0.7
        servoIntakeSlidesR.setPosition(IN_SLIDES_REST); //0.7
    }

    @Override
    public void loop() {

        switch (intakeState) {
            case intakeMove:
                if (servoIntakeSlidesL.getPosition() > 0.29 && servoIntakeSlidesL.getPosition() < 0.71) {
                    intakeSlidesPosL = servoIntakeSlidesL.getPosition();
                    intakeSlidesPosR = servoIntakeSlidesR.getPosition();
                    timer.reset();
                    if (signum(gamepad2.right_stick_y) != 0 && timer.milliseconds() > 100) {
                        intakeSlidesPosL += signum(gamepad2.right_stick_y);
                        intakeSlidesPosR += signum(gamepad2.right_stick_y);
                        servoIntakeSlidesL.setPosition(intakeSlidesPosL);
                        servoIntakeSlidesR.setPosition(intakeSlidesPosR);
                        timer.reset();
                    } else {
                        intakeState = IntakeState.intakeRotate;
                    }
                }
                break;
            case intakeRotate:
                if (signum(gamepad2.right_stick_y) != 0) {
                    intakeState = IntakeState.intakeMove;
                }
                if (gamepad2.start) {
                    if (servoInRotate.getPosition() < (IN_ROTATE_RETRACT + 0.01)) {
                        servoInRotate.setPosition(IN_ROTATE_ENGAGE);
                        intakeState = IntakeState.intakeRun;
                    } else if (servoInRotate.getPosition() > (IN_ROTATE_ENGAGE - 0.01)) {
                        timer.reset();
                        servoInRotate.setPosition(IN_ROTATE_RETRACT);
                        if (timer.milliseconds() > 300) {
                            servoIntakeSlidesL.setPosition(IN_SLIDES_REST);
                            servoIntakeSlidesR.setPosition(IN_SLIDES_REST);
                        }
                        intakeState = IntakeState.intakeIn;
                    }
                }
                break;
            case intakeRun:
                while (gamepad2.right_bumper) {
                    servoInGeckoL.setPower(ROLL_ON);
                    servoInGeckoR.setPower(ROLL_ON);
                    servoInRoller.setPower(ROLL_ON);
                } 
                while (gamepad2.left_bumper) {
                    servoInGeckoL.setPower(ROLL_OUT);
                    servoInGeckoR.setPower(ROLL_OUT);
                    servoInRoller.setPower(ROLL_OUT);
                }
            
                servoInGeckoL.setPower(ROLL_OFF);
                servoInGeckoR.setPower(ROLL_OFF);
                servoInRoller.setPower(ROLL_OFF);
                intakeState = IntakeState.intakeRotate;
                
                break;
        }

        switch (outtakeState) {
            case outtakeLift:
                if (gamepad2.left_stick_y != 0) {
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesL.setVelocity(signum(gamepad2.left_stick_y) * 2000);
                    motorOuttakeSlidesR.setVelocity(signum(gamepad2.left_stick_y) * 2000);
                    position = motorOuttakeSlidesL.getCurrentPosition();
                    prevposition = position;
                    intakeSlides = true;
                } else if (intakeSlides) {
                    //will correct the position of right side to counteract human error from belt tensioning velocity
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesR.setVelocity(2000);
                    motorOuttakeSlidesR.setTargetPosition(motorOuttakeSlidesL.getCurrentPosition());
                    position = motorOuttakeSlidesL.getCurrentPosition();
                    prevposition = position;
                    intakeSlides = false;
                }
                if (prevposition != position && gamepad2.left_stick_y == 0) {
                    motorOuttakeSlidesR.setTargetPosition(position);
                    motorOuttakeSlidesL.setTargetPosition(position);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesR.setVelocity(2000);
                    motorOuttakeSlidesL.setVelocity(2000);
                    prevposition = position;
                }
                outtakeState = OuttakeState.outtakeBucket1;
                break;
            case outtakeBucket1:
                if (gamepad2.dpad_up) {
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesL.setVelocity(2000);
                    motorOuttakeSlidesR.setVelocity(2000);
                    motorOuttakeSlidesR.setTargetPosition(2000);
                    motorOuttakeSlidesL.setTargetPosition(2000);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    servoOutRotate.setPosition(0.85);
                }
                if (signum(gamepad2.right_stick_y) > 0 || signum(gamepad2.right_stick_y) < 0) {
                    outtakeState = OuttakeState.outtakeLift;
                }
                break;
            case outtakeBucket2:
                if (gamepad2.dpad_down) {
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesL.setVelocity(2000);
                    motorOuttakeSlidesR.setVelocity(2000);
                    motorOuttakeSlidesR.setTargetPosition(4000);
                    motorOuttakeSlidesL.setTargetPosition(4000);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    servoOutRotate.setPosition(0.85);
                }
                if (signum(gamepad2.right_stick_y) > 0 || signum(gamepad2.right_stick_y) < 0) {
                    outtakeState = OuttakeState.outtakeLift;
                }
                break;
            case outtakeSpec1:
                if (gamepad2.dpad_up) {
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesL.setVelocity(2000);
                    motorOuttakeSlidesR.setVelocity(2000);
                    motorOuttakeSlidesR.setTargetPosition(1500);
                    motorOuttakeSlidesL.setTargetPosition(1500);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    servoOutRotate.setPosition(0.85);
                }
                if (signum(gamepad2.right_stick_y) > 0 || signum(gamepad2.right_stick_y) < 0) {
                    outtakeState = OuttakeState.outtakeLift;
                }
                break;
            case outtakeSpec2:
                if (gamepad2.dpad_up) {
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    motorOuttakeSlidesL.setVelocity(2000);
                    motorOuttakeSlidesR.setVelocity(2000);
                    motorOuttakeSlidesR.setTargetPosition(3000);
                    motorOuttakeSlidesL.setTargetPosition(3000);
                    motorOuttakeSlidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motorOuttakeSlidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    servoOutRotate.setPosition(0.85);
                }
                if (signum(gamepad2.right_stick_y) > 0 || signum(gamepad2.right_stick_y) < 0) {
                    outtakeState = OuttakeState.outtakeLift;
                }
                break;
        }


        if (gamepad1.right_trigger > 0) {
            y = -gamepad1.left_stick_y - gamepad1.right_stick_y; // Remember, this is reversed!
            x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            rx = -gamepad1.right_stick_x;
        } else if (gamepad1.left_trigger > 0) {
            y = -0.25 * (gamepad1.left_stick_y + gamepad1.right_stick_y); // Remember, this is reversed!
            x = 0.25 * gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            rx = -0.35 * gamepad1.right_stick_x;
        } else {
            y = -0.5 * (gamepad1.left_stick_y + gamepad1.right_stick_y); // Remember, this is reversed!
            x = 0.5 * gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            rx = -0.65 * gamepad1.right_stick_x;
        }

        if (gamepad1.back) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) + Math.toRadians(90);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        motorFrontLeft.setPower(frontLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackLeft.setPower(backLeftPower);
        motorBackRight.setPower(backRightPower);
    }
}