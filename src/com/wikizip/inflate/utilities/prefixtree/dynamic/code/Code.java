package com.wikizip.inflate.utilities.prefixtree.dynamic.code;

public class Code {
    public short storedLiteral;
    public short actualCode;
    public byte length;

    public Code(short storedLiteral, short actualCode, byte length) {
        this.storedLiteral = storedLiteral;
        this.actualCode = actualCode;
        this.length = length;
    }
}
