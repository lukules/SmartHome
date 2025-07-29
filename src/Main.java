import com.smarthome.devices.concrete.Light;
import com.smarthome.devices.concrete.Thermostat;
import com.smarthome.enums.DeviceType;
import com.smarthome.exceptions.DeviceAlreadyExistsException;
import com.smarthome.exceptions.DeviceNotFoundException;
import com.smarthome.exceptions.RoomAlreadyExistsException;
import com.smarthome.exceptions.RoomNotFoundException;
import com.smarthome.interfaces.Controllable;
import com.smarthome.system.SmartHomeController;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private static final String SAVE_FILE_NAME = "smarthome.dat";

    public static void main(String[] args) {
        SmartHomeController controller;

        try {
            controller = SmartHomeController.loadStateFromFile(SAVE_FILE_NAME);
            System.out.println("Home state loaded from file: " + SAVE_FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Creating new smart home system.");
            controller = new SmartHomeController();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load home state from file. Error: " + e.getMessage());
            System.err.println("Creating new smart home system.");
            controller = new SmartHomeController();
        }

        System.out.println("\n--- Smart Home simulation started ---");

        try {
            if (controller.getRooms().isEmpty()) {
                System.out.println("\n--- Initial home setup ---");
                controller.addRoom("Living Room");
                controller.addRoom("Kitchen");
                controller.addRoom("Bedroom");

                controller.addDeviceToRoom("Living Room", new Light("L001", "Main Living Room Light", DeviceType.LIGHT));
                controller.addDeviceToRoom("Living Room", new Thermostat("T001", "Living Room Thermostat", DeviceType.THERMOSTAT));
                controller.addDeviceToRoom("Kitchen", new Light("L002", "Kitchen Counter Light", DeviceType.LIGHT));
            }

            System.out.println("\n--- Current home status ---");
            controller.getSystemStatusReport();

            System.out.println("\n--- Interacting with devices ---");
            Controllable livingRoomLight = controller.getDevice("L001");
            livingRoomLight.turnOn();
            if (livingRoomLight instanceof Light) {
                ((Light) livingRoomLight).setBrightness(80);
            }

            Controllable livingRoomThermostat = controller.getDevice("T001");
            livingRoomThermostat.turnOn();
            if (livingRoomThermostat instanceof Thermostat) {
                ((Thermostat) livingRoomThermostat).setTargetTemperature(22.5);
            }

            System.out.println("\n--- Home status after changes ---");
            controller.getSystemStatusReport();

            System.out.println("\n--- Testing error handling ---");
            System.out.println("Attempt to add existing room 'Living Room'...");
            controller.addRoom("Living Room");

        } catch (RoomAlreadyExistsException | DeviceAlreadyExistsException | RoomNotFoundException | DeviceNotFoundException e) {
            System.err.println("Expected error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Turning off all devices ---");
        controller.turnOffAllDevices();
        try {
            controller.getSystemStatusReport();
        } catch (RoomNotFoundException e) {
            System.err.println("Room not found: " + e.getMessage());
        }


        try {
            controller.saveStateToFile(SAVE_FILE_NAME);
        } catch (IOException e) {
            System.err.println("Failed to save home state to file. Error: " + e.getMessage());
        }

        System.out.println("\n--- Simulation ended ---");
    }
}
