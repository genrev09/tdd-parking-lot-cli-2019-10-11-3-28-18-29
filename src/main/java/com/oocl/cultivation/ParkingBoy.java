package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (parkingLot.getAvailableParkingPosition() != 0)
            return parkingLot.addCar(car);
        else {
            System.out.print("Not enough position.");
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = parkingLot.getCar(ticket);
        if (ticket == null)
            System.out.print("Please provide your parking ticket.");
        else if (car == null)
            System.out.print("Unrecognized parking ticket.");
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
