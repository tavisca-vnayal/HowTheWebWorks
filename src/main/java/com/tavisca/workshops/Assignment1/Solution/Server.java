package com.tavisca.workshops.Assignment1.Solution;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        LogsWriter logsWriter = new LogsWriter("Server");
        ServerSocket serverSocket = new ServerSocket(80);
        logsWriter.writeLog("Server is listening on port " + serverSocket.getLocalPort());

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                logsWriter.writeLog("A new Client is Connected : " + socket);

                BufferedInputStream inStream = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream outStream = new BufferedOutputStream(socket.getOutputStream());

                Thread client = new ClientHandler(socket,inStream,outStream);
                logsWriter.writeLog("New Client Thread Started");
                client.start();
            } catch (Exception e) {
                socket.close();
                e.printStackTrace();
            }
        }
    }
}
