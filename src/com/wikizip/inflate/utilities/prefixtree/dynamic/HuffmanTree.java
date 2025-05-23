package com.wikizip.inflate.utilities.prefixtree.dynamic;

import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.prefixtree.PrefixCodeTree;
import com.wikizip.inflate.utilities.prefixtree.dynamic.code.Code;
import com.wikizip.inflate.utilities.prefixtree.dynamic.code.CodeLength;
import com.wikizip.inflate.utilities.prefixtree.dynamic.code.LiteralLength;
import com.wikizip.inflate.utilities.prefixtree.dynamic.node.BranchingNode;
import com.wikizip.inflate.utilities.prefixtree.dynamic.node.Node;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree implements PrefixCodeTree {
    private Node rootNode;

    public HuffmanTree(Node rootNode){
        this.rootNode = rootNode;
    }

    public static HuffmanTree fromLiteralLengths(LiteralLength[] literalLengths){
        CodeLength[] c = new CodeLength[16];
        for (int i = 0; i < c.length; i++)
        {
            c[i] = new CodeLength((short)i, new ArrayList<Short>());
        }

        for (int i = 0; i < literalLengths.length; i++)
        {
            LiteralLength l = literalLengths[i];
            c[l.length].literals.add(l.storedLiteral);
        }

        int ll = 0;
        for (int i = 0; i < c.length; i++)
        {
            Collections.sort(c[i].literals);
            if (c[i].length > 0 && c[i].literals.size() > 0) ll += c[i].literals.size();
        }

        Code[] huffmanTree = new Code[ll];
        fillCode(c, huffmanTree);

        BranchingNode rootNode = new BranchingNode();
        for (int i = 0; i < huffmanTree.length; i++)
        {
            Code code = huffmanTree[i];
            rootNode.addValue(code.storedLiteral, code.actualCode, code.length - 1);
        }

        return new HuffmanTree(rootNode);
    }

    private static void fillCode(CodeLength[] codeLengths, Code[] output)
    {
        short[] next_code = new short[16];

        int code = 0;
        for (int bits = 1; bits <= 15; bits++)
        {
            code = (code + codeLengths[bits - 1].literals.size()) << 1;
            next_code[bits] = (short)code;
        }

        int index = 0;
        for (int i = 0; i < codeLengths.length; i++)
        {
            CodeLength l = codeLengths[i];
            if (l.length == 0 || l.literals.size() == 0) continue;

            for (int j = 0; j < l.literals.size(); j++)
            {
                output[index++] = new Code(l.literals.get(j), next_code[i], (byte)i);
                next_code[i]++;
            }
        }
    }

    @Override
    public short read(BitReadOnlyStream input) {
        return rootNode.read(input);
    }
}
