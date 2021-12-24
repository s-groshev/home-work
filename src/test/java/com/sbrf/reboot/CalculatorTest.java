package com.sbrf.reboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private  final Calculator calc=new Calculator();

    @Test
    void add() {
        assertEquals(4,calc.add(2,2));
    }

    @Test
    void sub() {
        assertEquals(0,calc.sub(2,2));
    }

    @Test
    void mul() {
        assertEquals(4,calc.mul(2,2));
    }

    @Test
    void div() {
        assertEquals(1,calc.div(2,2));
    }

    @Test
    void abs() {
        assertEquals(2,calc.abs(2));
    }

    @Test
    void pow() {
        assertEquals(4,calc.pow(2,2));
    }
}