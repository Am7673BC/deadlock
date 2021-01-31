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
        truckMoves.start();
        suvMoves.start();
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