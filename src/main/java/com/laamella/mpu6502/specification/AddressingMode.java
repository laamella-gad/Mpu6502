package com.laamella.mpu6502.specification;

public enum AddressingMode {
    NONE(1),
    /**
     * (n, x)
     */
    IND_X(2),
    /**
     * (n), y
     */
    IND_Y(2),
    /**
     * this instruction will jam the CPU
     */
    JAM(1),
    /**
     * n
     */
    ZERO_PAGE(2),
    /**
     * n, x
     */
    ZERO_PAGE_X(2),
    /**
     * n, y
     */
    ZERO_PAGE_Y(2),
    /**
     * #n
     */
    IMMEDIATE(2),
    /**
     * nn
     */
    ABSOLUTE(3),
    /**
     * nn, x
     */
    ABSOLUTE_X(3),
    /**
     * nn, y
     */
    ABSOLUTE_Y(3),
    /**
     * n (for relative jumps)
     */
    RELATIVE(2),
    /**
     * (nn)
     */
    INDIRECT(3),
    /**
     * Data is in the accumulator.
     */
    ACCUMULATOR(1);

    public int totalBytes;

    AddressingMode(int totalBytes) {
        this.totalBytes = totalBytes;
    }
}

