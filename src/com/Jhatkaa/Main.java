package com.Jhatkaa;

import com.Jhatkaa.entity.EventName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {

    private static final String INVALID_INPUT = "Please check the input provided";

    public static void main(String[] args) {
        try {
            if (args != null && args.length > 0) {
                if (args.length != 3) {
                    throw new Exception(INVALID_INPUT);
                } else {
                    System.setIn(new FileInputStream(args[0]));
                    System.setOut(new PrintStream(new FileOutputStream(args[2])));
                    eventInvoker();
                }
            } else {
                eventInvoker();
            }
        } catch (Exception e) {
            System.out.println(INVALID_INPUT);
        }

    }


    public static void eventInvoker() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        EventName eventName = EventName.getEventName(input);
        if (eventName == null) {
            throw new Exception(INVALID_INPUT);
        }
        switch (eventName) {
            case CREATE_GARAGE:
                int parkingSize = sc.nextInt();
        }
    }

}
