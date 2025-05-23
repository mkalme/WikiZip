package com.wikizip.inflate.block;

import com.wikizip.inflate.symbol.DistanceSymbol;
import com.wikizip.inflate.symbol.LengthSymbol;
import com.wikizip.inflate.symbol.SymbolHelper;
import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.prefixtree.PrefixCodeTree;
import com.wikizip.inflate.utilities.writer.Writer;

public abstract class PrefixEncodedBlock implements DeflateBlock{
    protected int readBlock(BitReadOnlyStream input, Writer output, PrefixCodeTree literalTree, PrefixCodeTree distanceTree){
        short symbol;
        int prevOutputIndex = output.getPosition();

        while (!SymbolHelper.isEndOfBlock(symbol = literalTree.read(input)))
        {
            if (SymbolHelper.isLiteral(symbol))
            {
                output.write((byte)symbol);
            }
            else
            {
                LengthSymbol lengthSymbol = LengthSymbol.getSymbol(symbol);
                int length = lengthSymbol.getLengthFromOffset(input.readNextBits(lengthSymbol.offsetBits));

                DistanceSymbol distanceSymbol = DistanceSymbol.table[distanceTree.read(input)];
                int distance = distanceSymbol.getDistanceFromOffset(input.readNextBits(distanceSymbol.offsetBits));

                SymbolHelper.readBackReference(length, distance, output);
            }
        }

        return output.getPosition() - prevOutputIndex;
    }

    @Override
    public abstract int decompress(BitReadOnlyStream input, Writer output);
}
