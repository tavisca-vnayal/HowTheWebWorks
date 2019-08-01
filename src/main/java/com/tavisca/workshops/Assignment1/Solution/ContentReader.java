package com.tavisca.workshops.Assignment1.Solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContentReader {
    public String readContentsOfFile(String fileName) throws IOException {
        String contentsOfFile = "";
        String line = "";
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        while (true) {
            if((line = in.readLine())!=null) {
                contentsOfFile += line;
            }
            else {
                break;
            }
        }
        return contentsOfFile;
    }
}
