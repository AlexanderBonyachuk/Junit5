package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    static Dog dog;

    @BeforeAll
    static void prepareData() {
        dog = new Dog("Albert", 2);
    }

    @org.junit.jupiter.api.Test
    void testGetDogName() {
        assertEquals("Albert", dog.getName());
    }

    @org.junit.jupiter.api.Test
    void testSetDogName() {
        dog.setName("Roman");
        assertEquals("Albert", dog.getName());
    }

    @org.junit.jupiter.api.Test
    void testSetDogNameIfEmpty() {
        Dog dog = new Dog("", 2);
        dog.setName("Roman");
        assertEquals("Roman", dog.getName());
    }

    @org.junit.jupiter.api.Test
    void getAge() {
    }

    @org.junit.jupiter.api.Test
    void setAge() {
    }
}