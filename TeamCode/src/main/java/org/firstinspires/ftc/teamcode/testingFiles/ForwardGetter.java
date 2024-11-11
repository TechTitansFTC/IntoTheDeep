package org.firstinspires.ftc.teamcode.testingFiles;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class ForwardGetter extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;


    private ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");//CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");//CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");//CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");//CH 3




        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        while (opModeIsActive()) {
            double ms = 0;
            if (gamepad1.a){
                timer.reset();
                backLeftDrive.setPower(1);
                backRightDrive.setPower(1);
                frontLeftDrive.setPower(1);
                frontRightDrive.setPower(1);
            }
            if (gamepad1.x){
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                ms = timer.milliseconds();
            }

            telemetry.addData(" time",ms);

            telemetry.update();
        }
    }
}