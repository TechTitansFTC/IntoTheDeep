package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class PUSHBOTBLUE  {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(270), Math.toRadians(270), 15)
                .build();

        Vector2d basket = new Vector2d(55,55);
        Vector2d spawn2 = new Vector2d(55,15);
        Vector2d spawn3 = new Vector2d(62,15);
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, 61, Math.toRadians(-55)))
                .lineToY(10)
                .waitSeconds(0.0001)
                .turn(Math.toRadians(145))
                .waitSeconds(0.0001)
                .splineTo(basket,Math.toRadians(45))
                .waitSeconds(0.0001)
                .lineToX(30)
                .turn(Math.toRadians(-80))
                .waitSeconds(0.0001)
                .lineToX(55)
                .turn(Math.toRadians(125))
                .waitSeconds(0.0001)
                .splineTo(basket,Math.toRadians(90))
                .waitSeconds(0.0001)
                .splineTo(new Vector2d(42, 10), Math.toRadians(0))
                .waitSeconds(0.0001)
                .splineTo(spawn3,Math.toRadians(90))
                .waitSeconds(0.0001)
                .lineToY(52)
                .waitSeconds(0.0001)
                .splineTo(new Vector2d(20,15),Math.toRadians(180))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}