package com.laamella.mpu6502.assembler;

import java.util.function.Consumer;

public final class Org extends Line {
    Org(int address) {
        this.address = address;
    }

    @Override
    public int byteSize() {
        return 0;
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
    }

    @Override
    public String toString() {
        return "\t*=" + address;
    }
}
