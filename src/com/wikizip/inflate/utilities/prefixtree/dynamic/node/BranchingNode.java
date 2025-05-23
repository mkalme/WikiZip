package com.wikizip.inflate.utilities.prefixtree.dynamic.node;

import com.wikizip.inflate.utilities.BitReadOnlyStream;

public class BranchingNode implements Node {
    public Node one;
    public Node zero;

    @Override
    public short read(BitReadOnlyStream input) {
        boolean bit = input.readNextBit();

        if(!bit) return zero != null ? zero.read(input) : Short.MIN_VALUE;
        return one != null ? one.read(input) : Short.MIN_VALUE;
    }

    @Override
    public void addValue(short value, short code, int index) {
        int bits = readNBits(code, index);

        Node node;
        if(index == 0) {
            node = new ValueNode();

            if (bits == 0) zero = node;
            else one = node;
        }else{
            if(bits == 0){
                if(zero == null) zero = new BranchingNode();
                node = zero;
            }else{
                if(one == null) one = new BranchingNode();
                node = one;
            }
        }

        node.addValue(value, code, --index);
    }

    private static int readNBits(short value, int index)
    {
        int moveBy = 15 - index;
        return Short.toUnsignedInt((short)(value << moveBy)) >> (15);
    }
}
