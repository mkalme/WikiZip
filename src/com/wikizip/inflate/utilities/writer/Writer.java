package com.wikizip.inflate.utilities.writer;

public interface Writer {
    int getPosition();

    byte getPrevValue(int back);

    void write(byte value);

    void write(byte[] array, int offset, int length);
}
