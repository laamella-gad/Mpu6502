package com.laamella.mpu6502;

import java.util.List;

public class Segment {
    public final int loadAddress;
    public final int[] data;

    public Segment(int loadAddress, int[] data) {
        this.loadAddress = loadAddress;
        this.data = data;
    }

    public Segment(int loadAddress, List<Integer> data) {
        this(loadAddress, data.stream().mapToInt(i -> i).toArray());
    }

    public byte[] getDataAsByteArray() {
        byte[] bytes = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            bytes[i] = (byte) data[i];
        }
        return bytes;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("$%04X ", loadAddress));
        for (int b : data) {
            out.append(String.format("%02X ", b));
        }
        return out.toString();
    }
}
