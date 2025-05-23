package com.wikizip.inflate.utilities.prefixtree.fixed;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.prefixtree.PrefixCodeTree;

public class FixedHuffmanTree implements PrefixCodeTree {
    private static final FixedHuffmanTree _singleton = new FixedHuffmanTree();
    public static FixedHuffmanTree getSingleton(){
        return _singleton;
    }

    @Override
    public short read(BitReadOnlyStream input) {
        int startByte = input.position, startBit = input.positionInByte;

        for (int i = 0; i < FixedHuffmanCode.codes.length; i++)
        {
            FixedHuffmanCode code = FixedHuffmanCode.codes[i];

            int symbol = input.readNextBitsReversed(code.bits);
            if (!code.isSymbolWithinRange(symbol))
            {
                input.position = startByte;
                input.positionInByte = startBit;

                continue;
            }

            return code.convertToValidSymbol(symbol);
        }

        return Short.MIN_VALUE;
    }
}
