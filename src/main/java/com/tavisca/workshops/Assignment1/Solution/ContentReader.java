package com.tavisca.workshops.Assignment1.Solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContentReader {

    public String getContentType(String fileName) {
        String contentType = "";

        if(fileName.contains("html")) {
            contentType = "text/html";
        }
        else if(fileName.contains("JPG") || fileName.contains("ico") || fileName.contains("jpeg") || fileName.contains("png") || fileName.contains("jfif")) {
            contentType = "image/jpeg";
        }
        return contentType;
    }

    public String readContentsOfTextOrHtmlFile(String fileName) throws IOException {
        String contentsOfFile = "";
        String line;
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
