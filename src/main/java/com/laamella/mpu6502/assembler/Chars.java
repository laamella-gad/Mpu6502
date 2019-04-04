package com.laamella.mpu6502.assembler;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Chars extends Assemblable {
    private final char[] chars;

    Chars(char... chars) {
        this.chars = chars;
    }

    @Override
    public int byteSize() {
        return chars.length;
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
        for (int b : chars) {
            data.accept(b);
        }
    }

    @Override
    public String toString() {
        return getAddress().map(Object::toString).orElse("?") + "\t.char " + CharBuffer.wrap(chars).chars().mapToObj(b -> "'" + (char)b + "'").collect(joining(", "));
    }
}
