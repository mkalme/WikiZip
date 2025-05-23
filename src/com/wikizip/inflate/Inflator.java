package com.wikizip.inflate;

import com.wikizip.inflate.block.*;
import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.writer.Writer;

public class Inflator {
    private final DeflateBlock storeBlock = new StoreBlock();
    private final DeflateBlock fixedBlock = new FixedBlock();
    private final DeflateBlock dynamicBlock =  new DynamicBlock();

    private final BlockType[] blockTypeValues = BlockType.values();

    public int decompress(BitReadOnlyStream input, Writer output)
    {
        int red = 0;

        while (!input.isEnd())
        {
            boolean isFinal = isFinal(input);

            BlockType blockType = blockTypeValues[input.readNextBits(2)];
            switch (blockType)
            {
                case STORE:
                    red += storeBlock.decompress(input, output);
                    break;
                case FIXED_CODE:
                    red += fixedBlock.decompress(input, output);
                    break;
                case DYNAMIC_CODE:
                    red += dynamicBlock.decompress(input, output);
                    break;
            }

            if (isFinal) break;
        }

        return red;
    }

    private static boolean isFinal(BitReadOnlyStream input)
    {
        return input.readNextBit();
    }
}
