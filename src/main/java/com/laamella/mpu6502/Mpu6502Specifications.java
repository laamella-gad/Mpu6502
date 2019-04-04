package com.laamella.mpu6502;

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
        NONE(1), IZX(2), IZY(2), JAM(1), ZP(2), ZPX(2), ZPY(2), IMM(2), ABS(3), ABX(3), ABY(3), REL(2), IND(3);

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
}
