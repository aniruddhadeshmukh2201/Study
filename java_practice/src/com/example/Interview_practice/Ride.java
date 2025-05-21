package com.example.Interview_practice;

class RideStatus {

}

class Location {

}

class Driver {

}

class Rider {

}



public class Ride {
    private final String rideId;
    private final Location pickupLocation;
    private final Location dropLocation;
    private final Driver driver;
    private final Rider rider;

    private volatile RideStatus status;
    private double fare;


    // constructors and getters

    public Ride(String rideId, Location pickLocation, Location dropLocation, Driver driver, Rider rider) {
        this.driver = driver;
        this.rider = rider;
        this.pickupLocation = pickLocation;
        this.dropLocation = dropLocation;
        this.rideId = rideId;
    }


    public Driver getDriver() {
        return driver;
    }


    public Location getDropLocation() {
        return dropLocation;
    }

    public double getFare() {
        return fare;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public String getRideId() {
        return rideId;
    }

    public Rider getRider() {
        return rider;
    }

    public RideStatus getStatus() {
        return status;
    }
}
