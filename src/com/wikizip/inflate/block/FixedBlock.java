package com.wikizip.inflate.block;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.prefixtree.fixed.FixedDistanceTree;
import com.wikizip.inflate.utilities.prefixtree.fixed.FixedHuffmanTree;
import com.wikizip.inflate.utilities.writer.Writer;

public class FixedBlock extends PrefixEncodedBlock {
    @Override
    public int decompress(BitReadOnlyStream input, Writer output) {
        return readBlock(input, output, FixedHuffmanTree.getSingleton(), FixedDistanceTree.getSingleton());
    }
}
