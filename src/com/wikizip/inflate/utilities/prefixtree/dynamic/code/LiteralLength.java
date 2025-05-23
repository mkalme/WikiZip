package com.wikizip.inflate.utilities.prefixtree.dynamic.code;

public class LiteralLength {
    public short storedLiteral;
    public byte length;

    public LiteralLength(short storedLiteral, byte length) {
        this.storedLiteral = storedLiteral;
        this.length = length;
    }
}
