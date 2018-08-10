package com.laamella.mpu6502.assembler;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Bytes extends Line {
    private final int[] bytes;

    Bytes(int... bytes) {
        this.bytes = bytes;
    }

    @Override
    public int byteSize() {
        return bytes.length;
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
        for (int b : bytes) {
            data.accept(b);
        }
    }

    @Override
    public String toString() {
        return getAddress().map(Object::toString).orElse("?") + "\t.byte " + Arrays.stream(bytes).mapToObj(b -> "" + b).collect(Collectors.joining());
    }
}
