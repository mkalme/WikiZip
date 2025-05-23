package com.wikizip;

import com.wikizip.inflate.Inflator;
import com.wikizip.inflate.utilities.BitReadOnlyStream;
import com.wikizip.inflate.utilities.writer.ArrayWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.Deflater;

public class Main {


    public static void main(String[] args) {
        int a = 0, b = 1, z;

        //if(a>b) z=a; else z=b;

        z = a>b ? a : b;

        System.out.println(z);
    }

    private static byte[] generateInput(){
        try {
            return Files.readAllBytes(Paths.get("D:\\Darbvirsma\\nativelog.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static byte[] compress(byte[] decompressedData){
        byte[] output = new byte[1024 * 1024];

        Deflater compresser = new Deflater();
        compresser.setLevel(Deflater.NO_COMPRESSION);
        compresser.setInput(decompressedData);
        compresser.finish();

        int compressedDataLength = compresser.deflate(output);
        compresser.end();

        return Arrays.copyOfRange(output, 0, compressedDataLength);
    }
    private static byte[] decompress(byte[] compressedData){
        Inflator d = new Inflator();

        BitReadOnlyStream stream = new BitReadOnlyStream(compressedData, 0, 0);
        ArrayWriter writer = new ArrayWriter(new byte[1024 * 1024], 0);

        int red = d.decompress(stream, writer);

        return Arrays.copyOfRange(writer.buffer, 0, red);
    }

    private static boolean validateDecompression(byte[] compressed, byte[] decompressed){
        if(compressed.length != decompressed.length) return false;

        for(int i = 0; i < compressed.length; i++){
            if(compressed[i] != decompressed[i]) return false;
        }

        return true;
    }
}
