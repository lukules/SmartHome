package com.smarthome.system;

import com.smarthome.exceptions.DeviceAlreadyExistsException;
import com.smarthome.exceptions.DeviceNotFoundException;
import com.smarthome.exceptions.RoomAlreadyExistsException;
import com.smarthome.exceptions.RoomNotFoundException;
import com.smarthome.interfaces.Controllable;

import javax.naming.ldap.Control;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SmartHomeController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Room> rooms;

    public SmartHomeController() {
        this.rooms = new HashMap<>();
    }

    public void addRoom(String roomName) throws RoomAlreadyExistsException {
        if (rooms.containsKey(roomName)) {
            throw new RoomAlreadyExistsException("Room \"" + roomName + "'\" already exists");
        }
        rooms.put(roomName, new Room(roomName));
        System.out.println("Room '" + roomName + "' has been added");
    }


    public void addDeviceToRoom(String roomName, Controllable device) throws RoomNotFoundException, DeviceAlreadyExistsException {
        Room room = rooms.get(roomName);
        if (room == null) {
            throw new RoomNotFoundException("Room '" + roomName + "' doesn't exist");
        }

        boolean deviceExists = room.getDevices().stream()
                .anyMatch(d -> d.getDeviceId().equals(device.getDeviceId()));

        if (deviceExists) {
            throw new DeviceAlreadyExistsException("Device with ID '" + device.getDeviceId() + "' already exists in this room '" + roomName + "'.");
        }

        room.addDevice(device);
        System.out.println("Device'" + device.getDeviceName() + "' has been added to '" + roomName + "'.");
    }

    public Controllable getDevice(String deviceId) throws DeviceNotFoundException {
        Optional<Controllable> foundDevice = rooms.values().stream()
                .flatMap(room -> room.getDevices().stream())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .findFirst();

        return foundDevice.orElseThrow(() -> new DeviceNotFoundException("Nie znaleziono urządzenia o ID: " + deviceId));
    }

    public void getSystemStatusReport() throws RoomNotFoundException{
        if(rooms.isEmpty()) {
            throw new RoomNotFoundException("No rooms found in the system");
        }
        for(Map.Entry<String, Room> entry : rooms.entrySet()) {
            System.out.println("Room: " + entry.getKey());
            System.out.println(entry.getValue().getRoomStatus());
        }
    }

    public void turnOffAllDevices() {
        rooms.values().stream()
                .forEach(Room::turnOffAllDevicesInRoom);
        System.out.println("All devices in the house have been turned off.");
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public void saveStateToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this); // zapisujemy cały obiekt 'this'
            System.out.println("State saved to file: " + filename);
        }
    }

    public static SmartHomeController loadStateFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            return (SmartHomeController) obj;
        }
    }



}
