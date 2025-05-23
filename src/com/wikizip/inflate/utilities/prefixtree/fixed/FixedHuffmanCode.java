package com.wikizip.inflate.utilities.prefixtree.fixed;

import com.wikizip.inflate.symbol.SymbolHelper;

public class FixedHuffmanCode {
    public byte bits;
    public short codeStart;
    public short[] symbols;

    public static FixedHuffmanCode[] codes = new FixedHuffmanCode[] {
        new FixedHuffmanCode((byte)8, (short)0b00110000, SymbolHelper.createRange(0, 143)),
        new FixedHuffmanCode((byte)9, (short)0b110010000, SymbolHelper.createRange(144, 255)),
        new FixedHuffmanCode((byte)7, (short)0b0000000, SymbolHelper.createRange(256, 279)),
        new FixedHuffmanCode((byte)8, (short)0b11000000, SymbolHelper.createRange(280, 287))
    };

    public FixedHuffmanCode(byte bits, short start, short[] symbols)
    {
        this.bits = bits;
        this.codeStart = start;
        this.symbols = symbols;
    }

    public boolean isSymbolWithinRange(int symbol)
    {
        return symbol >= codeStart && (symbol - codeStart) < symbols.length;
    }

    public short convertToValidSymbol(int invalidSymbol)
    {
        return symbols[invalidSymbol - codeStart];
    }
}
