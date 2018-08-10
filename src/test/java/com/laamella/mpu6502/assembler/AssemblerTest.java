package com.laamella.mpu6502.assembler;

import com.laamella.mpu6502.Mpu6502;
import com.laamella.mpu6502.OnlyMemoryBus;
import com.laamella.mpu6502.RunState;
import com.laamella.mpu6502.Segment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.laamella.mpu6502.Mpu6502Specifications.OPCODE.LDA_IMM;
import static com.laamella.mpu6502.Mpu6502Specifications.OPCODE.STA_ABS;
import static com.laamella.mpu6502.assembler.Line.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblerTest {
    private final Assembler assembler = new Assembler();

    @Test
    public void test() {
        Line testByte = bytes(0);
        assembler.add(
                org(0x1000),
                line(LDA_IMM, 15),
                line(STA_ABS, testByte),
                line(2),
                testByte
        );
        List<Segment> segments = assembler.assemble();

        OnlyMemoryBus bus = new OnlyMemoryBus();
        bus.load(segments.get(0));

        Mpu6502 mpu6502 = new Mpu6502(bus);
        mpu6502.pc = segments.get(0).loadAddress;
        while (mpu6502.step() == RunState.RUNNING)
            ;
        assertEquals(15, bus.peek(testByte.address));
    }
}
