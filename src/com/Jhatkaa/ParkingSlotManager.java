package com.Jhatkaa;

import com.Jhatkaa.entity.Car;
import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vikram Aditya on 2/12/2017.
 */
public class ParkingSlotManager {

    private MinHeap availableSlots;
    private Map<String, Integer> regNumberToSlot;
    private Map<String, Map<Integer, String>> colourToCar;
    private String CREATED_GARAGE = "Created a parking garage with %d slots";

    public void createGarage(int parkingSize) {
        availableSlots = new MinHeap(parkingSize);
        colourToCar = new HashMap<>();
        regNumberToSlot = new HashMap<>();
        for (int slot = 1; slot <= parkingSize; slot++) {
            availableSlots.insert(slot);
        }
        System.out.format(CREATED_GARAGE, parkingSize);
    }


}
