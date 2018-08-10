package com.laamella.mpu6502;

/**
 * This bus is connected to RAM, nothing else.
 */
public class OnlyMemoryBus implements Bus {
    private final int[] memory;

    public OnlyMemoryBus(int[] memory) {
        this.memory = memory;
    }

    public OnlyMemoryBus(byte[] memory) {
        this.memory = new int[memory.length];
        for (int i = 0; i < memory.length; i++) {
            this.memory[i] = memory[i] & 0xff;
        }
    }

    public OnlyMemoryBus() {
        memory = new int[0x10000];
    }

    @Override
    public int read(int address) {
        return memory[address];
    }

    @Override
    public void write(int address, int data) {
        if (data > 0xff || data < 0) {
            throw new IllegalStateException();
        }
        memory[address] = data;
    }

    @Override
    public int peek(int address) {
        return memory[address];
    }

    public void load(Segment segment) {
        System.arraycopy(segment.data, 0, memory, segment.loadAddress, segment.data.length);
    }

}
