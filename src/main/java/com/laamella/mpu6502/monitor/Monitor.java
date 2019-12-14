package com.laamella.mpu6502.monitor;

import com.laamella.mpu6502.Mpu6502;
import com.laamella.mpu6502.disassembler.Disassembler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.laamella.mpu6502.Mpu6502Specifications.*;
import static com.laamella.mpu6502.RunState.RUNNING;
import static java.lang.String.format;

public class Monitor {
    public final Mpu6502 mpu;
    private final List<Predicate<Mpu6502>> breakpoints = new ArrayList<>();
    private final Disassembler disassembler = new Disassembler();

    public Monitor(Mpu6502 mpu) {
        this.mpu = mpu;
    }

    private boolean isSet(int val, int mask) {
        return (val & mask) > 0;
    }

    public void run() {
        while (true) {
            if (mpu.step() != RUNNING) {
                return;
            }
            for (Predicate<Mpu6502> breakpoint : breakpoints) {
                if (breakpoint.test(mpu)) {
                    return;
                }
            }
        }
    }

    public Predicate<Mpu6502> addBreakpoint(Predicate<Mpu6502> breakpoint) {
        breakpoints.add(breakpoint);
        return breakpoint;
    }

    public void removeBreakpoint(Predicate<Mpu6502> breakpoint) {
        breakpoints.remove(breakpoint);
    }

    public Predicate<Mpu6502> addProgramCounterBreakpoint(int breakAddress) {
        return addBreakpoint(mpu -> mpu.pc == breakAddress);
    }

    public Predicate<Mpu6502> addMemoryValueBreakpoint(int address, int breakValue) {
        return addBreakpoint(mpu -> mpu.bus.peek(address) == breakValue);
    }

    @Override
    public String toString() {
        return format("%04X %-12s %-14s - A:%02X X:%02X Y:%02X SP:%02X %s    %d",
                mpu.pc,
                disassembler.disassembleBytes(
                        mpu.bus.peek(mpu.pc),
                        mpu.bus.peek(mpu.pc + 1),
                        mpu.bus.peek(mpu.pc + 2)
                ),
                disassembler.disassembleAsm(
                        mpu.bus.peek(mpu.pc),
                        mpu.bus.peek(mpu.pc + 1),
                        mpu.bus.peek(mpu.pc + 2),
                        mpu.pc),
                mpu.a,
                mpu.x,
                mpu.y,
                mpu.sp,
                getFlagsString(),
                mpu.cycles);
    }

    private String getFlagsString() {
        return (isSet(mpu.flags, FLAG_MASK.NEGATIVE) ? "N" : ".") +
                (isSet(mpu.flags, FLAG_MASK.OVERFLOW) ? "V" : ".") +
                (isSet(mpu.flags, 0x20) ? "1" : ".") +
                (isSet(mpu.flags, FLAG_MASK.BREAK) ? "B" : ".") +
                (isSet(mpu.flags, FLAG_MASK.DECIMAL) ? "D" : ".") +
                (isSet(mpu.flags, FLAG_MASK.INTERRUPT) ? "I" : ".") +
                (isSet(mpu.flags, FLAG_MASK.ZERO) ? "Z" : ".") +
                (isSet(mpu.flags, FLAG_MASK.CARRY) ? "C" : ".");
    }

}
