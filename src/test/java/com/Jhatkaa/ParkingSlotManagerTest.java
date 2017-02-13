package test.java.com.Jhatkaa;

import main.java.com.Jhatkaa.ParkingSlotManager;
import main.java.com.Jhatkaa.entity.Car;
/**
 * Created by Vikram Aditya on 2/13/2017.
 */
public class ParkingSlotManagerTest {

    private ParkingSlotManager slotManager;

    //@BeforeTest

    public ParkingSlotManagerTest() {
        slotManager = new ParkingSlotManager();
    }

    //@Test
    public void createGarageTest() {
        slotManager.createGarage(5);
        //Assert.assertTrue
        if (slotManager.availableSlots != null && slotManager.slotToCar.isEmpty() &&
                slotManager.colourToCar.isEmpty() && slotManager.regNumberToSlot.isEmpty()) {
            System.out.println("create_garage_test_passed");
        } else {
            System.out.println("create_garage_test_failed");
        }
    }

    public void parkCarTest() {
        Car car = new Car("TS-123", "Red");
        slotManager.parkCar(car);

        if (!slotManager.slotToCar.isEmpty() && slotManager.colourToCar.containsKey(car.getColour()) &&
                slotManager.regNumberToSlot.containsKey(car.getRegNumber())) {
            System.out.println("park_car_test_passed");
        } else {
            System.out.println("park_car_test_failed");
        }
    }

    public void regNumberFromColourTest() {
        slotManager.regNumberFromColour("Red");
        System.out.println("reg_number_from_colour_test_passed");
    }

    public void slotsFromColourTest() {
        slotManager.slotsFromColour("Red");
        System.out.println("slots_from_colour_test_passed");
    }

    public void slotFromRegNumberTest() {
        slotManager.slotFromRegNumber("TS-123");
        System.out.println("slot_from_reg_number_test_passed");
    }

    public void statusTest() {
        slotManager.status();
        System.out.println("status_test_passed");
    }

    public void leaveCarTest() {
        slotManager.leaveCar(1);
        if (slotManager.slotToCar.isEmpty() && slotManager.colourToCar.isEmpty() && slotManager.regNumberToSlot.isEmpty()) {
            System.out.println("park_car_test_passed");
        } else {
            System.out.println("park_car_test_failed");
        }
    }

}
