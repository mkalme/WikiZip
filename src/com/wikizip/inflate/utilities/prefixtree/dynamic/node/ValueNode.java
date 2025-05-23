package com.wikizip.inflate.utilities.prefixtree.dynamic.node;

import com.wikizip.inflate.utilities.BitReadOnlyStream;

public class ValueNode implements Node {
    public short value;

    @Override
    public short read(BitReadOnlyStream input) {
        return value;
    }

    @Override
    public void addValue(short value, short code, int index) {
        this.value = value;
    }
}
