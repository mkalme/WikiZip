package com.wikizip.inflate.utilities.prefixtree;

import com.wikizip.inflate.utilities.BitReadOnlyStream;

public interface PrefixCodeTree {
    short read(BitReadOnlyStream input);
}
