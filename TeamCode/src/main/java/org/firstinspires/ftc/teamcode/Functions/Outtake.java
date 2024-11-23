package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    private Servo shoulderL;
    private Servo shoulderR;
    private Servo elbow;
    private Servo wrist;
    private Servo claw;
//TODO: FIND ALL SERVO VALUES
    private final double SHOULDER_L_OUT = 0.0;//arm outside robot
    private final double SHOULDER_L_IN = 0.0;//arm in robot
    private final double SHOULDER_L_N = 0.0;//arm perpendicular
    private final double SHOULDER_R_OUT = 0.0;//same but with other arm
    private final double SHOULDER_R_IN = 0.0;
    private final double SHOULDER_R_N = 0.0;
    private final double ELBOW_OUT = 0.0;//forearm out(away from robot)
    private final double ELBOW_IN = 0.0;//forearm in(towards robot)
    private final double ELBOW_N = 0.0;//forearm straight
    private final double ELBOW_ANG = 0.0;//forearm slight angle away for clipping
    private final double WRIST_PRO = 0.0;//pronated wrist(faces away robot)
    private final double WRIST_SUP = 0.0;//supinated wrist(faces towards robot)
    private final double CLAW_CLOSE = 0.0;//closes claw
    private final double CLAW_OPEN = 0.0;//open claw

    public Outtake(HardwareMap m){
        this.shoulderL = m.servo.get("rotateML");
        this.shoulderR = m.servo.get("rotateMR");
        this.elbow = m.servo.get("elbow");
        this.wrist = m.servo.get("wrist");
        this.claw = m.servo.get("claw");
    }

    //MINOR FUNCTIONS(INDUVIDUAL)

    public void shoulder(double shoulderLP, double shoulderRP){
        shoulderL.setPosition(shoulderLP);
        shoulderR.setPosition(shoulderRP);
    }

    public void elbow(double elbowP){
        elbow.setPosition(elbowP);
    }

    public void wrist(double wristP){
        wrist.setPosition(wristP);
    }

    public void claw(double clawP){
        claw.setPosition(clawP);
    }
    
}
