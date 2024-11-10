package org.firstinspires.ftc.teamcode.MotorPowerAuton;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.MotorPowerAuton.MotorPower;

//import org.firstinspires.ftc.teamcode.Functions.IntakeSystem;
import org.firstinspires.ftc.teamcode.Functions.OuttakeSystem;

@Config()
@Autonomous(name = "RED_SPECIMEN_MOTOR", group = "MOTOR_POWER")



public class RED_SPECIMEN_MOTOR extends LinearOpMode {

    
    @Override
    public void runOpMode() {
        MotorPower m = new MotorPower(hardwareMap);
        waitForStart();
        m.move(-0.3,1600);//rail against sub
        sleep(1500);
        m.move(0.3,300);//rail against sub
        sleep(1500);
//        m.turnP(-0.5, 300 );
//        sleep(300);
//        m.move(0.75,100);//forward a bit
        m.turnP(0.75,400);//strafe to entry
        sleep(500);
        m.move(0.3,1500);//back behind sample
        sleep(500);
        m.turnP(-0.75,400);//straighten to sample
        sleep(500);
        m.move(-0.3,2000);//observation
        sleep(500);
        m.strafe(-0.75,300);//back to sample
        sleep(500);
        m.move(0.3,3500);//observation
        sleep(500);
        m.move(-0.3,3500);//observation
        sleep(500);
        m.strafe(-0.75,300);//back to sample
        sleep(500);
        m.move(0.3,3500);//observation
        sleep(500);
        m.move(-0.3,3500);//observation
        sleep(500);
        m.strafe(-0.75,300);//back to sample
        sleep(500);
        m.move(0.3,3500);//observation
        sleep(500);


    }

}

