package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware;
import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;

@Autonomous (name = "semifinal")
public class ahorasi extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive mecanumDrive;
        hardware Hardware;
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        Hardware = new hardware();

        Hardware.init(hardwareMap);

        mecanumDrive.setPoseEstimate(new Pose2d(-36.0, 59.0, Math.toRadians(270.0)));

        TrajectorySequence secuencia1 = mecanumDrive.trajectorySequenceBuilder(new Pose2d(-36.0, 59.0, Math.toRadians(270.0)))
                .lineToSplineHeading(new Pose2d(-35.0, 9.0, Math.toRadians(90)))

                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    Hardware.eskupidito();
                })
                .UNSTABLE_addTemporalMarkerOffset(2, () -> {
                    Hardware.intake.setPower(0);
                })
                .waitSeconds(2)

                .lineToSplineHeading(new Pose2d(-35,7,Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(53,8))

                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    Hardware.subir();
                    Hardware.rotador.setPosition(0);
                    Hardware.Elev(0.6);
                })
                .lineToConstantHeading(new Vector2d(53,30))

                .UNSTABLE_addTemporalMarkerOffset(  0.5,()->{
                    Hardware.brazo.setPosition(0);
                    Hardware.Elev(0);
                })
                .waitSeconds(2)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    Hardware.bajar();
                    Hardware.rotador.setPosition(1);
                })
                .lineToConstantHeading(new Vector2d(53,8))
                .build();


        waitForStart();
        mecanumDrive.setPoseEstimate(new Pose2d(-36.0, 59.0, Math.toRadians(270.0)));
        mecanumDrive.followTrajectorySequence(secuencia1);
    }
}
