package com.laamella.mpu6502.assembler;

import java.util.function.Consumer;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class Words extends Assemblable {
    private final int[] words;

    Words(int... words) {
        this.words = words;
    }

    @Override
    public int byteSize() {
        return words.length * 2;
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
        for (int w : words) {
            data.accept(lo(w));
            data.accept(hi(w));
        }
    }

    public static int hi(int word) {
        return (word >> 8) & 0xff;
    }

    public static int lo(int word) {
        return word & 0xff;
    }

    @Override
    public String toString() {
        return getAddress().map(Object::toString).orElse("?") + "\t.word " + stream(words).mapToObj(b -> "" + b).collect(joining(", "));
    }
}
