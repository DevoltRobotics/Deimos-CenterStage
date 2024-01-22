package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hardware {
    public DcMotor intake;
    public DcMotor intwo;

    public DcMotor elev;

    public DcMotor colgadera;

    public Servo brazo;

    public Servo rotador;
    public Servo Resortera;


    public void init(HardwareMap hardwaremap) {

        intake = hardwaremap.dcMotor.get("intake");//motor expansion 2
        intwo = hardwaremap.dcMotor.get("intwo");
        elev = hardwaremap.get(DcMotor.class, "elevdor");//motor expansion 1
        colgadera = hardwaremap.get(DcMotor.class,"colgadera");
        brazo = hardwaremap.get(Servo.class,"brazo");
        rotador = hardwaremap.get(Servo.class,"rotador");
        Resortera = hardwaremap.get(Servo.class, "resortera");

        elev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elev.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        colgadera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }



    public  void colgare(double poder){
        colgadera.setPower(poder);
    }

    public void elevauto(double poder, int ticks){
        elev.setTargetPosition(ticks);
        elev.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elev.setPower(poder);
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
        brazo.setPosition(0.2);
    }


    public void Elev(double power) {
        elev.setPower(power);
    }


    public void shupar() {
        intake.setPower(1);
        intwo.setPower(1);
    }

    public void eskupir() {
        intake.setPower(-1);
        intwo.setPower(-1);
    }

    public void eskupidito(){
        intake.setPower(-0.5);
    }




}
