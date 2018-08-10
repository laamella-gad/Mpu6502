package com.laamella.mpu6502.assembler;

import com.laamella.mpu6502.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Assembler {
    private final List<Line> lines = new ArrayList<>();

    private void resolveAddresses() {
        Integer pc = null;
        for (Line line : lines) {
            if (line instanceof Org) {
                pc = line.address;
            } else if (pc == null) {
                throw new IllegalStateException("Cannot assemble without an org(...).");
            } else {
                line.address = pc;
                pc += line.byteSize();
            }
        }
    }

    public void add(Line... lines) {
        this.lines.addAll(asList(lines));
    }

    public void add(Line line) {
        lines.add(line);
    }

    public List<Segment> assemble() {
        resolveAddresses();
        return generateMachineCode();
    }

    private List<Segment> generateMachineCode() {
        List<Segment> segments = new ArrayList<>();
        int loadAddress = 0;
        List<Integer> data = null;
        for (Line line : lines) {
            if (line instanceof Org) {
                if (data != null) {
                    segments.add(new Segment(loadAddress, data));
                }
                loadAddress = line.address;
                data = new ArrayList<>();
            }
            line.assembleTo(data::add);
        }
        if (data != null) {
            segments.add(new Segment(loadAddress, data));
        }
        return segments;
    }


    @Override
    public String toString() {
        return lines.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
