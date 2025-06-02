package com.laamella.mpu6502;

public class ByteManipulation {
    public static int toByte(int v) {
        return v & 0xff;
    }

    public static int toSignedByte(int v) {
        return (byte) v;
    }

    public static int word(int lo, int hi) {
        return (toByte(hi) << 8) + toByte(lo);
    }
}
