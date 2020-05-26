package com.laamella.mpu6502.specification;


public enum Flag {
    NEGATIVE(FlagMask.NEGATIVE),
    OVERFLOW(FlagMask.OVERFLOW),
    BREAK(FlagMask.BREAK),
    DECIMAL(FlagMask.DECIMAL),
    INTERRUPT(FlagMask.INTERRUPT),
    ZERO(FlagMask.ZERO),
    CARRY(FlagMask.CARRY);

    public final int mask;

    Flag(int mask) {
        this.mask = mask;
    }
}

