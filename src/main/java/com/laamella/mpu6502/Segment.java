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
