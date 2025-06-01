package com.laamella.mpu6502;

public class ByteManipulation {
    public static int toSignedByte(int v) {
        return v & 0xff;
    }

    public static int word(int lo, int hi) {
        return ( toSignedByte(hi) << 8)+toSignedByte(lo);
    }
}
