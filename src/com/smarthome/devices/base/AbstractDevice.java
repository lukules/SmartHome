package com.smarthome.devices.base;
import com.smarthome.enums.ConnectionStatus;
import com.smarthome.enums.DeviceType;
import com.smarthome.interfaces.Controllable;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractDevice implements Controllable, Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceId;
    private String deviceName;
    protected boolean powerOn;
    private final DeviceType type;
    protected ConnectionStatus connectionStatus;

    public AbstractDevice(String deviceId, String deviceName, DeviceType deviceType) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.powerOn = false;
        this.type = deviceType;
        this.connectionStatus = ConnectionStatus.CONNECTED;
    }

    public void turnOn() {
        powerOn = true;
        System.out.println("Device " + deviceName + "turned on");
    }

    public void turnOff() {
        powerOn = false;
        System.out.println("Device " + deviceName + "turned off");
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean isPowerOn() {
        return powerOn;
    }

    public abstract String getStatus();

    protected void setConnectionStatus(ConnectionStatus status) {
        connectionStatus = status;
    }


    @Override
    public String toString() {
        return "Device ID: " + deviceId + ", Device name: " + deviceName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;

        if(o == null || this.getClass() != o.getClass()) return false;

        AbstractDevice that = (AbstractDevice) o;

        return deviceId.equals(that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId);
    }


}
