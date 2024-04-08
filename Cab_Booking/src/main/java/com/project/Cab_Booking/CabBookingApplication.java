package com.project.Cab_Booking;

import java.util.ArrayList;
import java.util.List;

class User {
    String name;
    String gender;
    int age;

    User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}

class Driver {
    String name;
    String gender;
    int age;
    String vehicle;
    String plateNumber;
    int[] currentLocation;
    boolean available;

    Driver(String name, String gender, int age, String vehicle, String plateNumber, int[] currentLocation) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.vehicle = vehicle;
        this.plateNumber = plateNumber;
        this.currentLocation = currentLocation;
        this.available = true;
    }
}

class CabBookingApplication {
    List<User> users;
    List<Driver> drivers;

    CabBookingApplication() {
        this.users = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    void addUser(String name, String gender, int age) {
        User user = new User(name, gender, age);
        users.add(user);
    }

    void addDriver(String name, String gender, int age, String vehicle, String plateNumber, int[] currentLocation) {
        Driver driver = new Driver(name, gender, age, vehicle, plateNumber, currentLocation);
        drivers.add(driver);
    }

    List<Driver> findRide(String username, int[] source, int[] destination) {
        List<Driver> availableRides = new ArrayList<>();
        for (Driver driver : drivers) {
            double distance = calculateDistance(driver.currentLocation, source);
            if (driver.available && distance <= 5) {
                availableRides.add(driver);
            }
        }
        return availableRides;
    }

    Driver chooseRide(String username, String driverName) {
        for (Driver driver : drivers) {
            if (driver.name.equals(driverName)) {
                driver.available = false;
                return driver;
            }
        }
        return null;
    }

    double calculateDistance(int[] point1, int[] point2) {
        int x1 = point1[0];
        int y1 = point1[1];
        int x2 = point2[0];
        int y2 = point2[1];
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        CabBookingApplication app = new CabBookingApplication();

        // Onboard users
        app.addUser("Abhishek", "M", 23);
        app.addUser("Rahul", "M", 29);
        app.addUser("Nandini", "F", 22);

        // Onboard drivers
        app.addDriver("Driver1", "M", 22, "Swift", "KA-01-12345", new int[]{2, 2});
        app.addDriver("Driver2", "M", 29, "Swift", "KA-01-12345", new int[]{11, 10});
        app.addDriver("Driver3", "M", 24, "Swift", "KA-01-12345", new int[]{5, 3});

        // Test cases
        System.out.println("User trying to get a ride:");
        List<Driver> rides = app.findRide("Abhishek", new int[]{0, 0}, new int[]{20, 1});
        if (rides.isEmpty()) {
            System.out.println("Output: No ride found [Since all the drivers are more than 5 units away from user]");
        } else {
            System.out.print("Output: ");
            for (Driver ride : rides) {
                System.out.print(ride.name + " ");
            }
            System.out.println();
        }

        Driver ride = app.chooseRide("Rahul", "Driver1");
        System.out.println("Output: " + ride.name + " [Available]");

        rides = app.findRide("Nandini", new int[]{15, 6}, new int[]{20, 4});
        if (rides.isEmpty()) {
            System.out.println("Output: No ride found [Driver one is set to not available]");
        } else {
            System.out.print("Output: ");
            for (Driver driver : rides) {
                System.out.print(driver.name + " ");
            }
            System.out.println();
        }
    }
}

