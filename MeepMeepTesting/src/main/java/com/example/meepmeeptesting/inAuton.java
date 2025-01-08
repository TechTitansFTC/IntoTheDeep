package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class inAuton {
    public static void main(String[] args) {




        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(250, 250, Math.toRadians(270), Math.toRadians(270), 15)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -60, Math.toRadians(90)))

                //drop1
                .lineToY(-33)
                .waitSeconds(0.1)
               .lineToY(-50)
                .waitSeconds(0.1)

                .splineTo(new Vector2d(40,-30),Math.toRadians(0))
                //pick 1
                .turn(Math.toRadians(-90))
                //turn 90 and extend and drop
                //retract a bit
                .turn(Math.toRadians(90))
                //extend and pick 2
                .turn(Math.toRadians(-90))
                //extend and drop
                //retract a vit
                .turn(Math.toRadians(90))
                //extend and pick 3
                .turn(Math.toRadians(-90))
                //drop and retract
                .splineTo(new Vector2d(40,-60),Math.toRadians(90))
                .strafeTo(new Vector2d(10,-33))
                .strafeTo(new Vector2d(40,-60))
                .strafeTo(new Vector2d(10,-33))
                .strafeTo(new Vector2d(40,-60))
                .strafeTo(new Vector2d(10,-33))
                .strafeTo(new Vector2d(40,-60))
                .strafeTo(new Vector2d(10,-33))
                .strafeTo(new Vector2d(40,-60))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
