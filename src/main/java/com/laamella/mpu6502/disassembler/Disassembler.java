package com.laamella.mpu6502.disassembler;

import com.laamella.mpu6502.specification.AddressingMode;

import static com.laamella.mpu6502.specification.Opcode.ADDRESSING_MODE;
import static com.laamella.mpu6502.specification.Opcode.OPCODE_NAME;
import static java.lang.String.format;

public class Disassembler {
    private int toSignedByte(int data) {
        if ((data & 0x80) > 0) {
            return (data & 0x7f) - 0x80;
        }
        return data & 0x7f;
    }

    public String disassembleAsm(int opcode, int byte1, int byte2, int addr) {
        String name = OPCODE_NAME[opcode];
        AddressingMode addressingMode = ADDRESSING_MODE[opcode];
        String operand;
        switch (addressingMode) {
            case IND_X:
                operand = format("($%02X, X)", byte1);
                break;
            case IND_Y:
                operand = format("($%02X), Y", byte1);
                break;
            case RELATIVE:
                operand = format("$%04X", addr + toSignedByte(byte1) + 2);
                break;
            case INDIRECT:
                operand = format("($%02X%02X)", byte2, byte1);
                break;
            case ABSOLUTE:
                operand = format("$%02X%02X", byte2, byte1);
                break;
            case ABSOLUTE_X:
                operand = format("$%02X%02X, X", byte2, byte1);
                break;
            case ABSOLUTE_Y:
                operand = format("$%02X%02X, Y", byte2, byte1);
                break;
            case IMMEDIATE:
                operand = format("#%02X", byte1);
                break;
            case ZERO_PAGE:
                operand = format("$%02X", byte1);
                break;
            case ZERO_PAGE_X:
                operand = format("$%02X, X", byte1);
                break;
            case ZERO_PAGE_Y:
                operand = format("$%02X, Y", byte1);
                break;
            case JAM:
            case ACCUMULATOR:
            case NONE:
                operand = "";
                break;
            default:
                throw new IllegalStateException();
        }
        return name + " " + operand;
    }


    public String disassembleBytes(int opcode, int byte1, int byte2) {
        switch (ADDRESSING_MODE[opcode].totalBytes) {
            case 1:
                return format("%02X", opcode);
            case 2:
                return format("%02X %02X", opcode, byte1);
            case 3:
                return format("%02X %02X %02X", opcode, byte1, byte2);
        }
        throw new IllegalStateException();
    }

}
