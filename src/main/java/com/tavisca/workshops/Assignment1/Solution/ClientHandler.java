package com.tavisca.workshops.Assignment1.Solution;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientHandler extends Thread {

    final BufferedInputStream inStream;
    final BufferedOutputStream outStream;
    final Socket socket;

    static LogsWriter logsWriter;

    static {
        try {
            logsWriter = new LogsWriter("ClientHandler");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ClientHandler(Socket socket, BufferedInputStream inStream, BufferedOutputStream outStream) throws IOException {
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
    }

    @Override
    public void run() {

        try {
            String requestHeaderData = (new StreamReader(inStream).readDataFromStream());
            this.logsWriter.writeLog("Header Information : " + requestHeaderData);
            if (requestHeaderData.startsWith("GET")) {
                this.logsWriter.writeLog("<GET> Request Found");
                ResourceExtractor resourceExtractor = new ResourceExtractor();
                resourceExtractor.extractResourceFromHeader(requestHeaderData);
                String nameOfResourceRequestByClient = resourceExtractor.getResourceName();
                this.logsWriter.writeLog("Resource Request by Client : " + nameOfResourceRequestByClient);
                String responseStatusCode;
                if (resourceExtractor.isResourceAvailable()) {
                    responseStatusCode = "200 OK";
                    this.logsWriter.writeLog("Requested Resource Exist in the Server");
                } else {
                    responseStatusCode = "404 Not Found";
                    nameOfResourceRequestByClient = "error.html";
                    this.logsWriter.writeLog("Requested Resource DOES NOT Exist in the Server : 404 Not Found ");
                }
                Response response = new Response("HTTP/1.1",responseStatusCode,
                        "My Java HTTP Server : 1.0", nameOfResourceRequestByClient, outStream );
                response.sendResponse();
                this.logsWriter.writeLog("Response Sent to the Client");
            } else {
                this.logsWriter.writeLog("<NOT GET> Request Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

