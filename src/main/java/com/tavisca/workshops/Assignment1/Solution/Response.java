package com.tavisca.workshops.Assignment1.Solution;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Response {
    private String httpResponse;

    Response(String protocolWithVersion, String responseStatusCode,
             String serverDescription, String responseDateAndTime,
             String contentType, String contentLength, String fileContents) {
        this.httpResponse = protocolWithVersion + " " + responseStatusCode + "\n" +
                "Server: " + serverDescription + "\n" + "Date: " + responseDateAndTime + "\n" +
                "Content-type: " + contentType + "\n" + "Content-length: " + contentLength + "\n" +
                "\n\n" + fileContents;
    }

    public void sendResponse(BufferedOutputStream outStream) throws IOException {
        outStream.write(httpResponse.getBytes("UTF-8"));
        outStream.flush();
    }
}
