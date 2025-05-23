package com.wikizip.inflate.symbol;

public class DistanceSymbol {
    public int baseDistance;
    public byte offsetBits;

    public static DistanceSymbol[] table = new DistanceSymbol[]
    {
        new DistanceSymbol(1, (byte)0),
                new DistanceSymbol(2, (byte)0),
                new DistanceSymbol(3, (byte)0),
                new DistanceSymbol(4, (byte)0),
                new DistanceSymbol(5, (byte)1),
                new DistanceSymbol(7, (byte)1),
                new DistanceSymbol(9, (byte)2),
                new DistanceSymbol(13, (byte)2),
                new DistanceSymbol(17, (byte)3),
                new DistanceSymbol(25, (byte)3),
                new DistanceSymbol(33, (byte)4),
                new DistanceSymbol(49, (byte)4),
                new DistanceSymbol(65, (byte)5),
                new DistanceSymbol(97, (byte)5),
                new DistanceSymbol(129, (byte)6),
                new DistanceSymbol(193, (byte)6),
                new DistanceSymbol(257, (byte)7),
                new DistanceSymbol(385, (byte)7),
                new DistanceSymbol(513, (byte)8),
                new DistanceSymbol(769, (byte)8),
                new DistanceSymbol(1025, (byte)9),
                new DistanceSymbol(1537, (byte)9),
                new DistanceSymbol(2049, (byte)10),
                new DistanceSymbol(3073, (byte)10),
                new DistanceSymbol(4097, (byte)11),
                new DistanceSymbol(6145, (byte)11),
                new DistanceSymbol(8193, (byte)12),
                new DistanceSymbol(12289, (byte)12),
                new DistanceSymbol(16385, (byte)13),
                new DistanceSymbol(24577, (byte)13),
    };

    public DistanceSymbol(int baseDistance, byte offsetBits)
    {
        this.baseDistance = baseDistance;
        this.offsetBits = offsetBits;
    }

    public int getDistanceFromOffset(int offset)
    {
        return baseDistance + offset;
    }
}
