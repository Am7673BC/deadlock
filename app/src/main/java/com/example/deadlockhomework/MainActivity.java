package com.example.deadlockhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Car> carsOnTheBridge;
    Car truck = new Car(13);
    Car suv = new Car(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(truck.getLenght()>suv.getLenght()){
            suvMoves.start();
            try {
                suvMoves.join(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            truckMoves.start();
        }else{
            truckMoves.start();
            try {
                truckMoves.join(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            suvMoves.start();
        }
    }

    Thread truckMoves = new Thread(new Runnable() {
        @Override
        public void run() {
            moveOnBridge(truck);
        }
    });
    Thread suvMoves = new Thread(new Runnable() {
        @Override
        public void run() {
            moveOnBridge(suv);
        }
    });

    private synchronized void moveOnBridge(Car Car) {
        carsOnTheBridge.add(Car);
        System.out.println(carsOnTheBridge.get(0) + "on the bridge");
        carsOnTheBridge.remove(0);
    }
}