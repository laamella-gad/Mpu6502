package com.laamella.mpu6502;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.laamella.mpu6502.Mpu6502Specifications.AddressingMode.*;

/**
 * A big collection of data on the processor's instructions.
 */
public final class Mpu6502Specifications {
    public static final class OPCODE {
        public static final int BRK = 0x00;
        public static final int ORA_IZX = 0x01;
        public static final int SLO_IZX_03 = 0x03;
        public static final int NOP_ZP_04 = 0x04;
        public static final int ORA_ZP = 0x05;
        public static final int ASL_ZP = 0x06;
        public static final int SLO_ZP_07 = 0x07;
        public static final int PHP = 0x08;
        public static final int ORA_IMM = 0x09;
        public static final int ASL = 0x0A;
        public static final int ANC_IMM_0B = 0x0B;
        public static final int NOP_ABS_0C = 0x0C;
        public static final int ORA_ABS = 0x0D;
        public static final int ASL_ABS = 0x0E;
        public static final int SLO_ABS_0F = 0x0F;
        public static final int BPL = 0x10;
        public static final int ORA_IZY = 0x11;
        public static final int SLO_IZY_13 = 0x13;
        public static final int NOP_ZPX_14 = 0x14;
        public static final int ORA_ZPX = 0x15;
        public static final int ASL_ZPX = 0x16;
        public static final int SLO_ZPX_17 = 0x17;
        public static final int CLC = 0x18;
        public static final int ORA_ABY = 0x19;
        public static final int NOP_1A = 0x1A;
        public static final int SLO_ABY_1B = 0x1B;
        public static final int NOP_ABX_1C = 0x1C;
        public static final int ORA_ABX = 0x1D;
        public static final int ASL_ABX = 0x1E;
        public static final int SLO_ABX_1F = 0x1F;
        public static final int JSR_ABS = 0x20;
        public static final int AND_IZX = 0x21;
        public static final int RLA_IZX_23 = 0x23;
        public static final int BIT_ZP = 0x24;
        public static final int AND_ZP = 0x25;
        public static final int ROL_ZP = 0x26;
        public static final int RLA_ZP_27 = 0x27;
        public static final int PLP = 0x28;
        public static final int AND_IMM = 0x29;
        public static final int ROL = 0x2A;
        public static final int ANC_IMM_2B = 0x2B;
        public static final int BIT_ABS = 0x2C;
        public static final int AND_ABS = 0x2D;
        public static final int ROL_ABS = 0x2E;
        public static final int RLA_ABS_2F = 0x2F;
        public static final int BMI = 0x30;
        public static final int AND_IZY = 0x31;
        public static final int RLA_IZY_33 = 0x33;
        public static final int NOP_ZPX_34 = 0x34;
        public static final int AND_ZPX = 0x35;
        public static final int ROL_ZPX = 0x36;
        public static final int RLA_ZPX_37 = 0x37;
        public static final int SEC = 0x38;
        public static final int AND_ABY = 0x39;
        public static final int NOP_3A = 0x3A;
        public static final int RLA_ABY_3B = 0x3B;
        public static final int NOP_ABX_3C = 0x3C;
        public static final int AND_ABX = 0x3D;
        public static final int ROL_ABX = 0x3E;
        public static final int RLA_ABX_3F = 0x3F;
        public static final int RTI = 0x40;
        public static final int EOR_IZX = 0x41;
        public static final int SRE_IZX_43 = 0x43;
        public static final int NOP_ZP_44 = 0x44;
        public static final int EOR_ZP = 0x45;
        public static final int LSR_ZP = 0x46;
        public static final int SRE_ZP_47 = 0x47;
        public static final int PHA = 0x48;
        public static final int EOR_IMM = 0x49;
        public static final int LSR = 0x4A;
        public static final int ALR_IMM_4B = 0x4B;
        public static final int JMP_ABS = 0x4C;
        public static final int EOR_ABS = 0x4D;
        public static final int LSR_ABS = 0x4E;
        public static final int SRE_ABS_4F = 0x4F;
        public static final int BVC = 0x50;
        public static final int EOR_IZY = 0x51;
        public static final int SRE_IZY_53 = 0x53;
        public static final int NOP_ZPX_54 = 0x54;
        public static final int EOR_ZPX = 0x55;
        public static final int LSR_ZPX = 0x56;
        public static final int SRE_ZPX_57 = 0x57;
        public static final int CLI = 0x58;
        public static final int EOR_ABY = 0x59;
        public static final int NOP_5A = 0x5A;
        public static final int SRE_ABY_5B = 0x5B;
        public static final int NOP_ABX_5C = 0x5C;
        public static final int EOR_ABX = 0x5D;
        public static final int LSR_ABX = 0x5E;
        public static final int SRE_ABX_5F = 0x5F;
        public static final int RTS = 0x60;
        public static final int ADC_IZX = 0x61;
        public static final int RRA_IZX_63 = 0x63;
        public static final int NOP_ZP_64 = 0x64;
        public static final int ADC_ZP = 0x65;
        public static final int ROR_ZP = 0x66;
        public static final int RRA_ZP_67 = 0x67;
        public static final int PLA = 0x68;
        public static final int ADC_IMM = 0x69;
        public static final int ROR = 0x6A;
        public static final int ARR_IMM_6B = 0x6B;
        public static final int JMP_IND = 0x6C;
        public static final int ADC_ABS = 0x6D;
        public static final int ROR_ABS = 0x6E;
        public static final int RRA_ABS_6F = 0x6F;
        public static final int BVS = 0x70;
        public static final int ADC_IZY = 0x71;
        public static final int RRA_IZY_73 = 0x73;
        public static final int NOP_ZPX_74 = 0x74;
        public static final int ADC_ZPX = 0x75;
        public static final int ROR_ZPX = 0x76;
        public static final int RRA_ZPX_77 = 0x77;
        public static final int SEI = 0x78;
        public static final int ADC_ABY = 0x79;
        public static final int NOP_7A = 0x7A;
        public static final int RRA_ABY_7B = 0x7B;
        public static final int NOP_ABX_7C = 0x7C;
        public static final int ADC_ABX = 0x7D;
        public static final int ROR_ABX = 0x7E;
        public static final int RRA_ABX_7F = 0x7F;
        public static final int NOP_IMM_80 = 0x80;
        public static final int STA_IZX = 0x81;
        public static final int NOP_IMM_82 = 0x82;
        public static final int SAX_IZX_83 = 0x83;
        public static final int STY_ZP = 0x84;
        public static final int STA_ZP = 0x85;
        public static final int STX_ZP = 0x86;
        public static final int SAX_ZP_87 = 0x87;
        public static final int DEY = 0x88;
        public static final int NOP_IMM_89 = 0x89;
        public static final int TXA = 0x8A;
        public static final int XAA_IMM_8B = 0x8B;
        public static final int STY_ABS = 0x8C;
        public static final int STA_ABS = 0x8D;
        public static final int STX_ABS = 0x8E;
        public static final int SAX_ABS_8F = 0x8F;
        public static final int BCC = 0x90;
        public static final int STA_IZY = 0x91;
        public static final int AHX_IZY_93 = 0x93;
        public static final int STY_ZPX = 0x94;
        public static final int STA_ZPX = 0x95;
        public static final int STX_ZPY = 0x96;
        public static final int SAX_ZPY_97 = 0x97;
        public static final int TYA = 0x98;
        public static final int STA_ABY = 0x99;
        public static final int TXS = 0x9A;
        public static final int TAS_ABY_9B = 0x9B;
        public static final int SHY_ABX_9C = 0x9C;
        public static final int STA_ABX = 0x9D;
        public static final int SHX_ABY_9E = 0x9E;
        public static final int AHX_ABY_9F = 0x9F;
        public static final int LDY_IMM = 0xA0;
        public static final int LDA_IZX = 0xA1;
        public static final int LDX_IMM = 0xA2;
        public static final int LAX_IZX_A3 = 0xA3;
        public static final int LDY_ZP = 0xA4;
        public static final int LDA_ZP = 0xA5;
        public static final int LDX_ZP = 0xA6;
        public static final int LAX_ZP_A7 = 0xA7;
        public static final int TAY = 0xA8;
        public static final int LDA_IMM = 0xA9;
        public static final int TAX = 0xAA;
        public static final int LAX_IMM_AB = 0xAB;
        public static final int LDY_ABS = 0xAC;
        public static final int LDA_ABS = 0xAD;
        public static final int LDX_ABS = 0xAE;
        public static final int LAX_ABS_AF = 0xAF;
        public static final int BCS = 0xB0;
        public static final int LDA_IZY = 0xB1;
        public static final int LAX_IZY_B3 = 0xB3;
        public static final int LDY_ZPX = 0xB4;
        public static final int LDA_ZPX = 0xB5;
        public static final int LDX_ZPY = 0xB6;
        public static final int LAX_ZPY_B7 = 0xB7;
        public static final int CLV = 0xB8;
        public static final int LDA_ABY = 0xB9;
        public static final int TSX = 0xBA;
        public static final int LAS_ABY_BB = 0xBB;
        public static final int LDY_ABX = 0xBC;
        public static final int LDA_ABX = 0xBD;
        public static final int LDX_ABY = 0xBE;
        public static final int LAX_ABY_BF = 0xBF;
        public static final int CPY_IMM = 0xC0;
        public static final int CMP_IZX = 0xC1;
        public static final int NOP_IMM_C2 = 0xC2;
        public static final int DCP_IZX_C3 = 0xC3;
        public static final int CPY_ZP = 0xC4;
        public static final int CMP_ZP = 0xC5;
        public static final int DEC_ZP = 0xC6;
        public static final int DCP_ZP_C7 = 0xC7;
        public static final int INY = 0xC8;
        public static final int CMP_IMM = 0xC9;
        public static final int DEX = 0xCA;
        public static final int AXS_IMM_CB = 0xCB;
        public static final int CPY_ABS = 0xCC;
        public static final int CMP_ABS = 0xCD;
        public static final int DEC_ABS = 0xCE;
        public static final int DCP_ABS_CF = 0xCF;
        public static final int BNE = 0xD0;
        public static final int CMP_IZY = 0xD1;
        public static final int DCP_IZY_D3 = 0xD3;
        public static final int NOP_ZPX_D4 = 0xD4;
        public static final int CMP_ZPX = 0xD5;
        public static final int DEC_ZPX = 0xD6;
        public static final int DCP_ZPX_D7 = 0xD7;
        public static final int CLD = 0xD8;
        public static final int CMP_ABY = 0xD9;
        public static final int NOP_DA = 0xDA;
        public static final int DCP_ABY_DB = 0xDB;
        public static final int NOP_ABX_DC = 0xDC;
        public static final int CMP_ABX = 0xDD;
        public static final int DEC_ABX = 0xDE;
        public static final int DCP_ABX_DF = 0xDF;
        public static final int CPX_IMM = 0xE0;
        public static final int SBC_IZX = 0xE1;
        public static final int NOP_IMM_E2 = 0xE2;
        public static final int ISC_IZX_E3 = 0xE3;
        public static final int CPX_ZP = 0xE4;
        public static final int SBC_ZP = 0xE5;
        public static final int INC_ZP = 0xE6;
        public static final int ISC_ZP_E7 = 0xE7;
        public static final int INX = 0xE8;
        public static final int SBC_IMM = 0xE9;
        public static final int NOP = 0xEA;
        public static final int SBC_IMM_EB = 0xEB;
        public static final int CPX_ABS = 0xEC;
        public static final int SBC_ABS = 0xED;
        public static final int INC_ABS = 0xEE;
        public static final int ISC_ABS_EF = 0xEF;
        public static final int BEQ = 0xF0;
        public static final int SBC_IZY = 0xF1;
        public static final int ISC_IZY_F3 = 0xF3;
        public static final int NOP_ZPX_F4 = 0xF4;
        public static final int SBC_ZPX = 0xF5;
        public static final int INC_ZPX = 0xF6;
        public static final int ISC_ZPX_F7 = 0xF7;
        public static final int SED = 0xF8;
        public static final int SBC_ABY = 0xF9;
        public static final int NOP_FA = 0xFA;
        public static final int ISC_ABY_FB = 0xFB;
        public static final int NOP_ABX_FC = 0xFC;
        public static final int SBC_ABX = 0xFD;
        public static final int INC_ABX = 0xFE;
        public static final int ISC_ABX_FF = 0xFF;
    }

    public static final class FLAG {
        public static final int NEGATIVE = 0x80;
        public static final int OVERFLOW = 0x40;
        public static final int BREAK = 0x10;
        public static final int DECIMAL = 0x08;
        public static final int INTERRUPT = 0x04;
        public static final int ZERO = 0x02;
        public static final int CARRY = 0x01;
    }

    public static final int[] CPU_CYCLES = {
            7, 6, 0, 8, 3, 3, 5, 5, 3, 2, 2, 2, 4, 4, 6, 6,
            2, 5, 0, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7,
            6, 6, 0, 8, 3, 3, 5, 5, 4, 2, 2, 2, 4, 4, 6, 6,
            2, 5, 0, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7,
            6, 6, 0, 8, 3, 3, 5, 5, 3, 2, 2, 2, 3, 4, 6, 6,
            2, 5, 0, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7,
            6, 6, 0, 8, 3, 3, 5, 5, 4, 2, 2, 2, 5, 4, 6, 6,
            2, 5, 0, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7,
            2, 6, 2, 6, 3, 3, 3, 3, 2, 2, 2, 2, 4, 4, 4, 4,
            2, 6, 0, 6, 4, 4, 4, 4, 2, 5, 2, 5, 5, 5, 5, 5,
            2, 6, 2, 6, 3, 3, 3, 3, 2, 2, 2, 2, 4, 4, 4, 4,
            2, 5, 0, 5, 4, 4, 4, 4, 2, 4, 2, 4, 4, 4, 4, 4,
            2, 6, 2, 8, 3, 3, 5, 5, 2, 2, 2, 2, 4, 4, 6, 6,
            2, 5, 0, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7,
            2, 6, 2, 8, 3, 3, 5, 5, 2, 2, 2, 2, 4, 4, 6, 6,
            2, 5, 0, 8, 4, 4, 6, 6, 2, 4, 2, 7, 4, 4, 7, 7
    };

    public static final String[] OPCODE_NAME = {
            "BRK", "ORA", "JAM", "SLO", "NOP", "ORA", "ASL", "SLO", "PHP", "ORA", "ASL", "ANC", "NOP", "ORA", "ASL", "SLO",
            "BPL", "ORA", "JAM", "SLO", "NOP", "ORA", "ASL", "SLO", "CLC", "ORA", "NOP", "SLO", "NOP", "ORA", "ASL", "SLO",
            "JSR", "AND", "JAM", "RLA", "BIT", "AND", "ROL", "RLA", "PLP", "AND", "ROL", "ANC", "BIT", "AND", "ROL", "RLA",
            "BMI", "AND", "JAM", "RLA", "NOP", "AND", "ROL", "RLA", "SEC", "AND", "NOP", "RLA", "NOP", "AND", "ROL", "RLA",
            "RTI", "EOR", "JAM", "SRE", "NOP", "EOR", "LSR", "SRE", "PHA", "EOR", "LSR", "ALR", "JMP", "EOR", "LSR", "SRE",
            "BVC", "EOR", "JAM", "SRE", "NOP", "EOR", "LSR", "SRE", "CLI", "EOR", "NOP", "SRE", "NOP", "EOR", "LSR", "SRE",
            "RTS", "ADC", "JAM", "RRA", "NOP", "ADC", "ROR", "RRA", "PLA", "ADC", "ROR", "ARR", "JMP", "ADC", "ROR", "RRA",
            "BVS", "ADC", "JAM", "RRA", "NOP", "ADC", "ROR", "RRA", "SEI", "ADC", "NOP", "RRA", "NOP", "ADC", "ROR", "RRA",
            "NOP", "STA", "NOP", "SAX", "STY", "STA", "STX", "SAX", "DEY", "NOP", "TXA", "XAA", "STY", "STA", "STX", "SAX",
            "BCC", "STA", "JAM", "AHX", "STY", "STA", "STX", "SAX", "TYA", "STA", "TXS", "TAS", "SHY", "STA", "SHX", "AHX",
            "LDY", "LDA", "LDX", "LAX", "LDY", "LDA", "LDX", "LAX", "TAY", "LDA", "TAX", "LAX", "LDY", "LDA", "LDX", "LAX",
            "BCS", "LDA", "JAM", "LAX", "LDY", "LDA", "LDX", "LAX", "CLV", "LDA", "TSX", "LAS", "LDY", "LDA", "LDX", "LAX",
            "CPY", "CMP", "NOP", "DCP", "CPY", "CMP", "DEC", "DCP", "INY", "CMP", "DEX", "AXS", "CPY", "CMP", "DEC", "DCP",
            "BNE", "CMP", "JAM", "DCP", "NOP", "CMP", "DEC", "DCP", "CLD", "CMP", "NOP", "DCP", "NOP", "CMP", "DEC", "DCP",
            "CPX", "SBC", "NOP", "ISC", "CPX", "SBC", "INC", "ISC", "INX", "SBC", "NOP", "SBC", "CPX", "SBC", "INC", "ISC",
            "BEQ", "SBC", "JAM", "ISC", "NOP", "SBC", "INC", "ISC", "SED", "SBC", "NOP", "ISC", "NOP", "SBC", "INC", "ISC"
    };

    private static final boolean Y = true;
    private static final boolean N = false;

    public static final boolean[] ILLEGAL = {
            N, N, Y, Y, Y, N, N, Y, N, N, N, Y, Y, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, Y, Y, Y, N, N, Y,
            N, N, Y, Y, N, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, Y, Y, Y, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, Y, Y, Y, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, Y, Y, Y, N, N, Y,
            Y, N, Y, Y, N, N, N, Y, N, Y, N, Y, N, N, N, Y,
            N, N, Y, Y, N, N, N, Y, N, N, N, Y, Y, N, Y, Y,
            N, N, N, Y, N, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, N, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, N, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, Y, Y, Y, N, N, Y,
            N, N, Y, Y, N, N, N, Y, N, N, N, Y, N, N, N, Y,
            N, N, Y, Y, Y, N, N, Y, N, N, Y, Y, Y, N, N, Y
    };

    public enum AddressingMode {
        NONE(1),
        /**
         * (n, x)
         */
        IZX(2),
        /**
         * (n), y
         */
        IZY(2),
        /**
         * this instruction will jam the CPU
         */
        JAM(1),
        /**
         * n
         */
        ZP(2),
        /**
         * n, x
         */
        ZPX(2),
        /**
         * n, y
         */
        ZPY(2),
        /**
         * #n
         */
        IMM(2),
        /**
         * nn
         */
        ABS(3),
        /**
         * nn, x
         */
        ABX(3),
        /**
         * nn, y
         */
        ABY(3),
        /**
         * n (for relative jumps)
         */
        REL(2),
        /**
         * (nn)
         */
        IND(3);

        public int totalBytes;

        AddressingMode(int totalBytes) {
            this.totalBytes = totalBytes;
        }
    }

    public static final AddressingMode[] ADDRESSING_MODE = {
            NONE, IZX, JAM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPX, ZPX, NONE, ABY, NONE, ABY, ABX, ABX, ABX, ABX,
            ABS, IZX, JAM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPX, ZPX, NONE, ABY, NONE, ABY, ABX, ABX, ABX, ABX,
            NONE, IZX, JAM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPX, ZPX, NONE, ABY, NONE, ABY, ABX, ABX, ABX, ABX,
            NONE, IZX, JAM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, IND, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPX, ZPX, NONE, ABY, NONE, ABY, ABX, ABX, ABX, ABX,
            IMM, IZX, IMM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPY, ZPY, NONE, ABY, NONE, ABY, ABX, ABX, ABY, ABY,
            IMM, IZX, IMM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPY, ZPY, NONE, ABY, NONE, ABY, ABX, ABX, ABY, ABY,
            IMM, IZX, IMM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPX, ZPX, NONE, ABY, NONE, ABY, ABX, ABX, ABX, ABX,
            IMM, IZX, IMM, IZX, ZP, ZP, ZP, ZP, NONE, IMM, NONE, IMM, ABS, ABS, ABS, ABS,
            REL, IZY, JAM, IZY, ZPX, ZPX, ZPX, ZPX, NONE, ABY, NONE, ABY, ABX, ABX, ABX, ABX
    };

    public static final boolean[] SPECIAL_CYCLES = {
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, N, N, N, N, N, N, Y, N, N, Y, Y, N, N,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, N, N, N, N, N, N, Y, N, N, Y, Y, N, N,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, N, N, N, N, N, N, Y, N, N, Y, Y, N, N,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, N, N, N, N, N, N, Y, N, N, Y, Y, N, N,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, Y, N, N, N, N, N, Y, N, Y, Y, Y, Y, Y,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, N, N, N, N, N, N, Y, N, N, Y, Y, N, N,
            N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
            Y, Y, N, N, N, N, N, N, N, Y, N, N, Y, Y, N, N
    };

    public static final class Vector {
        public static final int NMI = 0xFFFA;
        public static final int RESET = 0xFFFC;
        public static final int IRQ = 0xFFFE;
        public static final int BRK = IRQ;
    }

    public enum Mpu6502Instruction {
        BRK(0x00, 7, "BRK", false, NONE, false),
        ORA_IZX(0x01, 6, "ORA", false, IZX, false),
        SLO_IZX_03(0x03, 8, "SLO", true, IZX, false),
        NOP_ZP_04(0x04, 3, "NOP", true, ZP, false),
        ORA_ZP(0x05, 3, "ORA", false, ZP, false),
        ASL_ZP(0x06, 5, "ASL", false, ZP, false),
        SLO_ZP_07(0x07, 5, "SLO", true, ZP, false),
        PHP(0x08, 3, "PHP", false, NONE, false),
        ORA_IMM(0x09, 2, "ORA", false, IMM, false),
        ASL(0x0a, 2, "ASL", false, NONE, false),
        ANC_IMM_0B(0x0b, 2, "ANC", true, IMM, false),
        NOP_ABS_0C(0x0c, 4, "NOP", true, ABS, false),
        ORA_ABS(0x0d, 4, "ORA", false, ABS, false),
        ASL_ABS(0x0e, 6, "ASL", false, ABS, false),
        SLO_ABS_0F(0x0f, 6, "SLO", true, ABS, false),
        BPL(0x10, 2, "BPL", false, REL, true),
        ORA_IZY(0x11, 5, "ORA", false, IZY, true),
        SLO_IZY_13(0x13, 8, "SLO", true, IZY, false),
        NOP_ZPX_14(0x14, 4, "NOP", true, ZPX, false),
        ORA_ZPX(0x15, 4, "ORA", false, ZPX, false),
        ASL_ZPX(0x16, 6, "ASL", false, ZPX, false),
        SLO_ZPX_17(0x17, 6, "SLO", true, ZPX, false),
        CLC(0x18, 2, "CLC", false, NONE, false),
        ORA_ABY(0x19, 4, "ORA", false, ABY, true),
        NOP_1A(0x1a, 2, "NOP", true, NONE, false),
        SLO_ABY_1B(0x1b, 7, "SLO", true, ABY, false),
        NOP_ABX_1C(0x1c, 4, "NOP", true, ABX, true),
        ORA_ABX(0x1d, 4, "ORA", false, ABX, true),
        ASL_ABX(0x1e, 7, "ASL", false, ABX, false),
        SLO_ABX_1F(0x1f, 7, "SLO", true, ABX, false),
        JSR_ABS(0x20, 6, "JSR", false, ABS, false),
        AND_IZX(0x21, 6, "AND", false, IZX, false),
        RLA_IZX_23(0x23, 8, "RLA", true, IZX, false),
        BIT_ZP(0x24, 3, "BIT", false, ZP, false),
        AND_ZP(0x25, 3, "AND", false, ZP, false),
        ROL_ZP(0x26, 5, "ROL", false, ZP, false),
        RLA_ZP_27(0x27, 5, "RLA", true, ZP, false),
        PLP(0x28, 4, "PLP", false, NONE, false),
        AND_IMM(0x29, 2, "AND", false, IMM, false),
        ROL(0x2a, 2, "ROL", false, NONE, false),
        ANC_IMM_2B(0x2b, 2, "ANC", true, IMM, false),
        BIT_ABS(0x2c, 4, "BIT", false, ABS, false),
        AND_ABS(0x2d, 4, "AND", false, ABS, false),
        ROL_ABS(0x2e, 6, "ROL", false, ABS, false),
        RLA_ABS_2F(0x2f, 6, "RLA", true, ABS, false),
        BMI(0x30, 2, "BMI", false, REL, true),
        AND_IZY(0x31, 5, "AND", false, IZY, true),
        RLA_IZY_33(0x33, 8, "RLA", true, IZY, false),
        NOP_ZPX_34(0x34, 4, "NOP", true, ZPX, false),
        AND_ZPX(0x35, 4, "AND", false, ZPX, false),
        ROL_ZPX(0x36, 6, "ROL", false, ZPX, false),
        RLA_ZPX_37(0x37, 6, "RLA", true, ZPX, false),
        SEC(0x38, 2, "SEC", false, NONE, false),
        AND_ABY(0x39, 4, "AND", false, ABY, true),
        NOP_3A(0x3a, 2, "NOP", true, NONE, false),
        RLA_ABY_3B(0x3b, 7, "RLA", true, ABY, false),
        NOP_ABX_3C(0x3c, 4, "NOP", true, ABX, true),
        AND_ABX(0x3d, 4, "AND", false, ABX, true),
        ROL_ABX(0x3e, 7, "ROL", false, ABX, false),
        RLA_ABX_3F(0x3f, 7, "RLA", true, ABX, false),
        RTI(0x40, 6, "RTI", false, NONE, false),
        EOR_IZX(0x41, 6, "EOR", false, IZX, false),
        SRE_IZX_43(0x43, 8, "SRE", true, IZX, false),
        NOP_ZP_44(0x44, 3, "NOP", true, ZP, false),
        EOR_ZP(0x45, 3, "EOR", false, ZP, false),
        LSR_ZP(0x46, 5, "LSR", false, ZP, false),
        SRE_ZP_47(0x47, 5, "SRE", true, ZP, false),
        PHA(0x48, 3, "PHA", false, NONE, false),
        EOR_IMM(0x49, 2, "EOR", false, IMM, false),
        LSR(0x4a, 2, "LSR", false, NONE, false),
        ALR_IMM_4B(0x4b, 2, "ALR", true, IMM, false),
        JMP_ABS(0x4c, 3, "JMP", false, ABS, false),
        EOR_ABS(0x4d, 4, "EOR", false, ABS, false),
        LSR_ABS(0x4e, 6, "LSR", false, ABS, false),
        SRE_ABS_4F(0x4f, 6, "SRE", true, ABS, false),
        BVC(0x50, 2, "BVC", false, REL, true),
        EOR_IZY(0x51, 5, "EOR", false, IZY, true),
        SRE_IZY_53(0x53, 8, "SRE", true, IZY, false),
        NOP_ZPX_54(0x54, 4, "NOP", true, ZPX, false),
        EOR_ZPX(0x55, 4, "EOR", false, ZPX, false),
        LSR_ZPX(0x56, 6, "LSR", false, ZPX, false),
        SRE_ZPX_57(0x57, 6, "SRE", true, ZPX, false),
        CLI(0x58, 2, "CLI", false, NONE, false),
        EOR_ABY(0x59, 4, "EOR", false, ABY, true),
        NOP_5A(0x5a, 2, "NOP", true, NONE, false),
        SRE_ABY_5B(0x5b, 7, "SRE", true, ABY, false),
        NOP_ABX_5C(0x5c, 4, "NOP", true, ABX, true),
        EOR_ABX(0x5d, 4, "EOR", false, ABX, true),
        LSR_ABX(0x5e, 7, "LSR", false, ABX, false),
        SRE_ABX_5F(0x5f, 7, "SRE", true, ABX, false),
        RTS(0x60, 6, "RTS", false, NONE, false),
        ADC_IZX(0x61, 6, "ADC", false, IZX, false),
        RRA_IZX_63(0x63, 8, "RRA", true, IZX, false),
        NOP_ZP_64(0x64, 3, "NOP", true, ZP, false),
        ADC_ZP(0x65, 3, "ADC", false, ZP, false),
        ROR_ZP(0x66, 5, "ROR", false, ZP, false),
        RRA_ZP_67(0x67, 5, "RRA", true, ZP, false),
        PLA(0x68, 4, "PLA", false, NONE, false),
        ADC_IMM(0x69, 2, "ADC", false, IMM, false),
        ROR(0x6a, 2, "ROR", false, NONE, false),
        ARR_IMM_6B(0x6b, 2, "ARR", true, IMM, false),
        JMP_IND(0x6c, 5, "JMP", false, IND, false),
        ADC_ABS(0x6d, 4, "ADC", false, ABS, false),
        ROR_ABS(0x6e, 6, "ROR", false, ABS, false),
        RRA_ABS_6F(0x6f, 6, "RRA", true, ABS, false),
        BVS(0x70, 2, "BVS", false, REL, true),
        ADC_IZY(0x71, 5, "ADC", false, IZY, true),
        RRA_IZY_73(0x73, 8, "RRA", true, IZY, false),
        NOP_ZPX_74(0x74, 4, "NOP", true, ZPX, false),
        ADC_ZPX(0x75, 4, "ADC", false, ZPX, false),
        ROR_ZPX(0x76, 6, "ROR", false, ZPX, false),
        RRA_ZPX_77(0x77, 6, "RRA", true, ZPX, false),
        SEI(0x78, 2, "SEI", false, NONE, false),
        ADC_ABY(0x79, 4, "ADC", false, ABY, true),
        NOP_7A(0x7a, 2, "NOP", true, NONE, false),
        RRA_ABY_7B(0x7b, 7, "RRA", true, ABY, false),
        NOP_ABX_7C(0x7c, 4, "NOP", true, ABX, true),
        ADC_ABX(0x7d, 4, "ADC", false, ABX, true),
        ROR_ABX(0x7e, 7, "ROR", false, ABX, false),
        RRA_ABX_7F(0x7f, 7, "RRA", true, ABX, false),
        NOP_IMM_80(0x80, 2, "NOP", true, IMM, false),
        STA_IZX(0x81, 6, "STA", false, IZX, false),
        NOP_IMM_82(0x82, 2, "NOP", true, IMM, false),
        SAX_IZX_83(0x83, 6, "SAX", true, IZX, false),
        STY_ZP(0x84, 3, "STY", false, ZP, false),
        STA_ZP(0x85, 3, "STA", false, ZP, false),
        STX_ZP(0x86, 3, "STX", false, ZP, false),
        SAX_ZP_87(0x87, 3, "SAX", true, ZP, false),
        DEY(0x88, 2, "DEY", false, NONE, false),
        NOP_IMM_89(0x89, 2, "NOP", true, IMM, false),
        TXA(0x8a, 2, "TXA", false, NONE, false),
        XAA_IMM_8B(0x8b, 2, "XAA", true, IMM, false),
        STY_ABS(0x8c, 4, "STY", false, ABS, false),
        STA_ABS(0x8d, 4, "STA", false, ABS, false),
        STX_ABS(0x8e, 4, "STX", false, ABS, false),
        SAX_ABS_8F(0x8f, 4, "SAX", true, ABS, false),
        BCC(0x90, 2, "BCC", false, REL, true),
        STA_IZY(0x91, 6, "STA", false, IZY, false),
        AHX_IZY_93(0x93, 6, "AHX", true, IZY, false),
        STY_ZPX(0x94, 4, "STY", false, ZPX, false),
        STA_ZPX(0x95, 4, "STA", false, ZPX, false),
        STX_ZPY(0x96, 4, "STX", false, ZPY, false),
        SAX_ZPY_97(0x97, 4, "SAX", true, ZPY, false),
        TYA(0x98, 2, "TYA", false, NONE, false),
        STA_ABY(0x99, 5, "STA", false, ABY, false),
        TXS(0x9a, 2, "TXS", false, NONE, false),
        TAS_ABY_9B(0x9b, 5, "TAS", true, ABY, false),
        SHY_ABX_9C(0x9c, 5, "SHY", true, ABX, false),
        STA_ABX(0x9d, 5, "STA", false, ABX, false),
        SHX_ABY_9E(0x9e, 5, "SHX", true, ABY, false),
        AHX_ABY_9F(0x9f, 5, "AHX", true, ABY, false),
        LDY_IMM(0xa0, 2, "LDY", false, IMM, false),
        LDA_IZX(0xa1, 6, "LDA", false, IZX, false),
        LDX_IMM(0xa2, 2, "LDX", false, IMM, false),
        LAX_IZX_A3(0xa3, 6, "LAX", true, IZX, false),
        LDY_ZP(0xa4, 3, "LDY", false, ZP, false),
        LDA_ZP(0xa5, 3, "LDA", false, ZP, false),
        LDX_ZP(0xa6, 3, "LDX", false, ZP, false),
        LAX_ZP_A7(0xa7, 3, "LAX", true, ZP, false),
        TAY(0xa8, 2, "TAY", false, NONE, false),
        LDA_IMM(0xa9, 2, "LDA", false, IMM, false),
        TAX(0xaa, 2, "TAX", false, NONE, false),
        LAX_IMM_AB(0xab, 2, "LAX", true, IMM, false),
        LDY_ABS(0xac, 4, "LDY", false, ABS, false),
        LDA_ABS(0xad, 4, "LDA", false, ABS, false),
        LDX_ABS(0xae, 4, "LDX", false, ABS, false),
        LAX_ABS_AF(0xaf, 4, "LAX", true, ABS, false),
        BCS(0xb0, 2, "BCS", false, REL, true),
        LDA_IZY(0xb1, 5, "LDA", false, IZY, true),
        LAX_IZY_B3(0xb3, 5, "LAX", true, IZY, true),
        LDY_ZPX(0xb4, 4, "LDY", false, ZPX, false),
        LDA_ZPX(0xb5, 4, "LDA", false, ZPX, false),
        LDX_ZPY(0xb6, 4, "LDX", false, ZPY, false),
        LAX_ZPY_B7(0xb7, 4, "LAX", true, ZPY, false),
        CLV(0xb8, 2, "CLV", false, NONE, false),
        LDA_ABY(0xb9, 4, "LDA", false, ABY, true),
        TSX(0xba, 2, "TSX", false, NONE, false),
        LAS_ABY_BB(0xbb, 4, "LAS", true, ABY, true),
        LDY_ABX(0xbc, 4, "LDY", false, ABX, true),
        LDA_ABX(0xbd, 4, "LDA", false, ABX, true),
        LDX_ABY(0xbe, 4, "LDX", false, ABY, true),
        LAX_ABY_BF(0xbf, 4, "LAX", true, ABY, true),
        CPY_IMM(0xc0, 2, "CPY", false, IMM, false),
        CMP_IZX(0xc1, 6, "CMP", false, IZX, false),
        NOP_IMM_C2(0xc2, 2, "NOP", true, IMM, false),
        DCP_IZX_C3(0xc3, 8, "DCP", true, IZX, false),
        CPY_ZP(0xc4, 3, "CPY", false, ZP, false),
        CMP_ZP(0xc5, 3, "CMP", false, ZP, false),
        DEC_ZP(0xc6, 5, "DEC", false, ZP, false),
        DCP_ZP_C7(0xc7, 5, "DCP", true, ZP, false),
        INY(0xc8, 2, "INY", false, NONE, false),
        CMP_IMM(0xc9, 2, "CMP", false, IMM, false),
        DEX(0xca, 2, "DEX", false, NONE, false),
        AXS_IMM_CB(0xcb, 2, "AXS", true, IMM, false),
        CPY_ABS(0xcc, 4, "CPY", false, ABS, false),
        CMP_ABS(0xcd, 4, "CMP", false, ABS, false),
        DEC_ABS(0xce, 6, "DEC", false, ABS, false),
        DCP_ABS_CF(0xcf, 6, "DCP", true, ABS, false),
        BNE(0xd0, 2, "BNE", false, REL, true),
        CMP_IZY(0xd1, 5, "CMP", false, IZY, true),
        DCP_IZY_D3(0xd3, 8, "DCP", true, IZY, false),
        NOP_ZPX_D4(0xd4, 4, "NOP", true, ZPX, false),
        CMP_ZPX(0xd5, 4, "CMP", false, ZPX, false),
        DEC_ZPX(0xd6, 6, "DEC", false, ZPX, false),
        DCP_ZPX_D7(0xd7, 6, "DCP", true, ZPX, false),
        CLD(0xd8, 2, "CLD", false, NONE, false),
        CMP_ABY(0xd9, 4, "CMP", false, ABY, true),
        NOP_DA(0xda, 2, "NOP", true, NONE, false),
        DCP_ABY_DB(0xdb, 7, "DCP", true, ABY, false),
        NOP_ABX_DC(0xdc, 4, "NOP", true, ABX, true),
        CMP_ABX(0xdd, 4, "CMP", false, ABX, true),
        DEC_ABX(0xde, 7, "DEC", false, ABX, false),
        DCP_ABX_DF(0xdf, 7, "DCP", true, ABX, false),
        CPX_IMM(0xe0, 2, "CPX", false, IMM, false),
        SBC_IZX(0xe1, 6, "SBC", false, IZX, false),
        NOP_IMM_E2(0xe2, 2, "NOP", true, IMM, false),
        ISC_IZX_E3(0xe3, 8, "ISC", true, IZX, false),
        CPX_ZP(0xe4, 3, "CPX", false, ZP, false),
        SBC_ZP(0xe5, 3, "SBC", false, ZP, false),
        INC_ZP(0xe6, 5, "INC", false, ZP, false),
        ISC_ZP_E7(0xe7, 5, "ISC", true, ZP, false),
        INX(0xe8, 2, "INX", false, NONE, false),
        SBC_IMM(0xe9, 2, "SBC", false, IMM, false),
        NOP(0xea, 2, "NOP", false, NONE, false),
        SBC_IMM_EB(0xeb, 2, "SBC", true, IMM, false),
        CPX_ABS(0xec, 4, "CPX", false, ABS, false),
        SBC_ABS(0xed, 4, "SBC", false, ABS, false),
        INC_ABS(0xee, 6, "INC", false, ABS, false),
        ISC_ABS_EF(0xef, 6, "ISC", true, ABS, false),
        BEQ(0xf0, 2, "BEQ", false, REL, true),
        SBC_IZY(0xf1, 5, "SBC", false, IZY, true),
        ISC_IZY_F3(0xf3, 8, "ISC", true, IZY, false),
        NOP_ZPX_F4(0xf4, 4, "NOP", true, ZPX, false),
        SBC_ZPX(0xf5, 4, "SBC", false, ZPX, false),
        INC_ZPX(0xf6, 6, "INC", false, ZPX, false),
        ISC_ZPX_F7(0xf7, 6, "ISC", true, ZPX, false),
        SED(0xf8, 2, "SED", false, NONE, false),
        SBC_ABY(0xf9, 4, "SBC", false, ABY, true),
        NOP_FA(0xfa, 2, "NOP", true, NONE, false),
        ISC_ABY_FB(0xfb, 7, "ISC", true, ABY, false),
        NOP_ABX_FC(0xfc, 4, "NOP", true, ABX, true),
        SBC_ABX(0xfd, 4, "SBC", false, ABX, true),
        INC_ABX(0xfe, 7, "INC", false, ABX, false),
        ISC_ABX_FF(0xff, 7, "ISC", true, ABX, false);

        public final int nr;
        public final int cycles;
        public final String name;
        public final boolean illegal;
        public final Mpu6502Specifications.AddressingMode addressingMode;
        public final boolean specialCycles;

        Mpu6502Instruction(int nr, int cycles, String name, boolean illegal, Mpu6502Specifications.AddressingMode addressingMode, boolean specialCycles) {
            this.nr = nr;
            this.cycles = cycles;
            this.name = name;
            this.illegal = illegal;
            this.addressingMode = addressingMode;
            this.specialCycles = specialCycles;
        }
    }

    public static final Mpu6502Instruction[] INSTRUCTION_BY_NR = new Mpu6502Instruction[256];
    public static final Map<String, List<Mpu6502Instruction>> INSTRUCTIONS_BY_NAME = new HashMap<>();

    static {
        for (Mpu6502Instruction instruction : Mpu6502Instruction.values()) {
            INSTRUCTION_BY_NR[instruction.nr] = instruction;
            List<Mpu6502Instruction> instructions = INSTRUCTIONS_BY_NAME.computeIfAbsent(instruction.name, name -> new ArrayList<>());
            instructions.add(instruction);
        }
    }
}
