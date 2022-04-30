package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car car;

    @BeforeEach
    public void createCar() {
        car = new Car("Skoda", "ABC-1234", 2019, "Alex Bon");
    }

    @Test
    void getManufacturer() {
        assertEquals("Skoda", car.getManufacturer());
    }

    @Test
    void getNumber() {
        assertEquals("ABC-1234", car.getNumber());
    }

    @Test
    void setNumber() {
        car.setNumber("ABCD-123");
        assertEquals("ABCD-123", car.getNumber());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC-123", "DEF-456", "DFG-789"})
    @NullSource
    @EmptySource
    void testSetNumberMultipleValues(String number) {
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @ParameterizedTest
    @CsvSource({"'ABSD-123', 'ABS-13'", "'DEF-456', 'EF-45996'"})
    void testSetNumberMultipleValues(String number, String x) {
        car.setNumber(number);
        assertNotEquals(x, car.getNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 5",
            "5, 9",
            "12, 16",
            "32, 36"})
    void testInt(int input, int output) {
        assertEquals(output, car.testInt(input));
    }

    @ParameterizedTest
    @DisplayName("Test demonstrates how test data could be loaded from file")
    @CsvFileSource(resources = "/com/company/test-data.csv", delimiter = '|', numLinesToSkip = 1)
    public void testNumbers(String input, String expected) {
        car.setNumber(input);
        assertEquals(expected, car.getNumber());
    }


    @Test
    void getYear() {
        assertEquals(2019, car.getYear());
    }

    @Test
    void getOwner() {
        assertEquals("Alex Bon", car.getOwner());
    }

    @Test
    void setOwner() {
        car.setOwner("Denis");
        assertEquals("Denis", car.getOwner());
    }

    @Test
    void getListOfOwners() {
        assertArrayEquals(new String[]{"Alex Bon"}, car.getOwners().toArray());
    }

    @Test
    void getListOfTwoOwners() {
        car.setOwner("Second Owner");
        assertArrayEquals(new String[]{"Alex Bon", "Second  Owner"}, car.getOwners().toArray());
    }

    @Test
    public void testPrivateMethod() {
        try {
            Method method = Car.class.getDeclaredMethod("testMethod", null);
            method.setAccessible(true);
            assertEquals(method.invoke(car).toString(), "abc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrivateMethodArgument() {
        try {
            Method method = Car.class.getDeclaredMethod("testMethod", String.class);
            method.setAccessible(true);
            assertEquals(method.invoke(car, "Input string").toString(), "Input string");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}