package com.wikizip.inflate.utilities;

public class BitReadOnlyStream {
    public byte[] buffer;
    public int position;
    public int positionInByte;

    public BitReadOnlyStream(byte[] buffer, int position, int positionInByte){
        this.buffer = buffer;
        this.position = position;
        this.positionInByte = positionInByte;
    }

    public void advanceToNearestFullByte(){
        if(positionInByte == 0) return;

        position++;
        positionInByte = 0;
    }

    public void incrementPositionInsideByte(int value)
    {
        positionInByte += value;
        if (positionInByte >= 8)
        {
            position += positionInByte >> 3;
            positionInByte &= 7;
        }
    }

    public byte readCurrentFullByte()
    {
        return buffer[position++];
    }

    public int readNextBits(int length)
    {
        int output = 0, offset = 0;

        while (length > 0)
        {
            int read = Math.min(8 - positionInByte, length);

            output |= readNBits(buffer[position], positionInByte, read) << offset;
            offset += read;
            length -= read;

            incrementPositionInsideByte(read);
        }

        return output;
    }

    public int readNextBitsReversed(int length)
    {
        return reverseBits(readNextBits(length), length);
    }

    public boolean readNextBit()
    {
        int bit = readNBits(buffer[position], positionInByte, 1);
        incrementPositionInsideByte(1);

        return bit == 1;
    }

    public short readInt16()
    {
        int b1 = readNextBits(8), b2 = readNextBits(8);
        return (short)(b2 << 8 | b1);
    }

    public int reverseBits(int input, int kBits)
    {
        int output = 0;
        for (int i = 0; i < kBits; i++)
        {
            output |= (input & (1 << i)) != 0 ? 1 << (kBits - 1 - i) : 0;
        }

        return output;
    }

    public boolean isEnd()
    {
        if (position > buffer.length - 1) return true;
        return position == buffer.length - 1 && positionInByte == 8;
    }

    private static int readNBits(byte value, int index, int count)
    {
        int moveBy = 8 - count - index;
        return Byte.toUnsignedInt((byte)(value << moveBy)) >> (8 - count);
    }
}
