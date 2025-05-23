package com.wikizip.inflate.utilities.writer;

public class ArrayWriter implements Writer {
    public byte[] buffer;
    public int position;

    public ArrayWriter(byte[] buffer, int position) {
        this.buffer = buffer;
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public byte getPrevValue(int back) {
        return buffer[position - back];
    }

    public void write(byte value){
        buffer[position++] = value;
    }

    public void write(byte[] array, int offset, int length){
        System.arraycopy(array, offset, buffer, position, length);
        position += length;
    }
}
