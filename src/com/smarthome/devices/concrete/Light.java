package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;
import com.smarthome.enums.DeviceType;

import java.io.Serializable;

public class Light extends AbstractDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private int brightness; // 0 - 100

    public Light(String deviceId, String deviceName, DeviceType type) {
        super(deviceId, deviceName, type);
        this.brightness = 50;
    }

    public void setBrightness(int level) {

        if(level < 0) {
            System.err.println("Brightness level cannot be negative!");
            return;
        }
        if(level > 100) {
            System.err.println("Brightness level cannot be bigger than 100%");
            return;
        }

        if(isPowerOn()) {
            brightness = level;
        } else System.err.println("Cannot change the brightness level because your device" + getDeviceName() + "is off");

    }

    public String getStatus() {
        return "ID" + getDeviceId() + "Light " + getDeviceName() + " is ON (Brightness: " + brightness + "%) ";

    }


}
