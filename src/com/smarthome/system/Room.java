package com.smarthome.system;
import com.smarthome.interfaces.Controllable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roomName;
    private List<Controllable> devices = new ArrayList<>();

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public void addDevice(Controllable device) {
        devices.add(device);
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Controllable> getDevices() {
        return new ArrayList<>(devices);
    }


    public void turnOffAllDevicesInRoom() {
        devices.stream().forEach(Controllable::turnOff);
    }

    public String getRoomStatus() {
        return devices.stream().map(Controllable::getStatus).collect(Collectors.joining("\n"));
    }





}
