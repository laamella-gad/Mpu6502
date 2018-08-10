package com.laamella.mpu6502.disassembler;

import static com.laamella.mpu6502.Mpu6502Specifications.*;
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
            case IZX:
                operand = format("($%02X, X)", byte1);
                break;
            case IZY:
                operand = format("($%02X), Y", byte1);
                break;
            case REL:
                operand = format("$%04X", addr + toSignedByte(byte1) + 2);
                break;
            case IND:
                operand = format("($%02X%02X)", byte2, byte1);
                break;
            case ABS:
                operand = format("$%02X%02X", byte2, byte1);
                break;
            case ABX:
                operand = format("$%02X%02X, X", byte2, byte1);
                break;
            case ABY:
                operand = format("$%02X%02X, Y", byte2, byte1);
                break;
            case IMM:
                operand = format("#%02X", byte1);
                break;
            case ZP:
                operand = format("$%02X", byte1);
                break;
            case ZPX:
                operand = format("$%02X, X", byte1);
                break;
            case ZPY:
                operand = format("$%02X, Y", byte1);
                break;
            case JAM:
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
