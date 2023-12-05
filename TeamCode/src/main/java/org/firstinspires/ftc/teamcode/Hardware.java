package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hardware {
    public DcMotor intake;

    public DcMotor elev;

    public Servo brazo;

    public Servo rotador;
    public Servo Resortera;


    public void init(HardwareMap hardwaremap) {

        intake = hardwaremap.dcMotor.get("intake");//motor expansion 2
        elev = hardwaremap.get(DcMotor.class, "elevdor");//motor expansion 1
        brazo = hardwaremap.get(Servo.class,"brazo");
        rotador = hardwaremap.get(Servo.class,"rotador");
        Resortera = hardwaremap.get(Servo.class, "resortera");

        elev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }



    public void avion(){
        Resortera.setPosition(0);
    }

    public void avion2(){
        Resortera.setPosition(0.9);
    }
    public void bajar(){
        brazo.setPosition(1);
    }

    public void subir(){
        brazo.setPosition(0.1);
    }


    public void Elev(double power) {
        elev.setPower(power);
    }


    public void shupar() {
        intake.setPower(1);
    }

    public void eskupir() {
        intake.setPower(-1);
    }

    public void eskupidito(){
        intake.setPower(-0.7);
    }




}
