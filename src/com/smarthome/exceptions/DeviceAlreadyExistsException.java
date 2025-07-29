package com.smarthome.exceptions;

public class DeviceAlreadyExistsException extends Exception {
    public DeviceAlreadyExistsException (String message) {
        super(message);
    }
}
