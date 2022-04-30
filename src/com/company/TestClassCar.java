package com.company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassCar {

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    @EnabledOnJre(JRE.JAVA_11)
    public void getCarYear() {
        Car car = new Car("Ford", "12345-DFG", 2005, "Owner1");
        assertEquals(2005, car.getYear());
    }

    @Test
    public void getCarOwner() {
        Car car = new Car("Ford", "12345-DFG", 2005, "Owner1");
        assertEquals("Owner1", car.getOwner());
    }

    @Test
    public void getCarManufacturer() {
        Car car = new Car("Ford", "12345-DFG", 2005, "Owner1");
        assertEquals("Ford", car.getManufacturer());
    }
}
