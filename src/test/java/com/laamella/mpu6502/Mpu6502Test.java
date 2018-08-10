package com.laamella.mpu6502;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.laamella.mpu6502.RunState.RUNNING;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled
public class Mpu6502Test {

    /**
     * Runs the test suite found here: https://github.com/Klaus2m5/6502_65C02_functional_tests
     */
    @Test
    public void testSuite() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("6502_functional_test.bin").getFile());
        byte[] bytes = Files.readAllBytes(file.toPath());
        OnlyMemoryBus bus = new OnlyMemoryBus(bytes);
        Mpu6502 mpu = new Mpu6502(bus);
        mpu.pc = 0x0400;

        RunState running;
        do {
            final int oldPc = mpu.pc;
            running = mpu.step();
            if (mpu.pc == 0x3469) {
                return;
            }
            if (mpu.pc == oldPc) {
                fail(String.format("at %04x after %d cycles.", mpu.pc, mpu.cycles));
            }
        } while (running == RUNNING);
    }
}