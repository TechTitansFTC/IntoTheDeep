package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecimenRedNON_INTAKE {
    public static void main(String[] args) {

        Vector2d entry = new Vector2d(40,-36);
        Vector2d s1 = new Vector2d(48,-15);
        Vector2d s2 = new Vector2d(58,-15);
        Vector2d s3 = new Vector2d(60,-15);
        Vector2d end = new Vector2d(60,-60);
        Vector2d accept = new Vector2d(35,-50);
        Vector2d target = new Vector2d(10,-40);



        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(270), Math.toRadians(270), 15.)
                .setDimensions(18,18)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -60, Math.toRadians(-90)))
                .lineToY(-33)
                        .waitSeconds(1)
                                .splineTo(accept,Math.toRadians(90))
                                .waitSeconds(0.5)
                                .lineToY(-60)
                                .waitSeconds(1)
                                .splineTo(target,Math.toRadians(-90))
                                .lineToY(-33)
                                .waitSeconds(1)
                                .lineToY(-50)
                                .splineTo(s1,Math.toRadians(-90))
                                .lineToY(-60)
                                .waitSeconds(0.1)
                                .lineToY(-15)
                                .waitSeconds(0.1)
                                .strafeTo(s2)
                .waitSeconds(0.1)
                                .lineToY(-60)
                .waitSeconds(0.1)
                                .splineTo(accept,Math.toRadians(90))
                                .waitSeconds(1)
                        .lineToY(-60)
                        .waitSeconds(1)
                                .splineTo(target,Math.toRadians(-90))
                        .waitSeconds(1)
                                .lineToY(-33)
                                .strafeTo(end)

//                        .splineTo(accept,Math.toRadians(90))



                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}