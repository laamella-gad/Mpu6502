package com.laamella.mpu6502.assembler;

import com.laamella.mpu6502.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Assembler {
    private final List<Assemblable> assemblables = new ArrayList<>();

    private void resolveAddresses() {
        Integer pc = null;
        for (Assemblable assemblable : assemblables) {
            if (assemblable instanceof Org) {
                pc = assemblable.address;
            } else if (pc == null) {
                throw new IllegalStateException("Cannot assemble without an org(...).");
            } else {
                assemblable.address = pc;
                pc += assemblable.byteSize();
            }
        }
    }

    public void add(Assemblable... assemblables) {
        this.assemblables.addAll(asList(assemblables));
    }

    public void add(Assemblable assemblable) {
        assemblables.add(assemblable);
    }

    public List<Segment> assemble() {
        resolveAddresses();
        return generateMachineCode();
    }

    private List<Segment> generateMachineCode() {
        List<Segment> segments = new ArrayList<>();
        int loadAddress = 0;
        List<Integer> data = null;
        for (Assemblable assemblable : assemblables) {
            if (assemblable instanceof Org) {
                if (data != null) {
                    segments.add(new Segment(loadAddress, data));
                }
                loadAddress = assemblable.address;
                data = new ArrayList<>();
            }
            assemblable.assembleTo(data::add);
        }
        if (data != null) {
            segments.add(new Segment(loadAddress, data));
        }
        return segments;
    }


    @Override
    public String toString() {
        return assemblables.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
