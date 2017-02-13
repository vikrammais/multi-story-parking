package main.java.com.Jhatkaa;

import main.java.com.Jhatkaa.entity.Car;
import main.java.com.Jhatkaa.entity.EventName;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {

    private static final String INVALID_INPUT = "Please check the input provided";
    private static final String SOMETHING_WENT_WRONG = "Oops...Garage Under Maintenance";

    public static void main(String[] args) {
        try {
            if (args != null && args.length > 0) {
                if (args.length != 3) {
                    throw new Exception(INVALID_INPUT);
                } else {
                    System.setIn(new FileInputStream(args[0]));
                    System.setOut(new PrintStream(new FileOutputStream(args[2])));
                    eventInvoker(false);
                }
            } else {
                eventInvoker(true);
            }
        } catch (Exception e) {
            System.out.println(INVALID_INPUT);
        }

    }


    public static void eventInvoker(boolean wait) throws Exception {
        Scanner sc = new Scanner(System.in);
        ParkingSlotManager slotManager = new ParkingSlotManager();
        boolean eventListener = true;
        while (eventListener && (sc.hasNext() || wait)) {
            String input = sc.next();
            EventName eventName = EventName.getEventName(input);
            String colour;
            String regNumber;
            if (eventName == null) {
                System.out.println(INVALID_INPUT);
                continue;
            }
            try {
                switch (eventName) {
                    case CREATE_GARAGE:
                        int parkingSize = sc.nextInt();
                        slotManager.createGarage(parkingSize);
                        break;
                    case PARK:
                        regNumber = sc.next();
                        colour = sc.next().toUpperCase();
                        Car car = new Car(regNumber, colour);
                        slotManager.parkCar(car);
                        break;
                    case LEAVE:
                        int slotNumber = sc.nextInt();
                        slotManager.leaveCar(slotNumber);
                        break;
                    case NUMBER_FROM_COLOUR:
                        colour = sc.next().toUpperCase();
                        slotManager.regNumberFromColour(colour);
                        break;
                    case SLOTS_FROM_COLOUR:
                        colour = sc.next().toUpperCase();
                        slotManager.slotsFromColour(colour);
                        break;
                    case SLOT_FROM_NUMBER:
                        regNumber = sc.next();
                        slotManager.slotFromRegNumber(regNumber);
                        break;
                    case STATUS:
                        slotManager.status();
                        break;
                    case EXIT:
                        eventListener = false;
                        break;
                    default:
                        System.out.println(INVALID_INPUT);
                }
            } catch (Exception e) {
                System.out.println(SOMETHING_WENT_WRONG);
            }
        }
    }
}
