package org.firstinspires.ftc.teamcode.old.m1.Autonomous;
//import org.firstinspires.ftc.teamcode.old.m1.Functions.IntakeSystem;
//import org.firstinspires.ftc.teamcode.old.m1.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.old.m1.Functions.OuttakeSystem;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config()
@Autonomous(name = "SpecimenMainSpecimen")


public class SPECIMEN_AUTON_MAIN extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;





    BNO055IMU imu;

    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");//CH 1
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");//CH 2
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");//CH 0
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");//CH 3






        OuttakeSystem outtake = new OuttakeSystem(hardwareMap);
//        IntakeSystem intake = new IntakeSystem(hardwareMap);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        double power = 0.3;


//      methods

//        private void backward(int i) {
//            frontRightDrive.setPower(power);
//            backRightDrive.setPower(power);
//            frontLeftDrive.setPower(power);
        //          backLeftDrive.setPower(power);
        //        sleep(i);
        //      backLeftDrive.setPower(0);
        //    backRightDrive.setPower(0);
        //  frontLeftDrive.setPower(0);
        //frontRightDrive.setPower(0);
//        }

//        public void movingBack(int i)
//        {
//            frontRightDrive.setPower(power);
//            backRightDrive.setPower(power);
//            frontLeftDrive.setPower(power);
//            backLeftDrive.setPower(power);
//            sleep(i);
//            backLeftDrive.setPower(0);
//            backRightDrive.setPower(0);
//            frontLeftDrive.setPower(0);
//            frontRightDrive.setPower(0);
//        }




        waitForStart();

        outtake.init();

        outtake.getSlidesR().setPower(0.5);
        outtake.getSlidesL().setPower(0.5);
        sleep(1300);//raises slides
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);

        sleep(500);


        frontRightDrive.setPower(power);
        backRightDrive.setPower(-power);
        frontLeftDrive.setPower(-power);
        backLeftDrive.setPower(power);
        sleep(2000); //moves to bar
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(power);
        backRightDrive.setPower(power);
        frontLeftDrive.setPower(power);
        backLeftDrive.setPower(power);
        sleep(200); //goes back by a little
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        outtake.getSlidesR().setPower(-0.5);
        outtake.getSlidesL().setPower(-0.5);
        sleep(1300); //lowers slides
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);

        //outtake.clawOpen();

        frontRightDrive.setPower(power);
        backRightDrive.setPower(power);
        frontLeftDrive.setPower(-power);
        backLeftDrive.setPower(-power);
        sleep(2100); //turns around
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(-power);
        backRightDrive.setPower(-power);
        frontLeftDrive.setPower(-power);
        backLeftDrive.setPower(-power);
        sleep(500); //approaches wall
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(power);
        backRightDrive.setPower(-power);
        frontLeftDrive.setPower(-power);
        backLeftDrive.setPower(power);
        sleep(1000); //strafes left
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(-power);
        backRightDrive.setPower(-power);
        frontLeftDrive.setPower(-power);
        backLeftDrive.setPower(-power);
        sleep(300); //little back
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        outtake.clawClosed();

        outtake.getSlidesR().setPower(0.5);
        outtake.getSlidesL().setPower(0.5);
        sleep(1300);//raises slides
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);

        sleep(500);

        frontRightDrive.setPower(power);
        backRightDrive.setPower(power);
        frontLeftDrive.setPower(power);
        backLeftDrive.setPower(power);
        sleep(300); //little forward
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(-power);
        backRightDrive.setPower(power);
        frontLeftDrive.setPower(power);
        backLeftDrive.setPower(-power);
        sleep(300); //strafes right
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(-power);
        backRightDrive.setPower(-power);
        frontLeftDrive.setPower(power);
        backLeftDrive.setPower(power);
        sleep(2000); //turns around
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        frontRightDrive.setPower(-power);
        backRightDrive.setPower(-power);
        frontLeftDrive.setPower(-power);
        backLeftDrive.setPower(-power);
        sleep(1800); //approaches bar
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);


        frontRightDrive.setPower(power);
        backRightDrive.setPower(power);
        frontLeftDrive.setPower(power);
        backLeftDrive.setPower(power);
        sleep(2000); //little back
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backLeftDrive.setPower(0);

        outtake.getSlidesR().setPower(-0.5);
        outtake.getSlidesL().setPower(-0.5);
        sleep(1300);//lowers slides
        outtake.getSlidesR().setPower(0);
        outtake.getSlidesL().setPower(0);

        sleep(500);

        //specimen 3









    }


}