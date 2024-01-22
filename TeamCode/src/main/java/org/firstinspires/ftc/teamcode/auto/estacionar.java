package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware;
import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;
@Config
public class estacionar extends LinearOpMode {
    @Override

    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive mecanumDrive;
        hardware Hardware;
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        Hardware = new hardware();

        Hardware.init(hardwareMap);

        mecanumDrive.setPoseEstimate(new Pose2d(-36.0, 59.0, Math.toRadians(270.0)));

        TrajectorySequence secuencia1 = mecanumDrive.trajectorySequenceBuilder(new Pose2d(10, 59.0, Math.toRadians(270.0)))
                .lineTo(new Vector2d(60, 59))
                .build();

        waitForStart();

        mecanumDrive.followTrajectorySequence(secuencia1);
    }
}
