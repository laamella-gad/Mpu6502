package com.laamella.mpu6502.assembler;

import com.laamella.mpu6502.Mpu6502;
import com.laamella.mpu6502.OnlyMemoryBus;
import com.laamella.mpu6502.RunState;
import com.laamella.mpu6502.Segment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.laamella.mpu6502.Mpu6502Specifications.OPCODE_NR.LDA_IMM;
import static com.laamella.mpu6502.Mpu6502Specifications.OPCODE_NR.STA_ABS;
import static com.laamella.mpu6502.assembler.Assemblable.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblerTest {
    private final Assembler assembler = new Assembler();

    @Test
    public void test() {
        Assemblable testByte = bytes(0);
        assembler.add(
                org(0x1000),
                instruction(LDA_IMM, 15),
                instruction(STA_ABS, testByte),
                instruction(2),
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
