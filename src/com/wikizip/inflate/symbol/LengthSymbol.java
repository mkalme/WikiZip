package com.wikizip.inflate.symbol;

public class LengthSymbol {
    public int baseLength;
    public byte offsetBits;

    public static short baseSymbol = 257;

    public static LengthSymbol[] table = new LengthSymbol[]
    {
        new LengthSymbol(3, (byte)0),
        new LengthSymbol(4, (byte)0),
        new LengthSymbol(5, (byte)0),
        new LengthSymbol(6, (byte)0),
        new LengthSymbol(7, (byte)0),
        new LengthSymbol(8, (byte)0),
        new LengthSymbol(9, (byte)0),
        new LengthSymbol(10, (byte)0),
        new LengthSymbol(11, (byte)1),
        new LengthSymbol(13, (byte)1),
        new LengthSymbol(15, (byte)1),
        new LengthSymbol(17, (byte)1),
        new LengthSymbol(19, (byte)2),
        new LengthSymbol(23, (byte)2),
        new LengthSymbol(27, (byte)2),
        new LengthSymbol(31, (byte)2),
        new LengthSymbol(35, (byte)3),
        new LengthSymbol(43, (byte)3),
        new LengthSymbol(51, (byte)3),
        new LengthSymbol(59, (byte)3),
        new LengthSymbol(67, (byte)4),
        new LengthSymbol(83, (byte)4),
        new LengthSymbol(99, (byte)4),
        new LengthSymbol(115, (byte)4),
        new LengthSymbol(131, (byte)5),
        new LengthSymbol(163, (byte)5),
        new LengthSymbol(195, (byte)5),
        new LengthSymbol(227, (byte)5),
        new LengthSymbol(258, (byte)0),
    };

    public LengthSymbol(int baseLength, byte offsetBits)
    {
        this.baseLength = baseLength;
        this.offsetBits = offsetBits;
    }

    public int getLengthFromOffset(int offset)
    {
        return baseLength + offset;
    }

    public static LengthSymbol getSymbol(short symbol)
    {
        return table[symbol - baseSymbol];
    }
}
