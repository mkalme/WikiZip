package com.wikizip.inflate.block;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.prefixtree.dynamic.HuffmanTree;
import com.wikizip.inflate.utilities.prefixtree.dynamic.code.LiteralLength;
import com.wikizip.inflate.utilities.writer.Writer;

public class DynamicBlock extends PrefixEncodedBlock {
    private static final int[] codeLengthsForAlphabet = new int[] {
            16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15
    };

    @Override
    public int decompress(BitReadOnlyStream input, Writer output) {
        int hlit = input.readNextBits(5), hdist = input.readNextBits(5), hclen = input.readNextBits(4);

        HuffmanTree codeLengthTree = createCodeLengthHuffmanTree(input, hclen);
        HuffmanTree literalLengthTree = createHuffmanTreeFromLengthCodes(input, codeLengthTree, hlit + 257);
        HuffmanTree distanceLengthTree = createHuffmanTreeFromLengthCodes(input, codeLengthTree, hdist + 1);

        return readBlock(input, output, literalLengthTree, distanceLengthTree);
    }

    private HuffmanTree createCodeLengthHuffmanTree(BitReadOnlyStream input, int hclen)
    {
        LiteralLength[] codeLengths = new LiteralLength[hclen + 4];
        for (int i = 0; i < hclen + 4; i++)
        {
            codeLengths[i] = new LiteralLength((short)codeLengthsForAlphabet[i], (byte)input.readNextBits(3));
        }

        return HuffmanTree.fromLiteralLengths(codeLengths);
    }

    private static HuffmanTree createHuffmanTreeFromLengthCodes(BitReadOnlyStream input, HuffmanTree codeLengthHuffmanTree, int n)
    {
        byte[] lengthsOfLiterals = new byte[n];

        int index = 0;
        while (index < lengthsOfLiterals.length)
        {
            short a = codeLengthHuffmanTree.read(input);

            if (a < 16)
            {
                lengthsOfLiterals[index] = (byte)a;
                index++;
            }
            else if (a == 16)
            {
                int repeat = input.readNextBits(2) + 3;

                for (int i = 0; i < repeat; i++)
                {
                    lengthsOfLiterals[index + i] = lengthsOfLiterals[index - 1];
                }

                index += repeat;
            }
            else if (a == 17)
            {
                index += input.readNextBits(3) + 3;
            }
            else if (a == 18)
            {
                index += input.readNextBits(7) + 11;
            }
        }

        LiteralLength[] lc = new LiteralLength[lengthsOfLiterals.length];
        for (int i = 0; i < lengthsOfLiterals.length; i++)
        {
            lc[i] = new LiteralLength((short)i, lengthsOfLiterals[i]);
        }

        return HuffmanTree.fromLiteralLengths(lc);
    }
}
