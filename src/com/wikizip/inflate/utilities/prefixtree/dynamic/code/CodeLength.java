package com.wikizip.inflate.utilities.prefixtree.dynamic.code;

import java.util.List;

public class CodeLength {
    public short length;
    public List<Short> literals;

    public CodeLength(short length, List<Short> literals) {
        this.length = length;
        this.literals = literals;
    }
}
