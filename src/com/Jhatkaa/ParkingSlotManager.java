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
    private String GARAGE_FULL = "Sorry, parking garage is full";
    private String CAR_PARKED = "Park in slot number: %d";

    public void createGarage(int parkingSize) {
        availableSlots = new MinHeap(parkingSize);
        colourToCar = new HashMap<>();
        regNumberToSlot = new HashMap<>();
        for (int slot = 1; slot <= parkingSize; slot++) {
            availableSlots.insert(slot);
        }
        System.out.format(CREATED_GARAGE, parkingSize);
    }

    public void parkCar(Car car) {
        int availableSlot = availableSlots.getRoot();
        if (availableSlot == -1) {
            System.out.println(GARAGE_FULL);
        } else {
            regNumberToSlot.put(car.getRegNumber(), availableSlot);

            Map<Integer, String> carsData = getCarsFromColour(car.getColour());
            carsData.put(availableSlot, car.getRegNumber());
            colourToCar.put(car.getColour(), carsData);

            availableSlots.removemin();
            System.out.format(CAR_PARKED, availableSlot);
        }
    }

    private Map<Integer, String> getCarsFromColour(String colour) {
        if (colourToCar.get(colour) != null) {
            return colourToCar.get(colour);
        }
        return new HashMap<>();
    }

}
