package com.laamella.mpu6502;

import org.junit.jupiter.api.Test;

import static com.laamella.mpu6502.ByteManipulation.toSignedByte;
import static org.junit.jupiter.api.Assertions.*;

class ByteManipulationTest {
    @Test
    void toSignedBytePositive() {
        assertEquals(10, toSignedByte(10));
    }

    @Test
    void toSignedByteNegative() {
        assertEquals(-10, toSignedByte(-10));
        assertEquals(-1, toSignedByte(0xffff));
    }
}