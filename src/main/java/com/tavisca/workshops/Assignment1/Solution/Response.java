package com.tavisca.workshops.Assignment1.Solution;

import java.io.*;
import java.util.Date;

public class Response {
    private String httpResponseHeader;

    private String protocolWithVersion;
    private String responseStatusCode;
    private String serverDescription;
    private String responseDateAndTime;
    private String contentType;

    private BufferedOutputStream outStream;
    private String resourceName;

    Response(String protocolWithVersion, String responseStatusCode,
             String serverDescription, String resourceName, BufferedOutputStream outStream) {
        this.protocolWithVersion = protocolWithVersion;
        this.responseStatusCode = responseStatusCode;
        this.serverDescription = serverDescription;
        this.responseDateAndTime = (new Date()).toString();
        this.contentType = (new ContentReader()).getContentType(resourceName);
        this.resourceName = resourceName;
        this.outStream = outStream;
    }

    public void generateResponseHeader() {
        this.httpResponseHeader = protocolWithVersion + " " + responseStatusCode + "\n" +
                "Server: " + serverDescription + "\n" + "Date: " + responseDateAndTime + "\n" +
                "Content-type: " + contentType + "\r\n\r\n";
    }

    public void sendResponse() throws IOException {
        if(this.contentType == "text/html") {
            this.sendResponseAsHtmlOrTextFiles();
        }
        else if(this.contentType == "image/jpeg") {
            this.sendResponseAsImageFiles();
        }
    }

    public void sendResponseAsHtmlOrTextFiles() throws IOException {
        this.generateResponseHeader();
        String contentsOfFile = (new ContentReader()).readContentsOfTextOrHtmlFile(this.resourceName);
        this.httpResponseHeader += contentsOfFile;
        outStream.write(this.httpResponseHeader.getBytes("UTF-8"));
        outStream.flush();
    }

    public void sendResponseAsImageFiles() {
        try{
            this.generateResponseHeader();
            BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(this.resourceName));
            outStream.write(httpResponseHeader.getBytes("UTF-8"));
            outStream.flush();

            byte[] buffer = new byte[inStream.available()];
            int bytesRead;
            while((bytesRead = inStream.read(buffer))!=-1) {
                outStream.write(buffer,0,bytesRead);
            }
            outStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
