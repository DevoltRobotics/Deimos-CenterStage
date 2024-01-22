package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;


@TeleOp(name = "teleop azules")
public class teleop2 extends OpMode {

    SampleMecanumDrive mecanumDrive;
    hardware Hardware;

    int targetposition;


    @Override
    public void init() {
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        Hardware = new hardware();

        Hardware.init(hardwareMap);

        mecanumDrive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(270)));
    }

    @Override
    public void loop() {
        telemetry.addData("posicion y", mecanumDrive.rightRear.getCurrentPosition());
        telemetry.addData("posicion x", mecanumDrive.rightFront.getCurrentPosition());
        telemetry.addData("elevador", Hardware.elev.getCurrentPosition());

        double trigger = gamepad1.left_trigger;

        if (gamepad1.right_trigger > 0.35) {
            trigger = gamepad1.right_trigger;
        }

        double turbo = 1 - (trigger * 0.65);


        if (gamepad2.b) {
            Hardware.shupar();
        } else if (gamepad2.a) {
            Hardware.eskupir();
        } else {
            Hardware.intake.setPower(0);
            Hardware.intwo.setPower(0);
        }

        Vector2d drive = new Vector2d(-gamepad1.left_stick_y * turbo, -gamepad1.left_stick_x * turbo)
                .rotated(-mecanumDrive.getPoseEstimate().getHeading());

        mecanumDrive.setWeightedDrivePower(new Pose2d(drive.getX(), drive.getY(), -gamepad1.right_stick_x * turbo));

        if (gamepad1.dpad_left) {
            mecanumDrive.setPoseEstimate(new Pose2d());
        }


        if (gamepad2.dpad_up) {
            Hardware.subir();
            Hardware.rotador.setPosition(0.3);
        } else if (gamepad2.dpad_down) {
            Hardware.bajar();
            Hardware.rotador.setPosition(1);
        }


        if (gamepad2.dpad_right) {
            Hardware.rotador.setPosition(0.5);//dejar
        } else if (gamepad2.dpad_left) {
            Hardware.rotador.setPosition(0.2);
        }

        if (gamepad1.y) {
            Hardware.avion();
        } else if (gamepad1.x) {
            Hardware.avion2();
        }

        if (gamepad1.dpad_up) {
            Hardware.colgare(1);
        } else if (gamepad1.dpad_down) {
            Hardware.colgare(-1);
        } else {
            Hardware.colgare(0);
        }

        if (Math.abs(gamepad2.left_stick_y) >= 0.1) {
            targetposition += (gamepad2.left_stick_y) * 45
            ;
        } else {
            if (gamepad2.y) {
                targetposition = 400;
            } else if (gamepad2.x) {
                targetposition = 0;
            }
        }

        Hardware.elevauto(1, targetposition);
        mecanumDrive.update();
    }

}

