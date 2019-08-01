package com.tavisca.workshops.Assignment1.Solution;

import java.io.BufferedInputStream;
import java.io.IOException;

public class StreamReader {
    BufferedInputStream inStream;

    StreamReader(BufferedInputStream inStream) {
        this.inStream = inStream;
    }

    public String readDataFromStream() throws IOException {
        byte[] streamData = new byte[this.inStream.available()];
        this.inStream.read(streamData);
        return (new String(streamData));
    }
}
