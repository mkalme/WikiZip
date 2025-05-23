package com.wikizip.inflate.symbol;

import com.wikizip.inflate.utilities.writer.Writer;

public class SymbolHelper {
    private SymbolHelper(){}

    public static boolean isLiteral(short symbol)
    {
        return symbol < 256;
    }

    public static boolean isEndOfBlock(short symbol)
    {
        return symbol == 256;
    }

    public static void readBackReference(int length, int distance, Writer output)
    {
        while (length-- > 0)
        {
            output.write(output.getPrevValue(distance));
        }
    }

    public static short[] createRange(int from, int toInclusive)
    {
        short[] output = new short[toInclusive - from + 1];

        for (int i = 0; i < output.length; i++)
        {
            output[i] = (short)(from + i);
        }

        return output;
    }
}
