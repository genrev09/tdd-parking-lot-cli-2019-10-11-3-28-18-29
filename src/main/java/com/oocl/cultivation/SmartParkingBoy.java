package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithMoreSpace = getParkingLotList().stream()
                .reduce(((parkingLot, parkingLot2) -> parkingLot.countCars() <= parkingLot2.countCars() ? parkingLot : parkingLot2)).orElse(null);

        if (parkingLotWithMoreSpace == null){
            setLastErrorMessage("Not enough position.");
            return null;
        }
        return parkingLotWithMoreSpace.addCar(car);
    }
}
