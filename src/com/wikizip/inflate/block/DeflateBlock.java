package com.wikizip.inflate.block;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.writer.Writer;

public interface DeflateBlock {
    int decompress(BitReadOnlyStream input, Writer output);
}
