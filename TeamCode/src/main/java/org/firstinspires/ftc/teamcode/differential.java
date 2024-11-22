package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class differential {
    private DcMotorEx motorL;
    private DcMotorEx motorR;

    public differential (HardwareMap map) {
        this.motorL = (DcMotorEx) map.get("difL");
        this.motorR = (DcMotorEx) map.get("difR");
    }

    
}
