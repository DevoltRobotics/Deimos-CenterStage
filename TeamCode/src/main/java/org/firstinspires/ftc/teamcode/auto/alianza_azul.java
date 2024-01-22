package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware;
import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.vision.BlueTeamElementDetectionPipeline;
import org.firstinspires.ftc.teamcode.vision.TeamElementDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous (name = "azul")
public class
alianza_azul extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive mecanumDrive;
        hardware Hardware;
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        Hardware = new hardware();

        Hardware.init(hardwareMap);

        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
        BlueTeamElementDetectionPipeline pipeline = new BlueTeamElementDetectionPipeline();

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480);
                webcam.setPipeline(pipeline);
            }

            @Override
            public void onError(int errorCode) {
            }
        });

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
                    Hardware.elevauto(1,600);
                })
                .lineToConstantHeading(new Vector2d(53,30))

                .UNSTABLE_addTemporalMarkerOffset(  0,()->{
                    Hardware.subir();
                    Hardware.rotador.setPosition(0.2);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    Hardware.rotador.setPosition(0.5);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    Hardware.bajar();
                    Hardware.rotador.setPosition(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(1,()->{
                    Hardware.elevauto(1,0);
                })
                .lineToConstantHeading(new Vector2d(53,8))
                .build();


        while (!this.isStarted() && !isStopRequested()) {
            telemetry.addData("pattern", pipeline.getAnalysis());
            telemetry.update();
        }

        waitForStart();
        mecanumDrive.setPoseEstimate(new Pose2d(-36.0, 59.0, Math.toRadians(270.0)));

        if (pipeline.getAnalysis() == TeamElementDetectionPipeline.Pattern.A) {
            mecanumDrive.followTrajectorySequence(secuencia1);
        }
    }
}
