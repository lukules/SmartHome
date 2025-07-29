## Overview

SmartHome is a Java-based, console-driven simulation of a smart home system designed for educational purposes. The project focuses on building a solid business logic and architecture for managing smart devices scattered in multiple rooms, without involving any graphical user interface (GUI).

Its main goals are to practice and understand:

- Object-oriented design with interfaces, abstract classes, and inheritance.
- Usage of enums for device states and types.
- Collections API with generic interfaces.
- Stream API for functional-style processing.
- Custom exceptions for robust error handling.
- Java serialization for saving and loading application state.
- Clean code architecture and modular packaging.

## Project Structure and Packages

The project is organized into the following Java packages for maintainability and clarity:

- **com.smarthome.main**  
  Entry point class *Main* and future GUI starter *SmartHomeApp*.

- **com.smarthome.interfaces**  
  Interfaces, starting with the core *Controllable* interface which defines the contract for devices.

- **com.smarthome.devices.base**  
  Abstract base class *AbstractDevice* implementing common device logic and shared properties.

- **com.smarthome.devices.concrete**  
  Concrete device implementations like *Light* and *Thermostat*.

- **com.smarthome.enums**  
  Enumerations for device types (*DeviceType*), connection statuses (*ConnectionStatus*), and future-proof additions (e.g., security cameras).

- **com.smarthome.exceptions**  
  Custom exception classes for checked and unchecked exceptions, enabling professional error management.

- **com.smarthome.system**  
  Core system classes including *Room* – a container for devices, and *SmartHomeController* – the central brain managing rooms and devices.
