package com.laamella.mpu6502.assembler;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Line {
    Integer address = null;

    public Optional<Integer> getAddress() {
        return Optional.ofNullable(address);
    }

    public abstract int byteSize();

    public abstract void assembleTo(Consumer<Integer> data);

    public static Line label() {
        return new Label();
    }

    public static Line org(int address) {
        return new Org(address);
    }

    public static Line line(int opcode, Supplier<Optional<Integer>> operand) {
        return new Instruction(opcode, operand);
    }

    public static Line line(int opcode, int operand) {
        return new Instruction(opcode, () -> Optional.of(operand));
    }

    public static Line line(int opcode, Line line) {
        return new Instruction(opcode, line::getAddress);
    }

    public static Line line(int opcode) {
        return new Instruction(opcode, Optional::empty);
    }

    public static Line bytes(int... bytes) {
        return new Bytes(bytes);
    }
}
