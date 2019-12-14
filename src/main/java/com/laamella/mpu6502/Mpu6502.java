package com.laamella.mpu6502;

import static com.laamella.mpu6502.Mpu6502Specifications.*;
import static com.laamella.mpu6502.Mpu6502Specifications.AddressingMode.JAM;
import static com.laamella.mpu6502.Mpu6502Specifications.OPCODE_NR.*;
import static com.laamella.mpu6502.RunState.*;
import static java.lang.String.format;

/**
 * A simple 6502 emulator.
 * Run it by giving it a bus with something interesting on it,
 * then calling "step" until you're done.
 */
public final class Mpu6502 {
    private interface Ref {
        int get();

        void set(int value);
    }

    private class Pointer implements Ref {
        private final int address;

        Pointer(int address) {
            this.address = address;
        }

        @Override
        public int get() {
            return bus.read(address);
        }

        @Override
        public void set(int value) {
            bus.write(address, value);
        }

        @Override
        public String toString() {
            return format("mem[%04x] (= %02x)", address, bus.read(address));
        }
    }

    private Ref aRef = new Ref() {
        @Override
        public int get() {
            return a;
        }

        @Override
        public void set(int value) {
            a = value;
        }

        @Override
        public String toString() {
            return "A (= " + a + ")";
        }
    };

    private Ref xRef = new Ref() {
        @Override
        public int get() {
            return x;
        }

        @Override
        public void set(int value) {
            x = value;
        }

        @Override
        public String toString() {
            return "X (= " + x + ")";
        }
    };

    private Ref yRef = new Ref() {
        @Override
        public int get() {
            return y;
        }

        @Override
        public void set(int value) {
            y = value;
        }

        @Override
        public String toString() {
            return "Y (= " + y + ")";
        }
    };

    private Ref spRef = new Ref() {
        @Override
        public int get() {
            return sp;
        }

        @Override
        public void set(int value) {
            sp = value;
        }

        @Override
        public String toString() {
            return "SP (= " + sp + ")";
        }
    };

    public int a = 0;
    public int x = 0;
    public int y = 0;
    public int flags = 0;
    public int sp = 0xff;
    public int pc = 0;
    public int cycles = 0;

    public final Bus bus;

    public Mpu6502(Bus bus) {
        this.bus = bus;
    }

    private boolean isSet(int val, int mask) {
        return (val & mask) > 0;
    }

    private boolean zero(int val) {
        return val == 0;
    }

    private Pointer lo() {
        return new Pointer(pc);
    }

    private Pointer hi() {
        return new Pointer(pc + 1);
    }

    private int fetch() {
        return bus.read(pc++);
    }

    public void push(int data) {
        bus.write(0x100 + sp, data);
        sp = (sp - 1) & 0xff;
    }

    public int pop() {
        sp = (sp + 1) & 0xff;
        return bus.read(0x100 + sp);
    }

    private Pointer immediate() {
        return lo();
    }

    private Pointer absolute() {
        return new Pointer(lo().get() | (hi().get() << 8));
    }

    private Pointer absoluteX() {
        return new Pointer(((lo().get() | (hi().get() << 8)) + x) & 0xffff);
    }

    private Pointer absoluteY() {
        return new Pointer(((lo().get() | (hi().get() << 8)) + y) & 0xffff);
    }

    private Pointer zeropage() {
        return new Pointer(lo().get() & 0xff);
    }

    private Pointer zeropageX() {
        return new Pointer((lo().get() + x) & 0xff);
    }

    private Pointer zeropageY() {
        return new Pointer((lo().get() + y) & 0xff);
    }

    private Pointer indirectX() {
        return new Pointer(bus.read((lo().get() + x) & 0xff) | (bus.read((lo().get() + x + 1) & 0xff) << 8));
    }

    private Pointer indirectY() {
        return new Pointer(((bus.read(lo().get()) | (bus.read(lo().get() + 1 & 0xff) << 8)) + y) & 0xffff);
    }

    private Pointer indirectZP() {
        return new Pointer(((bus.read(lo().get()) | (bus.read((lo().get() + 1) & 0xff) << 8))) & 0xffff);
    }

    private int evalPagecrossing(Pointer baseaddr, Pointer realaddr) {
        return isSet(((baseaddr.address) ^ (realaddr.address)), 0xff00) ? 1 : 0;
    }

    private int evalPagecrossingAbsoluteX() {
        return evalPagecrossing(absolute(), absoluteX());
    }

    private int evalPagecrossingAbsoluteY() {
        return evalPagecrossing(absolute(), absoluteY());
    }

    private int evalPagecrossingIndirectY() {
        return evalPagecrossing(indirectZP(), indirectY());
    }

    private Pointer ref(int address) {
        return new Pointer(address);
    }

    private void branch() {
        ++cycles;
        int temp = fetch();
        if (temp < 0x80) {
            cycles += evalPagecrossing(ref(pc), ref(pc + temp));
            pc += temp;
        } else {
            cycles += evalPagecrossing(ref(pc), ref(pc + temp - 0x100));
            pc += temp - 0x100;
        }
    }

    private void assignAndSetFlags(Ref ref, Ref dataRef) {
        assignAndSetFlags(ref, dataRef.get());
    }

    private void assignAndSetFlags(Ref ref, int data) {
        data &= 0xff;
        ref.set(data);
        setFlags(data);
    }

    private void setFlags(int data) {
        if (zero(data))
            flags = (flags & ~FLAG_MASK.NEGATIVE) | FLAG_MASK.ZERO;
        else
            flags = (flags & ~(FLAG_MASK.NEGATIVE | FLAG_MASK.ZERO)) |
                    ((data) & FLAG_MASK.NEGATIVE);
    }

    private void ADC(Ref ref) {
        int data = ref.get();
        int temp;
        if (isSet(flags, FLAG_MASK.DECIMAL)) {
            temp = (a & 0xf) + (data & 0xf) + (flags & FLAG_MASK.CARRY);
            if (temp > 0x9)
                temp += 0x6;
            if (temp <= 0x0f)
                temp = (temp & 0xf) + (a & 0xf0) + (data & 0xf0);
            else
                temp = (temp & 0xf) + (a & 0xf0) + (data & 0xf0) + 0x10;
            if (!isSet(a + data + (flags & FLAG_MASK.CARRY), 0xff))
                flags |= FLAG_MASK.ZERO;
            else
                flags &= ~FLAG_MASK.ZERO;
            if (isSet(temp, 0x80))
                flags |= FLAG_MASK.NEGATIVE;
            else
                flags &= ~FLAG_MASK.NEGATIVE;
            if (isSet((a ^ temp), 0x80) && !isSet((a ^ data), 0x80))
                flags |= FLAG_MASK.OVERFLOW;
            else
                flags &= ~FLAG_MASK.OVERFLOW;
            if ((temp & 0x1f0) > 0x90) temp += 0x60;
            if ((temp & 0xff0) > 0xf0)
                flags |= FLAG_MASK.CARRY;
            else
                flags &= ~FLAG_MASK.CARRY;
        } else {
            temp = data + a + (flags & FLAG_MASK.CARRY);
            setFlags(temp & 0xff);
            if (!isSet((a ^ data), 0x80) && isSet((a ^ temp), 0x80))
                flags |= FLAG_MASK.OVERFLOW;
            else
                flags &= ~FLAG_MASK.OVERFLOW;
            if (temp > 0xff)
                flags |= FLAG_MASK.CARRY;
            else
                flags &= ~FLAG_MASK.CARRY;
        }
        a = temp & 0xff;
    }

    private void SBC(Ref dataRef) {
        int data = dataRef.get();
        int temp = a - data - ((flags & FLAG_MASK.CARRY) ^ FLAG_MASK.CARRY);

        if (isSet(flags, FLAG_MASK.DECIMAL)) {
            int tempval2;
            tempval2 = (a & 0xf) - (data & 0xf) - ((flags & FLAG_MASK.CARRY) ^ FLAG_MASK.CARRY);
            if (isSet(tempval2, 0x10))
                tempval2 = ((tempval2 - 6) & 0xf) | ((a & 0xf0) - (data & 0xf0) - 0x10);
            else
                tempval2 = (tempval2 & 0xf) | ((a & 0xf0) - (data & 0xf0));
            if (isSet(tempval2, 0x100))
                tempval2 -= 0x60;
            if (temp < 0) {
                flags &= ~FLAG_MASK.CARRY;
            } else {
                flags |= FLAG_MASK.CARRY;
            }
            setFlags(temp & 0xff);
            if (isSet((a ^ temp), 0x80) && isSet((a ^ data), 0x80))
                flags |= FLAG_MASK.OVERFLOW;
            else
                flags &= ~FLAG_MASK.OVERFLOW;
            a = tempval2 & 0xff;
        } else {
            setFlags(temp & 0xff);
            if (temp < 0) {
                flags &= ~FLAG_MASK.CARRY;
            } else {
                flags |= FLAG_MASK.CARRY;
            }
            if (isSet((a ^ temp), 0x80) && isSet((a ^ data), 0x80))
                flags |= FLAG_MASK.OVERFLOW;
            else
                flags &= ~FLAG_MASK.OVERFLOW;
            a = temp & 0xff;
        }
    }

    private void CMP(Ref src, Ref data) {
        final int temp = (src.get() - data.get()) & 0xff;

        flags = (flags & ~(FLAG_MASK.CARRY | FLAG_MASK.NEGATIVE | FLAG_MASK.ZERO)) |
                (temp & FLAG_MASK.NEGATIVE);

        if (zero(temp)) flags |= FLAG_MASK.ZERO;
        if (src.get() >= data.get()) flags |= FLAG_MASK.CARRY;
    }

    private void ASL(Ref data) {
        int temp = data.get();
        temp <<= 1;
        if (isSet(temp, 0x100)) flags |= FLAG_MASK.CARRY;
        else flags &= ~FLAG_MASK.CARRY;

        assignAndSetFlags(data, temp);
    }

    private void LSR(Ref data) {
        int temp = data.get();
        if (isSet(temp, 1)) flags |= FLAG_MASK.CARRY;
        else flags &= ~FLAG_MASK.CARRY;
        temp >>= 1;
        assignAndSetFlags(data, temp);
    }

    private void ROL(Ref data) {
        int temp = data.get();
        temp <<= 1;
        if (isSet(flags, FLAG_MASK.CARRY)) temp |= 1;
        if (isSet(temp, 0x100)) flags |= FLAG_MASK.CARRY;
        else flags &= ~FLAG_MASK.CARRY;
        assignAndSetFlags(data, temp);
    }

    private void ROR(Ref data) {
        int temp = data.get();
        if (isSet(flags, FLAG_MASK.CARRY)) temp |= 0x100;
        if (isSet(temp, 1)) flags |= FLAG_MASK.CARRY;
        else flags &= ~FLAG_MASK.CARRY;
        temp >>= 1;
        assignAndSetFlags(data, temp);
    }

    private void DEC(Ref data) {
        int temp = data.get() - 1;
        assignAndSetFlags(data, temp);
    }

    private void INC(Ref data) {
        int temp = data.get() + 1;
        assignAndSetFlags(data, temp);
    }

    private void EOR(Ref data) {
        a ^= data.get();
        setFlags(a);
    }

    private void ORA(Ref data) {
        a |= data.get();
        setFlags(a);
    }

    private void AND(Ref data) {
        a &= data.get();
        setFlags(a);
    }

    private void BIT(Ref dataRef) {
        final int data = dataRef.get();
        flags = (flags & ~(FLAG_MASK.NEGATIVE | FLAG_MASK.OVERFLOW)) |
                (data & (FLAG_MASK.NEGATIVE | FLAG_MASK.OVERFLOW));
        if (!isSet(data, a)) flags |= FLAG_MASK.ZERO;
        else flags &= ~FLAG_MASK.ZERO;
    }

    public RunState step() {
        int op = fetch();

        cycles += CPU_CYCLES[op];
        if (ADDRESSING_MODE[op] == JAM) {
            return JAMMED;
        }
        switch (op) {
            case LAX_ZP_A7:
                assignAndSetFlags(aRef, zeropage().get());
                x = a;
                pc++;
                break;

            case LAX_ZPY_B7:
                assignAndSetFlags(aRef, zeropageY().get());
                x = a;
                pc++;
                break;

            case LAX_ABS_AF:
                assignAndSetFlags(aRef, absolute().get());
                x = a;
                pc += 2;
                break;

            case LAX_IZX_A3:
                assignAndSetFlags(aRef, indirectX().get());
                x = a;
                pc++;
                break;

            case LAX_IZY_B3:
                cycles += evalPagecrossingIndirectY();
                assignAndSetFlags(aRef, indirectY().get());
                x = a;
                pc++;
                break;

            case NOP_1A:
            case NOP_3A:
            case NOP_5A:
            case NOP_7A:
            case NOP_DA:
            case NOP_FA:
                break;

            case NOP_IMM_80:
            case NOP_IMM_82:
            case NOP_IMM_89:
            case NOP_IMM_C2:
            case NOP_IMM_E2:
            case NOP_ZP_04:
            case NOP_ZP_44:
            case NOP_ZP_64:
            case NOP_ZPX_14:
            case NOP_ZPX_34:
            case NOP_ZPX_54:
            case NOP_ZPX_74:
            case NOP_ZPX_D4:
            case NOP_ZPX_F4:
                pc++;
                break;

            case NOP_ABS_0C:
            case NOP_ABX_1C:
            case NOP_ABX_3C:
            case NOP_ABX_5C:
            case NOP_ABX_7C:
            case NOP_ABX_DC:
            case NOP_ABX_FC:
                cycles += evalPagecrossingAbsoluteX();
                pc += 2;
                break;

            case ADC_IMM:
                ADC(immediate());
                pc++;
                break;

            case ADC_ZP:
                ADC(zeropage());
                pc++;
                break;

            case ADC_ZPX:
                ADC(zeropageX());
                pc++;
                break;

            case ADC_ABS:
                ADC(absolute());
                pc += 2;
                break;

            case ADC_ABX:
                cycles += evalPagecrossingAbsoluteX();
                ADC(absoluteX());
                pc += 2;
                break;

            case ADC_ABY:
                cycles += evalPagecrossingAbsoluteY();
                ADC(absoluteY());
                pc += 2;
                break;

            case ADC_IZX:
                ADC(indirectX());
                pc++;
                break;

            case ADC_IZY:
                cycles += evalPagecrossingIndirectY();
                ADC(indirectY());
                pc++;
                break;

            case AND_IMM:
                AND(immediate());
                pc++;
                break;

            case AND_ZP:
                AND(zeropage());
                pc++;
                break;

            case AND_ZPX:
                AND(zeropageX());
                pc++;
                break;

            case AND_ABS:
                AND(absolute());
                pc += 2;
                break;

            case AND_ABX:
                cycles += evalPagecrossingAbsoluteX();
                AND(absoluteX());
                pc += 2;
                break;

            case AND_ABY:
                cycles += evalPagecrossingAbsoluteY();
                AND(absoluteY());
                pc += 2;
                break;

            case AND_IZX:
                AND(indirectX());
                pc++;
                break;

            case AND_IZY:
                cycles += evalPagecrossingIndirectY();
                AND(indirectY());
                pc++;
                break;

            case ASL:
                ASL(aRef);
                break;

            case ASL_ZP:
                ASL(zeropage());
                pc++;
                break;

            case ASL_ZPX:
                ASL(zeropageX());
                pc++;
                break;

            case ASL_ABS:
                ASL(absolute());
                pc += 2;
                break;

            case ASL_ABX:
                ASL(absoluteX());
                pc += 2;
                break;

            case BCC:
                if (!isSet(flags, FLAG_MASK.CARRY)) branch();
                else pc++;
                break;

            case BCS:
                if (isSet(flags, FLAG_MASK.CARRY)) branch();
                else pc++;
                break;

            case BEQ:
                if (isSet(flags, FLAG_MASK.ZERO)) branch();
                else pc++;
                break;

            case BIT_ZP:
                BIT(zeropage());
                pc++;
                break;

            case BIT_ABS:
                BIT(absolute());
                pc += 2;
                break;

            case BMI:
                if (isSet(flags, FLAG_MASK.NEGATIVE)) branch();
                else pc++;
                break;

            case BNE:
                if (!isSet(flags, FLAG_MASK.ZERO)) branch();
                else pc++;
                break;

            case BPL:
                if (!isSet(flags, FLAG_MASK.NEGATIVE)) branch();
                else pc++;
                break;

            case BVC:
                if (!isSet(flags, FLAG_MASK.OVERFLOW)) branch();
                else pc++;
                break;

            case BVS:
                if (isSet(flags, FLAG_MASK.OVERFLOW)) branch();
                else pc++;
                break;

            case CLC:
                flags &= ~FLAG_MASK.CARRY;
                break;

            case CLD:
                flags &= ~FLAG_MASK.DECIMAL;
                break;

            case CLI:
                flags &= ~FLAG_MASK.INTERRUPT;
                break;

            case CLV:
                flags &= ~FLAG_MASK.OVERFLOW;
                break;

            case CMP_IMM:
                CMP(aRef, immediate());
                pc++;
                break;

            case CMP_ZP:
                CMP(aRef, zeropage());
                pc++;
                break;

            case CMP_ZPX:
                CMP(aRef, zeropageX());
                pc++;
                break;

            case CMP_ABS:
                CMP(aRef, absolute());
                pc += 2;
                break;

            case CMP_ABX:
                cycles += evalPagecrossingAbsoluteX();
                CMP(aRef, absoluteX());
                pc += 2;
                break;

            case CMP_ABY:
                cycles += evalPagecrossingAbsoluteY();
                CMP(aRef, absoluteY());
                pc += 2;
                break;

            case CMP_IZX:
                CMP(aRef, indirectX());
                pc++;
                break;

            case CMP_IZY:
                cycles += evalPagecrossingIndirectY();
                CMP(aRef, indirectY());
                pc++;
                break;

            case CPX_IMM:
                CMP(xRef, immediate());
                pc++;
                break;

            case CPX_ZP:
                CMP(xRef, zeropage());
                pc++;
                break;

            case CPX_ABS:
                CMP(xRef, absolute());
                pc += 2;
                break;

            case CPY_IMM:
                CMP(yRef, immediate());
                pc++;
                break;

            case CPY_ZP:
                CMP(yRef, zeropage());
                pc++;
                break;

            case CPY_ABS:
                CMP(yRef, absolute());
                pc += 2;
                break;

            case DEC_ZP:
                DEC(zeropage());
                pc++;
                break;

            case DEC_ZPX:
                DEC(zeropageX());
                pc++;
                break;

            case DEC_ABS:
                DEC(absolute());
                pc += 2;
                break;

            case DEC_ABX:
                DEC(absoluteX());
                pc += 2;
                break;

            case DEX:
                x = (x - 1) & 0xff;
                setFlags(x);
                break;

            case DEY:
                y = (y - 1) & 0xff;
                setFlags(y);
                break;

            case EOR_IMM:
                EOR(immediate());
                pc++;
                break;

            case EOR_ZP:
                EOR(zeropage());
                pc++;
                break;

            case EOR_ZPX:
                EOR(zeropageX());
                pc++;
                break;

            case EOR_ABS:
                EOR(absolute());
                pc += 2;
                break;

            case EOR_ABX:
                cycles += evalPagecrossingAbsoluteX();
                EOR(absoluteX());
                pc += 2;
                break;

            case EOR_ABY:
                cycles += evalPagecrossingAbsoluteY();
                EOR(absoluteY());
                pc += 2;
                break;

            case EOR_IZX:
                EOR(indirectX());
                pc++;
                break;

            case EOR_IZY:
                cycles += evalPagecrossingIndirectY();
                EOR(indirectY());
                pc++;
                break;

            case INC_ZP:
                INC(zeropage());
                pc++;
                break;

            case INC_ZPX:
                INC(zeropageX());
                pc++;
                break;

            case INC_ABS:
                INC(absolute());
                pc += 2;
                break;

            case INC_ABX:
                INC(absoluteX());
                pc += 2;
                break;

            case INX:
                x = (x + 1) & 0xff;
                setFlags(x);
                break;

            case INY:
                y = (y + 1) & 0xff;
                setFlags(y);
                break;

            case JSR_ABS:
                push((pc + 1) >> 8);
                push((pc + 1) & 0xff);
                pc = absolute().address;
                break;

            case JMP_ABS:
                pc = absolute().address;
                break;

            case JMP_IND: {
                int adr = absolute().address;
                pc = (bus.read(adr) | (bus.read(((adr + 1) & 0xff) | (adr & 0xff00)) << 8));
            }
            break;

            case LDA_IMM:
                assignAndSetFlags(aRef, immediate());
                pc++;
                break;

            case LDA_ZP:
                assignAndSetFlags(aRef, zeropage());
                pc++;
                break;

            case LDA_ZPX:
                assignAndSetFlags(aRef, zeropageX());
                pc++;
                break;

            case LDA_ABS:
                assignAndSetFlags(aRef, absolute());
                pc += 2;
                break;

            case LDA_ABX:
                cycles += evalPagecrossingAbsoluteX();
                assignAndSetFlags(aRef, absoluteX());
                pc += 2;
                break;

            case LDA_ABY:
                cycles += evalPagecrossingAbsoluteY();
                assignAndSetFlags(aRef, absoluteY());
                pc += 2;
                break;

            case LDA_IZX:
                assignAndSetFlags(aRef, indirectX());
                pc++;
                break;

            case LDA_IZY:
                cycles += evalPagecrossingIndirectY();
                assignAndSetFlags(aRef, indirectY());
                pc++;
                break;

            case LDX_IMM:
                assignAndSetFlags(xRef, immediate());
                pc++;
                break;

            case LDX_ZP:
                assignAndSetFlags(xRef, zeropage());
                pc++;
                break;

            case LDX_ZPY:
                assignAndSetFlags(xRef, zeropageY());
                pc++;
                break;

            case LDX_ABS:
                assignAndSetFlags(xRef, absolute());
                pc += 2;
                break;

            case LDX_ABY:
                cycles += evalPagecrossingAbsoluteY();
                assignAndSetFlags(xRef, absoluteY());
                pc += 2;
                break;

            case LDY_IMM:
                assignAndSetFlags(yRef, immediate());
                pc++;
                break;

            case LDY_ZP:
                assignAndSetFlags(yRef, zeropage());
                pc++;
                break;

            case LDY_ZPX:
                assignAndSetFlags(yRef, zeropageX());
                pc++;
                break;

            case LDY_ABS:
                assignAndSetFlags(yRef, absolute());
                pc += 2;
                break;

            case LDY_ABX:
                cycles += evalPagecrossingAbsoluteX();
                assignAndSetFlags(yRef, absoluteX());
                pc += 2;
                break;

            case LSR:
                LSR(aRef);
                break;

            case LSR_ZP:
                LSR(zeropage());
                pc++;
                break;

            case LSR_ZPX:
                LSR(zeropageX());
                pc++;
                break;

            case LSR_ABS:
                LSR(absolute());
                pc += 2;
                break;

            case LSR_ABX:
                LSR(absoluteX());
                pc += 2;
                break;

            case NOP:
                break;

            case ORA_IMM:
                ORA(immediate());
                pc++;
                break;

            case ORA_ZP:
                ORA(zeropage());
                pc++;
                break;

            case ORA_ZPX:
                ORA(zeropageX());
                pc++;
                break;

            case ORA_ABS:
                ORA(absolute());
                pc += 2;
                break;

            case ORA_ABX:
                cycles += evalPagecrossingAbsoluteX();
                ORA(absoluteX());
                pc += 2;
                break;

            case ORA_ABY:
                cycles += evalPagecrossingAbsoluteY();
                ORA(absoluteY());
                pc += 2;
                break;

            case ORA_IZX:
                ORA(indirectX());
                pc++;
                break;

            case ORA_IZY:
                cycles += evalPagecrossingIndirectY();
                ORA(indirectY());
                pc++;
                break;

            case PHA:
                push(a);
                break;

            case PHP:
                push(flags | FLAG_MASK.BREAK | 0x20);
                break;

            case PLA:
                assignAndSetFlags(aRef, pop());
                break;

            case PLP:
                flags = pop();
                break;

            case ROL:
                ROL(aRef);
                break;

            case ROL_ZP:
                ROL(zeropage());
                pc++;
                break;

            case ROL_ZPX:
                ROL(zeropageX());
                pc++;
                break;

            case ROL_ABS:
                ROL(absolute());
                pc += 2;
                break;

            case ROL_ABX:
                ROL(absoluteX());
                pc += 2;
                break;

            case ROR:
                ROR(aRef);
                break;

            case ROR_ZP:
                ROR(zeropage());
                pc++;
                break;

            case ROR_ZPX:
                ROR(zeropageX());
                pc++;
                break;

            case ROR_ABS:
                ROR(absolute());
                pc += 2;
                break;

            case ROR_ABX:
                ROR(absoluteX());
                pc += 2;
                break;

            case RTI:
                flags = pop();
                pc = pop();
                pc |= pop() << 8;
                break;

            case RTS:
                pc = pop();
                pc |= pop() << 8;
                pc++;
                break;

            case SBC_IMM:
                SBC(immediate());
                pc++;
                break;

            case SBC_ZP:
                SBC(zeropage());
                pc++;
                break;

            case SBC_ZPX:
                SBC(zeropageX());
                pc++;
                break;

            case SBC_ABS:
                SBC(absolute());
                pc += 2;
                break;

            case SBC_ABX:
                cycles += evalPagecrossingAbsoluteX();
                SBC(absoluteX());
                pc += 2;
                break;

            case SBC_ABY:
                cycles += evalPagecrossingAbsoluteY();
                SBC(absoluteY());
                pc += 2;
                break;

            case SBC_IZX:
                SBC(indirectX());
                pc++;
                break;

            case SBC_IZY:
                cycles += evalPagecrossingIndirectY();
                SBC(indirectY());
                pc++;
                break;

            case SEC:
                flags |= FLAG_MASK.CARRY;
                break;

            case SED:
                flags |= FLAG_MASK.DECIMAL;
                break;

            case SEI:
                flags |= FLAG_MASK.INTERRUPT;
                break;

            case STA_ZP:
                zeropage().set(a);
                pc++;
                break;

            case STA_ZPX:
                zeropageX().set(a);
                pc++;
                break;

            case STA_ABS:
                absolute().set(a);
                pc += 2;
                break;

            case STA_ABX:
                absoluteX().set(a);
                pc += 2;
                break;

            case STA_ABY:
                absoluteY().set(a);
                pc += 2;
                break;

            case STA_IZX:
                indirectX().set(a);
                pc++;
                break;

            case STA_IZY:
                indirectY().set(a);
                pc++;
                break;

            case STX_ZP:
                zeropage().set(x);
                pc++;
                break;

            case STX_ZPY:
                zeropageY().set(x);
                pc++;
                break;

            case STX_ABS:
                absolute().set(x);
                pc += 2;
                break;

            case STY_ZP:
                zeropage().set(y);
                pc++;
                break;

            case STY_ZPX:
                zeropageX().set(y);
                pc++;
                break;

            case STY_ABS:
                absolute().set(y);
                pc += 2;
                break;

            case TAX:
                assignAndSetFlags(xRef, a);
                break;

            case TSX:
                assignAndSetFlags(xRef, sp);
                break;

            case TXA:
                assignAndSetFlags(aRef, x);
                break;

            case TXS:
                spRef.set(x);
                break;

            case TYA:
                assignAndSetFlags(aRef, y);
                break;

            case TAY:
                assignAndSetFlags(yRef, a);
                break;

            case BRK:
                brk();
                break;

            default:
                return UNIMPLEMENTED_OPCODE;
        }
        return RUNNING;
    }

    private void assertValidByteRange(int b) {
        if (b > 0xff) {
            throw new AssertionError();
        }
        if (b < 0x00) {
            throw new AssertionError();
        }
    }

    public void reset() {
        interruptSequence(Vector.RESET, false, false);
    }

    public void nmi() {
        interruptSequence(Vector.NMI, true, false);
    }

    public void irq() {
        interruptSequence(Vector.IRQ, true, false);
    }

    public void brk() {
        interruptSequence(Vector.BRK, true, true);
    }

    private void interruptSequence(int vectorAddress, boolean pushPcAndP, boolean setBreakFlag) {
        if (pushPcAndP) {
            int returnAddress = pc + 1;
            push(returnAddress >> 8);
            push(returnAddress & 0xff);
            int flagsToPush = flags | 0x20;
            if (setBreakFlag) {
                push(flagsToPush | FLAG_MASK.BREAK);
            } else {
                push(flagsToPush & ~FLAG_MASK.BREAK);
            }
        }
        flags |= FLAG_MASK.INTERRUPT;
        pc = bus.readWord(vectorAddress);
    }
}

