package com.laamella.mpu6502.assembler;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.laamella.mpu6502.Mpu6502Specifications.ADDRESSING_MODE;
import static com.laamella.mpu6502.Mpu6502Specifications.OPCODE_NAME;

public final class Instruction extends Line {
    private final int opcode;
    private final Supplier<Optional<Integer>> operand;

    Instruction(int opcode, Supplier<Optional<Integer>> operand) {
        this.opcode = opcode;
        this.operand = operand;
    }

    @Override
    public int byteSize() {
        return ADDRESSING_MODE[opcode].totalBytes;
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
        data.accept(opcode);
        if (byteSize() > 1) {
            int oper = operand.get().get();
            data.accept(oper & 0xFF);
            if (byteSize() > 2) {
                data.accept((oper >> 8) & 0xFF);
            }
        }
    }

    @Override
    public String toString() {
        if (byteSize() == 1) {
            return getAddress().orElse(0) + "\t" + OPCODE_NAME[opcode];

        }
        return getAddress().orElse(0) + "\t" + OPCODE_NAME[opcode] + " " + operand.get().map(Object::toString).orElse("?");
    }
}
