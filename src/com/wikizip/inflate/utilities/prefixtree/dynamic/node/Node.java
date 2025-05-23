package com.wikizip.inflate.utilities.prefixtree.dynamic.node;

import com.wikizip.inflate.utilities.BitReadOnlyStream;

public interface Node {
    short read(BitReadOnlyStream input);

    void addValue(short value, short code, int index);
}
