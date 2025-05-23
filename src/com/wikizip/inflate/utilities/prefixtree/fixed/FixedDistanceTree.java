package com.wikizip.inflate.utilities.prefixtree.fixed;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.prefixtree.PrefixCodeTree;

public class FixedDistanceTree implements PrefixCodeTree {
    private static final FixedDistanceTree singleton = new FixedDistanceTree();
    public static FixedDistanceTree getSingleton(){
        return singleton;
    }

    @Override
    public short read(BitReadOnlyStream input) {
        return (short)input.readNextBitsReversed(5);
    }
}
