package com.laamella.mpu6502.specification;

import java.util.HashMap;
import java.util.Map;

import static com.laamella.mpu6502.specification.AddressingMode.*;

/**
 * A big collection of data on the processor's instructions.
 */
public enum Instruction {
    BRK(0x00, 7, Opcode.BRK, false, NONE, false),
    ORA_IZX(0x01, 6, Opcode.ORA, false, IND_X, false),
    SLO_IZX_03(0x03, 8, Opcode.SLO, true, IND_X, false),
    NOP_ZP_04(0x04, 3, Opcode.NOP, true, ZERO_PAGE, false),
    ORA_ZP(0x05, 3, Opcode.ORA, false, ZERO_PAGE, false),
    ASL_ZP(0x06, 5, Opcode.ASL, false, ZERO_PAGE, false),
    SLO_ZP_07(0x07, 5, Opcode.SLO, true, ZERO_PAGE, false),
    PHP(0x08, 3, Opcode.PHP, false, NONE, false),
    ORA_IMM(0x09, 2, Opcode.ORA, false, IMMEDIATE, false),
    ASL(0x0a, 2, Opcode.ASL, false, ACCUMULATOR, false),
    ANC_IMM_0B(0x0b, 2, Opcode.ANC, true, IMMEDIATE, false),
    NOP_ABS_0C(0x0c, 4, Opcode.NOP, true, ABSOLUTE, false),
    ORA_ABS(0x0d, 4, Opcode.ORA, false, ABSOLUTE, false),
    ASL_ABS(0x0e, 6, Opcode.ASL, false, ABSOLUTE, false),
    SLO_ABS_0F(0x0f, 6, Opcode.SLO, true, ABSOLUTE, false),
    BPL(0x10, 2, Opcode.BPL, false, RELATIVE, true),
    ORA_IZY(0x11, 5, Opcode.ORA, false, IND_Y, true),
    SLO_IZY_13(0x13, 8, Opcode.SLO, true, IND_Y, false),
    NOP_ZPX_14(0x14, 4, Opcode.NOP, true, ZERO_PAGE_X, false),
    ORA_ZPX(0x15, 4, Opcode.ORA, false, ZERO_PAGE_X, false),
    ASL_ZPX(0x16, 6, Opcode.ASL, false, ZERO_PAGE_X, false),
    SLO_ZPX_17(0x17, 6, Opcode.SLO, true, ZERO_PAGE_X, false),
    CLC(0x18, 2, Opcode.CLC, false, NONE, false),
    ORA_ABY(0x19, 4, Opcode.ORA, false, ABSOLUTE_Y, true),
    NOP_1A(0x1a, 2, Opcode.NOP, true, NONE, false),
    SLO_ABY_1B(0x1b, 7, Opcode.SLO, true, ABSOLUTE_Y, false),
    NOP_ABX_1C(0x1c, 4, Opcode.NOP, true, ABSOLUTE_X, true),
    ORA_ABX(0x1d, 4, Opcode.ORA, false, ABSOLUTE_X, true),
    ASL_ABX(0x1e, 7, Opcode.ASL, false, ABSOLUTE_X, false),
    SLO_ABX_1F(0x1f, 7, Opcode.SLO, true, ABSOLUTE_X, false),
    JSR_ABS(0x20, 6, Opcode.JSR, false, ABSOLUTE, false),
    AND_IZX(0x21, 6, Opcode.AND, false, IND_X, false),
    RLA_IZX_23(0x23, 8, Opcode.RLA, true, IND_X, false),
    BIT_ZP(0x24, 3, Opcode.BIT, false, ZERO_PAGE, false),
    AND_ZP(0x25, 3, Opcode.AND, false, ZERO_PAGE, false),
    ROL_ZP(0x26, 5, Opcode.ROL, false, ZERO_PAGE, false),
    RLA_ZP_27(0x27, 5, Opcode.RLA, true, ZERO_PAGE, false),
    PLP(0x28, 4, Opcode.PLP, false, NONE, false),
    AND_IMM(0x29, 2, Opcode.AND, false, IMMEDIATE, false),
    ROL(0x2a, 2, Opcode.ROL, false, ACCUMULATOR, false),
    ANC_IMM_2B(0x2b, 2, Opcode.ANC, true, IMMEDIATE, false),
    BIT_ABS(0x2c, 4, Opcode.BIT, false, ABSOLUTE, false),
    AND_ABS(0x2d, 4, Opcode.AND, false, ABSOLUTE, false),
    ROL_ABS(0x2e, 6, Opcode.ROL, false, ABSOLUTE, false),
    RLA_ABS_2F(0x2f, 6, Opcode.RLA, true, ABSOLUTE, false),
    BMI(0x30, 2, Opcode.BMI, false, RELATIVE, true),
    AND_IZY(0x31, 5, Opcode.AND, false, IND_Y, true),
    RLA_IZY_33(0x33, 8, Opcode.RLA, true, IND_Y, false),
    NOP_ZPX_34(0x34, 4, Opcode.NOP, true, ZERO_PAGE_X, false),
    AND_ZPX(0x35, 4, Opcode.AND, false, ZERO_PAGE_X, false),
    ROL_ZPX(0x36, 6, Opcode.ROL, false, ZERO_PAGE_X, false),
    RLA_ZPX_37(0x37, 6, Opcode.RLA, true, ZERO_PAGE_X, false),
    SEC(0x38, 2, Opcode.SEC, false, NONE, false),
    AND_ABY(0x39, 4, Opcode.AND, false, ABSOLUTE_Y, true),
    NOP_3A(0x3a, 2, Opcode.NOP, true, NONE, false),
    RLA_ABY_3B(0x3b, 7, Opcode.RLA, true, ABSOLUTE_Y, false),
    NOP_ABX_3C(0x3c, 4, Opcode.NOP, true, ABSOLUTE_X, true),
    AND_ABX(0x3d, 4, Opcode.AND, false, ABSOLUTE_X, true),
    ROL_ABX(0x3e, 7, Opcode.ROL, false, ABSOLUTE_X, false),
    RLA_ABX_3F(0x3f, 7, Opcode.RLA, true, ABSOLUTE_X, false),
    RTI(0x40, 6, Opcode.RTI, false, NONE, false),
    EOR_IZX(0x41, 6, Opcode.EOR, false, IND_X, false),
    SRE_IZX_43(0x43, 8, Opcode.SRE, true, IND_X, false),
    NOP_ZP_44(0x44, 3, Opcode.NOP, true, ZERO_PAGE, false),
    EOR_ZP(0x45, 3, Opcode.EOR, false, ZERO_PAGE, false),
    LSR_ZP(0x46, 5, Opcode.LSR, false, ZERO_PAGE, false),
    SRE_ZP_47(0x47, 5, Opcode.SRE, true, ZERO_PAGE, false),
    PHA(0x48, 3, Opcode.PHA, false, NONE, false),
    EOR_IMM(0x49, 2, Opcode.EOR, false, IMMEDIATE, false),
    LSR(0x4a, 2, Opcode.LSR, false, ACCUMULATOR, false),
    ALR_IMM_4B(0x4b, 2, Opcode.ALR, true, IMMEDIATE, false),
    JMP_ABS(0x4c, 3, Opcode.JMP, false, ABSOLUTE, false),
    EOR_ABS(0x4d, 4, Opcode.EOR, false, ABSOLUTE, false),
    LSR_ABS(0x4e, 6, Opcode.LSR, false, ABSOLUTE, false),
    SRE_ABS_4F(0x4f, 6, Opcode.SRE, true, ABSOLUTE, false),
    BVC(0x50, 2, Opcode.BVC, false, RELATIVE, true),
    EOR_IZY(0x51, 5, Opcode.EOR, false, IND_Y, true),
    SRE_IZY_53(0x53, 8, Opcode.SRE, true, IND_Y, false),
    NOP_ZPX_54(0x54, 4, Opcode.NOP, true, ZERO_PAGE_X, false),
    EOR_ZPX(0x55, 4, Opcode.EOR, false, ZERO_PAGE_X, false),
    LSR_ZPX(0x56, 6, Opcode.LSR, false, ZERO_PAGE_X, false),
    SRE_ZPX_57(0x57, 6, Opcode.SRE, true, ZERO_PAGE_X, false),
    CLI(0x58, 2, Opcode.CLI, false, NONE, false),
    EOR_ABY(0x59, 4, Opcode.EOR, false, ABSOLUTE_Y, true),
    NOP_5A(0x5a, 2, Opcode.NOP, true, NONE, false),
    SRE_ABY_5B(0x5b, 7, Opcode.SRE, true, ABSOLUTE_Y, false),
    NOP_ABX_5C(0x5c, 4, Opcode.NOP, true, ABSOLUTE_X, true),
    EOR_ABX(0x5d, 4, Opcode.EOR, false, ABSOLUTE_X, true),
    LSR_ABX(0x5e, 7, Opcode.LSR, false, ABSOLUTE_X, false),
    SRE_ABX_5F(0x5f, 7, Opcode.SRE, true, ABSOLUTE_X, false),
    RTS(0x60, 6, Opcode.RTS, false, NONE, false),
    ADC_IZX(0x61, 6, Opcode.ADC, false, IND_X, false),
    RRA_IZX_63(0x63, 8, Opcode.RRA, true, IND_X, false),
    NOP_ZP_64(0x64, 3, Opcode.NOP, true, ZERO_PAGE, false),
    ADC_ZP(0x65, 3, Opcode.ADC, false, ZERO_PAGE, false),
    ROR_ZP(0x66, 5, Opcode.ROR, false, ZERO_PAGE, false),
    RRA_ZP_67(0x67, 5, Opcode.RRA, true, ZERO_PAGE, false),
    PLA(0x68, 4, Opcode.PLA, false, NONE, false),
    ADC_IMM(0x69, 2, Opcode.ADC, false, IMMEDIATE, false),
    ROR(0x6a, 2, Opcode.ROR, false, ACCUMULATOR, false),
    ARR_IMM_6B(0x6b, 2, Opcode.ARR, true, IMMEDIATE, false),
    JMP_IND(0x6c, 5, Opcode.JMP, false, INDIRECT, false),
    ADC_ABS(0x6d, 4, Opcode.ADC, false, ABSOLUTE, false),
    ROR_ABS(0x6e, 6, Opcode.ROR, false, ABSOLUTE, false),
    RRA_ABS_6F(0x6f, 6, Opcode.RRA, true, ABSOLUTE, false),
    BVS(0x70, 2, Opcode.BVS, false, RELATIVE, true),
    ADC_IZY(0x71, 5, Opcode.ADC, false, IND_Y, true),
    RRA_IZY_73(0x73, 8, Opcode.RRA, true, IND_Y, false),
    NOP_ZPX_74(0x74, 4, Opcode.NOP, true, ZERO_PAGE_X, false),
    ADC_ZPX(0x75, 4, Opcode.ADC, false, ZERO_PAGE_X, false),
    ROR_ZPX(0x76, 6, Opcode.ROR, false, ZERO_PAGE_X, false),
    RRA_ZPX_77(0x77, 6, Opcode.RRA, true, ZERO_PAGE_X, false),
    SEI(0x78, 2, Opcode.SEI, false, NONE, false),
    ADC_ABY(0x79, 4, Opcode.ADC, false, ABSOLUTE_Y, true),
    NOP_7A(0x7a, 2, Opcode.NOP, true, NONE, false),
    RRA_ABY_7B(0x7b, 7, Opcode.RRA, true, ABSOLUTE_Y, false),
    NOP_ABX_7C(0x7c, 4, Opcode.NOP, true, ABSOLUTE_X, true),
    ADC_ABX(0x7d, 4, Opcode.ADC, false, ABSOLUTE_X, true),
    ROR_ABX(0x7e, 7, Opcode.ROR, false, ABSOLUTE_X, false),
    RRA_ABX_7F(0x7f, 7, Opcode.RRA, true, ABSOLUTE_X, false),
    NOP_IMM_80(0x80, 2, Opcode.NOP, true, IMMEDIATE, false),
    STA_IZX(0x81, 6, Opcode.STA, false, IND_X, false),
    NOP_IMM_82(0x82, 2, Opcode.NOP, true, IMMEDIATE, false),
    SAX_IZX_83(0x83, 6, Opcode.SAX, true, IND_X, false),
    STY_ZP(0x84, 3, Opcode.STY, false, ZERO_PAGE, false),
    STA_ZP(0x85, 3, Opcode.STA, false, ZERO_PAGE, false),
    STX_ZP(0x86, 3, Opcode.STX, false, ZERO_PAGE, false),
    SAX_ZP_87(0x87, 3, Opcode.SAX, true, ZERO_PAGE, false),
    DEY(0x88, 2, Opcode.DEY, false, NONE, false),
    NOP_IMM_89(0x89, 2, Opcode.NOP, true, IMMEDIATE, false),
    TXA(0x8a, 2, Opcode.TXA, false, NONE, false),
    XAA_IMM_8B(0x8b, 2, Opcode.XAA, true, IMMEDIATE, false),
    STY_ABS(0x8c, 4, Opcode.STY, false, ABSOLUTE, false),
    STA_ABS(0x8d, 4, Opcode.STA, false, ABSOLUTE, false),
    STX_ABS(0x8e, 4, Opcode.STX, false, ABSOLUTE, false),
    SAX_ABS_8F(0x8f, 4, Opcode.SAX, true, ABSOLUTE, false),
    BCC(0x90, 2, Opcode.BCC, false, RELATIVE, true),
    STA_IZY(0x91, 6, Opcode.STA, false, IND_Y, false),
    AHX_IZY_93(0x93, 6, Opcode.AHX, true, IND_Y, false),
    STY_ZPX(0x94, 4, Opcode.STY, false, ZERO_PAGE_X, false),
    STA_ZPX(0x95, 4, Opcode.STA, false, ZERO_PAGE_X, false),
    STX_ZPY(0x96, 4, Opcode.STX, false, ZERO_PAGE_Y, false),
    SAX_ZPY_97(0x97, 4, Opcode.SAX, true, ZERO_PAGE_Y, false),
    TYA(0x98, 2, Opcode.TYA, false, NONE, false),
    STA_ABY(0x99, 5, Opcode.STA, false, ABSOLUTE_Y, false),
    TXS(0x9a, 2, Opcode.TXS, false, NONE, false),
    TAS_ABY_9B(0x9b, 5, Opcode.TAS, true, ABSOLUTE_Y, false),
    SHY_ABX_9C(0x9c, 5, Opcode.SHY, true, ABSOLUTE_X, false),
    STA_ABX(0x9d, 5, Opcode.STA, false, ABSOLUTE_X, false),
    SHX_ABY_9E(0x9e, 5, Opcode.SHX, true, ABSOLUTE_Y, false),
    AHX_ABY_9F(0x9f, 5, Opcode.AHX, true, ABSOLUTE_Y, false),
    LDY_IMM(0xa0, 2, Opcode.LDY, false, IMMEDIATE, false),
    LDA_IZX(0xa1, 6, Opcode.LDA, false, IND_X, false),
    LDX_IMM(0xa2, 2, Opcode.LDX, false, IMMEDIATE, false),
    LAX_IZX_A3(0xa3, 6, Opcode.LAX, true, IND_X, false),
    LDY_ZP(0xa4, 3, Opcode.LDY, false, ZERO_PAGE, false),
    LDA_ZP(0xa5, 3, Opcode.LDA, false, ZERO_PAGE, false),
    LDX_ZP(0xa6, 3, Opcode.LDX, false, ZERO_PAGE, false),
    LAX_ZP_A7(0xa7, 3, Opcode.LAX, true, ZERO_PAGE, false),
    TAY(0xa8, 2, Opcode.TAY, false, NONE, false),
    LDA_IMM(0xa9, 2, Opcode.LDA, false, IMMEDIATE, false),
    TAX(0xaa, 2, Opcode.TAX, false, NONE, false),
    LAX_IMM_AB(0xab, 2, Opcode.LAX, true, IMMEDIATE, false),
    LDY_ABS(0xac, 4, Opcode.LDY, false, ABSOLUTE, false),
    LDA_ABS(0xad, 4, Opcode.LDA, false, ABSOLUTE, false),
    LDX_ABS(0xae, 4, Opcode.LDX, false, ABSOLUTE, false),
    LAX_ABS_AF(0xaf, 4, Opcode.LAX, true, ABSOLUTE, false),
    BCS(0xb0, 2, Opcode.BCS, false, RELATIVE, true),
    LDA_IZY(0xb1, 5, Opcode.LDA, false, IND_Y, true),
    LAX_IZY_B3(0xb3, 5, Opcode.LAX, true, IND_Y, true),
    LDY_ZPX(0xb4, 4, Opcode.LDY, false, ZERO_PAGE_X, false),
    LDA_ZPX(0xb5, 4, Opcode.LDA, false, ZERO_PAGE_X, false),
    LDX_ZPY(0xb6, 4, Opcode.LDX, false, ZERO_PAGE_Y, false),
    LAX_ZPY_B7(0xb7, 4, Opcode.LAX, true, ZERO_PAGE_Y, false),
    CLV(0xb8, 2, Opcode.CLV, false, NONE, false),
    LDA_ABY(0xb9, 4, Opcode.LDA, false, ABSOLUTE_Y, true),
    TSX(0xba, 2, Opcode.TSX, false, NONE, false),
    LAS_ABY_BB(0xbb, 4, Opcode.LAS, true, ABSOLUTE_Y, true),
    LDY_ABX(0xbc, 4, Opcode.LDY, false, ABSOLUTE_X, true),
    LDA_ABX(0xbd, 4, Opcode.LDA, false, ABSOLUTE_X, true),
    LDX_ABY(0xbe, 4, Opcode.LDX, false, ABSOLUTE_Y, true),
    LAX_ABY_BF(0xbf, 4, Opcode.LAX, true, ABSOLUTE_Y, true),
    CPY_IMM(0xc0, 2, Opcode.CPY, false, IMMEDIATE, false),
    CMP_IZX(0xc1, 6, Opcode.CMP, false, IND_X, false),
    NOP_IMM_C2(0xc2, 2, Opcode.NOP, true, IMMEDIATE, false),
    DCP_IZX_C3(0xc3, 8, Opcode.DCP, true, IND_X, false),
    CPY_ZP(0xc4, 3, Opcode.CPY, false, ZERO_PAGE, false),
    CMP_ZP(0xc5, 3, Opcode.CMP, false, ZERO_PAGE, false),
    DEC_ZP(0xc6, 5, Opcode.DEC, false, ZERO_PAGE, false),
    DCP_ZP_C7(0xc7, 5, Opcode.DCP, true, ZERO_PAGE, false),
    INY(0xc8, 2, Opcode.INY, false, NONE, false),
    CMP_IMM(0xc9, 2, Opcode.CMP, false, IMMEDIATE, false),
    DEX(0xca, 2, Opcode.DEX, false, NONE, false),
    AXS_IMM_CB(0xcb, 2, Opcode.AXS, true, IMMEDIATE, false),
    CPY_ABS(0xcc, 4, Opcode.CPY, false, ABSOLUTE, false),
    CMP_ABS(0xcd, 4, Opcode.CMP, false, ABSOLUTE, false),
    DEC_ABS(0xce, 6, Opcode.DEC, false, ABSOLUTE, false),
    DCP_ABS_CF(0xcf, 6, Opcode.DCP, true, ABSOLUTE, false),
    BNE(0xd0, 2, Opcode.BNE, false, RELATIVE, true),
    CMP_IZY(0xd1, 5, Opcode.CMP, false, IND_Y, true),
    DCP_IZY_D3(0xd3, 8, Opcode.DCP, true, IND_Y, false),
    NOP_ZPX_D4(0xd4, 4, Opcode.NOP, true, ZERO_PAGE_X, false),
    CMP_ZPX(0xd5, 4, Opcode.CMP, false, ZERO_PAGE_X, false),
    DEC_ZPX(0xd6, 6, Opcode.DEC, false, ZERO_PAGE_X, false),
    DCP_ZPX_D7(0xd7, 6, Opcode.DCP, true, ZERO_PAGE_X, false),
    CLD(0xd8, 2, Opcode.CLD, false, NONE, false),
    CMP_ABY(0xd9, 4, Opcode.CMP, false, ABSOLUTE_Y, true),
    NOP_DA(0xda, 2, Opcode.NOP, true, NONE, false),
    DCP_ABY_DB(0xdb, 7, Opcode.DCP, true, ABSOLUTE_Y, false),
    NOP_ABX_DC(0xdc, 4, Opcode.NOP, true, ABSOLUTE_X, true),
    CMP_ABX(0xdd, 4, Opcode.CMP, false, ABSOLUTE_X, true),
    DEC_ABX(0xde, 7, Opcode.DEC, false, ABSOLUTE_X, false),
    DCP_ABX_DF(0xdf, 7, Opcode.DCP, true, ABSOLUTE_X, false),
    CPX_IMM(0xe0, 2, Opcode.CPX, false, IMMEDIATE, false),
    SBC_IZX(0xe1, 6, Opcode.SBC, false, IND_X, false),
    NOP_IMM_E2(0xe2, 2, Opcode.NOP, true, IMMEDIATE, false),
    ISC_IZX_E3(0xe3, 8, Opcode.ISC, true, IND_X, false),
    CPX_ZP(0xe4, 3, Opcode.CPX, false, ZERO_PAGE, false),
    SBC_ZP(0xe5, 3, Opcode.SBC, false, ZERO_PAGE, false),
    INC_ZP(0xe6, 5, Opcode.INC, false, ZERO_PAGE, false),
    ISC_ZP_E7(0xe7, 5, Opcode.ISC, true, ZERO_PAGE, false),
    INX(0xe8, 2, Opcode.INX, false, NONE, false),
    SBC_IMM(0xe9, 2, Opcode.SBC, false, IMMEDIATE, false),
    NOP(0xea, 2, Opcode.NOP, false, NONE, false),
    SBC_IMM_EB(0xeb, 2, Opcode.SBC, true, IMMEDIATE, false),
    CPX_ABS(0xec, 4, Opcode.CPX, false, ABSOLUTE, false),
    SBC_ABS(0xed, 4, Opcode.SBC, false, ABSOLUTE, false),
    INC_ABS(0xee, 6, Opcode.INC, false, ABSOLUTE, false),
    ISC_ABS_EF(0xef, 6, Opcode.ISC, true, ABSOLUTE, false),
    BEQ(0xf0, 2, Opcode.BEQ, false, RELATIVE, true),
    SBC_IZY(0xf1, 5, Opcode.SBC, false, IND_Y, true),
    ISC_IZY_F3(0xf3, 8, Opcode.ISC, true, IND_Y, false),
    NOP_ZPX_F4(0xf4, 4, Opcode.NOP, true, ZERO_PAGE_X, false),
    SBC_ZPX(0xf5, 4, Opcode.SBC, false, ZERO_PAGE_X, false),
    INC_ZPX(0xf6, 6, Opcode.INC, false, ZERO_PAGE_X, false),
    ISC_ZPX_F7(0xf7, 6, Opcode.ISC, true, ZERO_PAGE_X, false),
    SED(0xf8, 2, Opcode.SED, false, NONE, false),
    SBC_ABY(0xf9, 4, Opcode.SBC, false, ABSOLUTE_Y, true),
    NOP_FA(0xfa, 2, Opcode.NOP, true, NONE, false),
    ISC_ABY_FB(0xfb, 7, Opcode.ISC, true, ABSOLUTE_Y, false),
    NOP_ABX_FC(0xfc, 4, Opcode.NOP, true, ABSOLUTE_X, true),
    SBC_ABX(0xfd, 4, Opcode.SBC, false, ABSOLUTE_X, true),
    INC_ABX(0xfe, 7, Opcode.INC, false, ABSOLUTE_X, false),
    ISC_ABX_FF(0xff, 7, Opcode.ISC, true, ABSOLUTE_X, false);

    public final int nr;
    public final int cycles;
    public final Opcode opcode;
    public final boolean illegal;
    public final AddressingMode addressingMode;
    public final boolean specialCycles;

    Instruction(int nr, int cycles, Opcode opcode, boolean illegal, AddressingMode addressingMode, boolean specialCycles) {
        this.nr = nr;
        this.cycles = cycles;
        this.opcode = opcode;
        this.illegal = illegal;
        this.addressingMode = addressingMode;
        this.specialCycles = specialCycles;
    }

    public static final Instruction[] INSTRUCTION_BY_NR = new Instruction[256];
    private static final Map<Opcode, Map<AddressingMode, Instruction>> INSTRUCTIONS_BY_NAME_BY_ADDRESSING_MODE = new HashMap<>();

    static {
        for (Instruction instruction : Instruction.values()) {
            INSTRUCTION_BY_NR[instruction.nr] = instruction;
            Map<AddressingMode, Instruction> addressingModes = INSTRUCTIONS_BY_NAME_BY_ADDRESSING_MODE.computeIfAbsent(instruction.opcode, name -> new HashMap<>());
            addressingModes.put(instruction.addressingMode, instruction);
        }
    }

    public static Instruction find(Opcode opcode, AddressingMode addressingMode) {
        Map<AddressingMode, Instruction> addressingModes = find(opcode);
        if (addressingModes == null) {
            return null;
        }
        return addressingModes.get(addressingMode);
    }

    public static Instruction find(String opcode, AddressingMode addressingMode) {
        Map<AddressingMode, Instruction> addressingModes = find(opcode);
        if (addressingModes == null) {
            return null;
        }
        return addressingModes.get(addressingMode);
    }

    public static Map<AddressingMode, Instruction> find(String name) {
        return find(Opcode.valueOf(name.toUpperCase()));
    }

    public static Map<AddressingMode, Instruction> find(Opcode opcode) {
        return INSTRUCTIONS_BY_NAME_BY_ADDRESSING_MODE.get(opcode);
    }
}
