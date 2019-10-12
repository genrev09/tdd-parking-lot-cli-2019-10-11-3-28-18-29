package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

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

    @Test
    void should_not_fetch_car_using_used_ticket() {

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Car car2 = parkingBoy.fetch(parkingTicket);

        assertNull(car2);
    }

    @Test
    void should_not_park_when_parking_lot_is_full() {
        Car ExceedingCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for (int park_times = 0; park_times < parkingLot.getCapacity(); park_times++){
            parkingBoy.park(new Car());
        }
        ParkingTicket ticket = parkingBoy.park(ExceedingCar);
        assertNull(ticket);
    }

    @Test
    void should_message_unrecognized_parking_ticket_when_ticket_is_null() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car nullCar = parkingBoy.fetch(null);

        String actualResponseMessage = systemOut();

        assertEquals("Unrecognized parking ticket",actualResponseMessage);
    }

    @Test
    void should_message_unrecognized_parking_ticket_when_ticket_is_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car originalCar = new Car();
        ParkingTicket ticket = parkingBoy.park(originalCar);
        Car fetchedCar = parkingBoy.fetch(ticket);

        Car nullCar = parkingBoy.fetch(ticket);
        String actualResponseMessage = systemOut();

        assertEquals("Unrecognized parking ticket",actualResponseMessage);
    }

    private String systemOut() {
        return outContent.toString();
    }
}
