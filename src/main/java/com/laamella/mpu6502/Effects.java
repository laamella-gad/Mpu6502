package com.laamella.mpu6502;

import com.laamella.mpu6502.specification.Flag;
import com.laamella.mpu6502.specification.Register;

import java.util.HashMap;
import java.util.Map;

public class Effects {

    public enum Effect {
        CLEAR, SET, MODIFY, NONE
    }

    public final Effect memory;
    public final Map<Register, Effect> registers = new HashMap<>();
    public final Map<Flag, Effect> flags = new HashMap<>();

    public Effects(Effect memory, Effect accumulator, Effect x, Effect y, Effect n, Effect o, Effect b, Effect d, Effect i, Effect z, Effect c) {
        this.memory = memory;
        registers.put(Register.A, accumulator);
        registers.put(Register.X, x);
        registers.put(Register.Y, y);
        flags.put(Flag.NEGATIVE, n);
        flags.put(Flag.OVERFLOW, o);
        flags.put(Flag.BREAK, b);
        flags.put(Flag.DECIMAL, d);
        flags.put(Flag.INTERRUPT, i);
        flags.put(Flag.ZERO, z);
        flags.put(Flag.CARRY, c);
    }

}
