package com.example.meepmeeptesting;



import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecimenRedNON_INTAKE {
    public static void main(String[] args) {

        Vector2d entry = new Vector2d(35,-36);
        Vector2d s1 = new Vector2d(48,-10);
        Vector2d s2 = new Vector2d(58,-10);
        Vector2d s3 = new Vector2d(62,-10);
        Vector2d end = new Vector2d(60,-60);
        Vector2d accept = new Vector2d(35,-62);
        Vector2d target2 =   new Vector2d(7,-33);
        Vector2d target3 =   new Vector2d(4,-33);
        Vector2d target4 =   new Vector2d(1,-33);
        Vector2d target5 =   new Vector2d(-2,-33);



        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(250, 250, Math.toRadians(540), Math.toRadians(540), 10.)
                .setDimensions(18,18)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(10, -60, Math.toRadians(90)))

                .lineToY(-33)
                .waitSeconds(1)
                .lineToY(-36)
                .waitSeconds(0.01)
                .strafeTo(entry)
                .waitSeconds(0.01)
                .lineToY(-10)
                .waitSeconds(0.01)
                .strafeTo(s1)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.01)
                .lineToY(-10)
                .waitSeconds(0.01)
                .strafeTo(s2)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.01)
                .lineToY(-10)
                .waitSeconds(0.01)
                .strafeTo(s3)
                .waitSeconds(0.01)
                .lineToY(-60)
                .waitSeconds(0.01)
                .lineToY(-50)
                .waitSeconds(0.01)
                .strafeTo(accept)
                .waitSeconds(1)
                .strafeTo(target2)
                .waitSeconds(1)
                .strafeTo(accept)
                .waitSeconds(1)
                .strafeTo(target3)
                .waitSeconds(1)
                .strafeTo(accept)
                .waitSeconds(1)
                .strafeTo(target4)
                .waitSeconds(1)
                .strafeTo(accept)
                .waitSeconds(1)
                .strafeTo(target5)
                .strafeTo(end)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
