package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;
import com.smarthome.enums.DeviceType;

import java.io.Serializable;

public class Thermostat extends AbstractDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private double targetTemperature;

    public Thermostat(String deviceId, String deviceName, DeviceType type) {
        super(deviceId, deviceName, type);
        this.targetTemperature = 20.0;
    }

    public void setTargetTemperature(double temp) {
        if(temp < 0) {
            System.err.println("You'll freeze to death!");
            return;
        }
        if(temp > 40) {
            System.err.println("Temperature too high!");
            return;
        }
        if(isPowerOn()) {
            targetTemperature = temp;
            System.out.println("Temperature set to " + targetTemperature + " degrees");
        } else System.err.println("Your device is turned off so you cannot change the temperature");

    }

    public String getStatus() {
        return "ID" + getDeviceId() + "Thermostat " + getDeviceName() + " is ON (Temperature: " + targetTemperature + "degrees) ";
    }
}
