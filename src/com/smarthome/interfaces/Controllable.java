package com.smarthome.interfaces;

public interface Controllable {
    void turnOn();
    void turnOff();
    String getStatus();
    String getDeviceId();
    String getDeviceName();


}
