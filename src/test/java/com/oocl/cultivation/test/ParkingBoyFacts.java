package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_car_into_the_parking_lot_by_parking_boy() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_fetch_the_car_from_parking_lot_by_parking_boy() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car parkedCar = new Car();
        
        ParkingTicket parkingTicket = parkingBoy.park(parkedCar);

        Car car = parkingBoy.fetch(parkingTicket);
        assertNotNull(car);
    }

    @Test
    void should_Park_multiple_cars_into_parking_lot_and_get_right_car_using_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        ParkingTicket parkingTicket3 = parkingBoy.park(car3);

        assertEquals(parkingLot.countCars(),3);
        assertEquals(car1,parkingLot.getCar(parkingTicket1));
    }

    @Test
    void should_not_fetch_car_if_has_invalid_or_no_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        assertNull(parkingLot.getCar(wrongTicket));
        assertNull(parkingLot.getCar(null));
    }
}
