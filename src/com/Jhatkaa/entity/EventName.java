package com.Jhatkaa.entity;

/**
 * Created by Vikram Aditya on 2/12/2017.
 */
public enum EventName {
    CREATE_GARAGE("create_parking_garage"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    NUMBER_FROM_COLOUR("registration_numbers_of_cars_with_colour"),
    SLOTS_FROM_COLOUR("slots_for_cars_with_colour"),
    SLOT_FROM_NUMBER("slot_for_registration_number"),
    EXIT("exit");

    private final String value;

    EventName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EventName getEventName(String value) {
        if (value != null) {
            EventName[] eventNames = EventName.values();
            for (EventName eventName : eventNames) {
                if (eventName.getValue().equalsIgnoreCase(value.trim())) {
                    return eventName;
                }
            }
        }
        return null;
    }

}
