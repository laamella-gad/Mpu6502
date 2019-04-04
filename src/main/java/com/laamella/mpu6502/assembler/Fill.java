package com.laamella.mpu6502.assembler;

import java.util.function.Consumer;

public class Fill extends Assemblable {
    private int amountOfBytes;

    public Fill(int amountOfBytes) {
        this.amountOfBytes = amountOfBytes;
    }

    @Override
    public int byteSize() {
        return amountOfBytes;
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
        for (int i = 0; i < amountOfBytes; i++) {
            data.accept(0);
        }
    }

    @Override
    public String toString() {
        return getAddress().map(Object::toString).orElse("?") + "\t.fill " + amountOfBytes;

    }
}
