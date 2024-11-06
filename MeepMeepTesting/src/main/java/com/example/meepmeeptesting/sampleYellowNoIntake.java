package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class sampleYellowNoIntake {
    public static void main(String[] args) {

        Vector2d end = new Vector2d(60,-60);
        Vector2d basket = new Vector2d(-55,-55);
        Vector2d s1 = new Vector2d(-45,-15);
        Vector2d s2 = new Vector2d(-55,-15);
        Vector2d s3 = new Vector2d(-62,-15);
        Vector2d accept1 = new Vector2d(-45,-60);
        Vector2d accept2 = new Vector2d(-45,-15);
        Vector2d target = new Vector2d(-32,-45);



        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(270), Math.toRadians(270), 15.)
                .setDimensions(18,18)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-10, -60, Math.toRadians(90)))
                .splineTo(basket,Math.toRadians(45))
                .waitSeconds(0.5)
                .strafeTo(target)
                .splineTo(s1,Math.toRadians(-90))
                .waitSeconds(0.5)
                .splineTo(accept1,Math.toRadians(-90))
                .lineToY(-5)
                .splineTo(s2,Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60)
                .waitSeconds(0.5)
                .lineToY(-5)
                .splineTo(s3,Math.toRadians(-90))
                .waitSeconds(0.5)
                .lineToY(-60)
                .waitSeconds(0.5)
                .lineToY(-55)
                .strafeTo(end)


                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
