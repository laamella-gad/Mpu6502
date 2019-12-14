package com.laamella.mpu6502;

import java.util.HashMap;
import java.util.Map;

public class Effects {

    public enum Effect {
        CLEAR, SET, MODIFY, NONE
    }

    public final Effect memory;
    public final Map<Mpu6502Specifications.Register, Effect> registers = new HashMap<>();
    public final Map<Mpu6502Specifications.Flag, Effect> flags = new HashMap<>();

    public Effects(Effect memory, Effect accumulator, Effect x, Effect y, Effect n, Effect o, Effect b, Effect d, Effect i, Effect z, Effect c) {
        this.memory = memory;
        registers.put(Mpu6502Specifications.Register.A, accumulator);
        registers.put(Mpu6502Specifications.Register.X, x);
        registers.put(Mpu6502Specifications.Register.Y, y);
        flags.put(Mpu6502Specifications.Flag.NEGATIVE, n);
        flags.put(Mpu6502Specifications.Flag.OVERFLOW, o);
        flags.put(Mpu6502Specifications.Flag.BREAK, b);
        flags.put(Mpu6502Specifications.Flag.DECIMAL, d);
        flags.put(Mpu6502Specifications.Flag.INTERRUPT, i);
        flags.put(Mpu6502Specifications.Flag.ZERO, z);
        flags.put(Mpu6502Specifications.Flag.CARRY, c);
    }

}
