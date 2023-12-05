package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;


@TeleOp (name = "teleop")
public class teleop extends OpMode {

    SampleMecanumDrive mecanumDrive;
    hardware Hardware;





    @Override
    public void init() {
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        Hardware = new hardware();

        Hardware.init(hardwareMap);


    }

    @Override
    public void loop() {
        telemetry.addData("posicion y", mecanumDrive.rightRear.getCurrentPosition());
        telemetry.addData("posicion x", mecanumDrive.rightFront.getCurrentPosition());

        double trigger = gamepad1.left_trigger;

        if (gamepad1.right_trigger > 0.35) {
            trigger = gamepad1.right_trigger;
        }

        double turbo = 1 - (trigger * 0.65);


        if (gamepad2.dpad_right){
            Hardware.brazo.setPosition(0);
        }





        if (gamepad2.b){
            Hardware.shupar();
        }

        else if (gamepad2.a){
            Hardware.eskupir();
        }
        else {
            Hardware.intake.setPower(0);
        }


       mecanumDrive.setWeightedDrivePower(new Pose2d(-gamepad1.left_stick_y * turbo, -gamepad1.left_stick_x * turbo, -gamepad1.right_stick_x * turbo));

        Hardware.Elev(gamepad2.left_stick_y);

        if (gamepad2.dpad_up) {
            Hardware.subir();
            Hardware.rotador.setPosition(0);
        }else if(gamepad2.dpad_down){
            Hardware.bajar();
            Hardware.rotador.setPosition(1);
        }


        if (gamepad1.y){
            Hardware.avion();
        }else if (gamepad1.x){
            Hardware.avion2();
        }


    }
}
