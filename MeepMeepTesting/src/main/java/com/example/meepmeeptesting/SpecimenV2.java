package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecimenV2 {
    public static void main(String[] args) {

        Vector2d pos1 = new Vector2d(35,-15);
        Vector2d spec1 = new Vector2d(45,-15);
        Vector2d spec2 = new Vector2d(55,-15);
        Vector2d spec3 = new Vector2d(63,-15);
        Vector2d spot = new Vector2d(45,-50);
        Vector2d pickUp = new Vector2d(45,-65);
        Vector2d chamberLoc = new Vector2d(10,-40);
        Vector2d end = new Vector2d(60,-60);



        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(270), Math.toRadians(270), 15)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -60, Math.toRadians(-90)))

                .lineToY(-33)
                .waitSeconds(0.0001)
                .splineTo(pos1,Math.toRadians(90))
                .waitSeconds(0.0001)
                .strafeTo(spec1)
                .waitSeconds(0.0001)
                .lineToY(-60)
                .waitSeconds(0.0001)
                .lineToY(-15)
                .waitSeconds(0.0001)
                .strafeTo(spec2)
                .waitSeconds(0.0001)
                .lineToY(-60)
                .waitSeconds(0.0001)
                .lineToY(-15)
                .waitSeconds(0.0001)
                .strafeTo(spec3)
                .waitSeconds(0.0001)
                .lineToY(-60)
                .waitSeconds(0.0001)
                .lineToY(-50)
                .waitSeconds(0.0001)
                .strafeTo(spot)
                .waitSeconds(0.0001)
                .lineToY(-65)
                .waitSeconds(0.0001)
                //drop1
                .splineTo(chamberLoc,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .lineToY(-34)
                .waitSeconds(0.0001)
                .lineToY(-40)
                .splineTo(spot,Math.toRadians(90))
                .waitSeconds(0.0001)
                .lineToY(-65)
                .waitSeconds(0.0001)
                //drop2
                .splineTo(chamberLoc,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .lineToY(-34)
                .waitSeconds(0.0001)
                .lineToY(-40)
                .splineTo(spot,Math.toRadians(90))
                .waitSeconds(0.0001)
                .lineToY(-65)
                .waitSeconds(0.0001)
                //drop3
                .splineTo(chamberLoc,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .lineToY(-34)
                .waitSeconds(0.0001)
                .lineToY(-40)
                .splineTo(spot,Math.toRadians(90))
                .waitSeconds(0.0001)
                .lineToY(-65)
                .waitSeconds(0.0001)
                //drop4
                .splineTo(chamberLoc,Math.toRadians(-90))
                .waitSeconds(0.0001)
                .lineToY(-34)
                .waitSeconds(0.0001)
                .lineToY(-40)
                .splineTo(spot,Math.toRadians(90))
                .waitSeconds(0.0001)
                .lineToY(-65)



                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
