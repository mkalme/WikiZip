package com.wikizip.inflate.block;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.writer.Writer;

public class StoreBlock implements DeflateBlock {
    @Override
    public int decompress(BitReadOnlyStream input, Writer output) {
        input.advanceToNearestFullByte();

        short length = input.readInt16();
        input.incrementPositionInsideByte(16);
        output.write(input.buffer, input.position, length);

        input.position += length;

        return length;
    }
}
