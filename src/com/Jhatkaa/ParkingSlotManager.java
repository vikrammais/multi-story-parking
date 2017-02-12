package com.Jhatkaa;

import com.Jhatkaa.entity.Car;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Vikram Aditya on 2/12/2017.
 */
public class ParkingSlotManager {

    private MinHeap availableSlots;
    private Map<String, Integer> regNumberToSlot;
    private Map<String, Map<Integer, String>> colourToCar;
    private Map<Integer, Car> slotToCar;

    private String CREATED_GARAGE = "Created a parking garage with %d slots%n";
    private String GARAGE_FULL = "Sorry, parking garage is full";
    private String CAR_PARKED = "Park in slot number: %d%n";
    private String CAR_LEFT = "Slot number %d is free%n";
    private String NOT_FOUND = "Not found";

    private String GARAGE_EMPTY = "Sorry, parking garage is empty";
    private String INVALID_INPUT = "Please check the input provided";
    private String DELIMITER = ", ";
    private String STATUS_FORMATTER = "%-6s %-20s %-10s%n";

    public void createGarage(int parkingSize) {
        availableSlots = new MinHeap(parkingSize);
        colourToCar = new HashMap<>();
        regNumberToSlot = new HashMap<>();
        slotToCar = new HashMap<>();

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
            slotToCar.put(availableSlot, car);

            Map<Integer, String> carsData = getCarsFromColour(car.getColour());
            carsData.put(availableSlot, car.getRegNumber());
            colourToCar.put(car.getColour(), carsData);

            availableSlots.removemin();
            System.out.format(CAR_PARKED, availableSlot);
        }
    }

    public void leaveCar(int slotNumber) {
        Car leavingCar = slotToCar.get(slotNumber);
        if (leavingCar == null) {
            System.out.println(INVALID_INPUT);
        } else {
            regNumberToSlot.remove(leavingCar.getRegNumber());
            removeCarFromColourMap(leavingCar, slotNumber);
            slotToCar.remove(slotNumber);
            availableSlots.insert(slotNumber);
            System.out.format(CAR_LEFT, slotNumber);
        }
    }

    public void regNumberFromColour(String colour) {
        Map<Integer, String> carsData = getCarsFromColour(colour);
        if (!carsData.isEmpty()) {
            StringJoiner stringJoiner = new StringJoiner(DELIMITER);
            for (Map.Entry<Integer, String> carData : carsData.entrySet()) {
                stringJoiner.add(carData.getValue());
            }
            System.out.println(stringJoiner);
        }
        else {
            System.out.println(NOT_FOUND);
        }
    }

    public void slotsFromColour(String colour) {
        Map<Integer, String> carsData = getCarsFromColour(colour);
        if (!carsData.isEmpty()) {
            StringJoiner stringJoiner = new StringJoiner(DELIMITER);
            for (Map.Entry<Integer, String> carData : carsData.entrySet()) {
                stringJoiner.add(carData.getKey().toString());
            }
            System.out.println(stringJoiner);
        }
        else {
            System.out.println(NOT_FOUND);
        }
    }

    public void slotFromRegNumber(String regNumber) {
        Integer slot = regNumberToSlot.get(regNumber);
        if (slot != null) {
            System.out.println(slot);
        } else {
            System.out.println(NOT_FOUND);
        }
    }

    public void status() {
        if (slotToCar.isEmpty()) {
            System.out.println(GARAGE_EMPTY);
        } else {
            System.out.printf(STATUS_FORMATTER, "Slot", "Registration", "Colour");
            for (Map.Entry<Integer, Car> entry : slotToCar.entrySet()) {
                System.out.printf(STATUS_FORMATTER, entry.getKey().toString(), entry.getValue().getRegNumber(),
                        entry.getValue().getColour());
            }
        }
    }

    private Map<Integer, String> getCarsFromColour(String colour) {
        if (colourToCar.get(colour) != null) {
            return colourToCar.get(colour);
        }
        return new HashMap<>();
    }

    private void removeCarFromColourMap(Car leavingCar, int slotNumber) {
        Map<Integer, String> carsData = getCarsFromColour(leavingCar.getColour());
        carsData.remove(slotNumber);
        if (carsData.isEmpty()) {
            colourToCar.remove(leavingCar.getColour());
        } else {
            colourToCar.put(leavingCar.getColour(), carsData);
        }
    }

}
