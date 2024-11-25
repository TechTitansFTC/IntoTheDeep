package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecimenV2 {
    public static void main(String[] args) {

        Vector2d home = new Vector2d(55,-42);
        Vector2d target = new Vector2d(10,-36);
        Vector2d end = new Vector2d(60,-60);



        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(270), Math.toRadians(270), 15)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -60, Math.toRadians(-90)))
                .lineToY(-33)
                .waitSeconds(0.0001)

                .splineTo(home,Math.toRadians(50))
                .waitSeconds(0.0001)
                .turn(Math.toRadians(-140))
                .waitSeconds(0.0001)
                .turn(Math.toRadians(170))
                .waitSeconds(0.0001)
                .turn(Math.toRadians(190))
                .waitSeconds(0.0001)
                .strafeTo(target)
                .waitSeconds(0.0001)
                .splineTo(home,Math.toRadians(120))
                .waitSeconds(0.0001)
                .turn(Math.toRadians(-210))
                .waitSeconds(0.0001)
                .strafeTo(target)
                .waitSeconds(0.0001)
                .strafeTo(home)
                .waitSeconds(0.0001)
                .strafeTo(target)
                .waitSeconds(0.0001)
                .strafeTo(end)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
