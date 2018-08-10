package com.laamella.mpu6502;

/**
 * The processor's interface to the outside world.
 * Read/write are actual emulated bus operations.
 * Peek is meant for tooling.
 */
public interface Bus {
    int read(int address);

    void write(int address, int value);

    int peek(int address);

    default void write(int address, byte value) {
        write(address, value & 0xFF);
    }

    default int readWord(int address) {
        return read(address) | read(address + 1) << 8;
    }

    default void writeWord(int address, int data) {
        write(address, data & 0xff);
        write(address, (data >> 8) & 0xff);
    }

    default int peekWord(int address) {
        return peek(address) | peek(address + 1) << 8;
    }
}
