package com.laamella.mpu6502.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Assemblables extends Assemblable {
    private final List<Assemblable> assemblables = new ArrayList<>();

    Assemblables(Assemblable... assemblables) {
        add(assemblables);
    }

    public void add(Assemblable... assemblables) {
        this.assemblables.addAll(asList(assemblables));
    }

    @Override
    public int byteSize() {
        return assemblables.stream().mapToInt(Assemblable::byteSize).sum();
    }

    @Override
    public void assembleTo(Consumer<Integer> data) {
        assemblables.forEach(a -> a.assembleTo(data));
    }

    @Override
    public String toString() {
        return assemblables.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}

