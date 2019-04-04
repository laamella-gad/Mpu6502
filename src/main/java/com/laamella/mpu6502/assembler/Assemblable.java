package com.laamella.mpu6502.assembler;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Assemblable {
    Integer address = null;

    public Optional<Integer> getAddress() {
        return Optional.ofNullable(address);
    }

    public abstract int byteSize();

    public abstract void assembleTo(Consumer<Integer> data);

    public static Label label() {
        return new Label();
    }

    public static Org org(int address) {
        return new Org(address);
    }

    public static Assemblable instruction(int opcode, Supplier<Optional<Integer>> operand) {
        return new Instruction(opcode, operand);
    }

    public static Assemblable instruction(int opcode, int operand) {
        return new Instruction(opcode, () -> Optional.of(operand));
    }

    public static Assemblable instruction(int opcode, Assemblable assemblable) {
        return new Instruction(opcode, assemblable::getAddress);
    }

    public static Assemblable instruction(int opcode) {
        return new Instruction(opcode, Optional::empty);
    }

    public static Bytes bytes(int... bytes) {
        return new Bytes(bytes);
    }

    public static Chars chars(char... chars) {
        return new Chars(chars);
    }

    public static Chars chars(String chars) {
        return new Chars(chars.toCharArray());
    }

    public static Words words(int... words) {
        return new Words(words);
    }

    public static Assemblables assemblables(Assemblable... assemblables) {
        return new Assemblables(assemblables);
    }
    
    public static Fill fill(int amountOfBytes){
        return new Fill(amountOfBytes);
    }

}
