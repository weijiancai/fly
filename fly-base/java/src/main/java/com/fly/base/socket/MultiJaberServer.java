package com.fly.base.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MultiJaberServer {
    static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    new ServeOneJabber(socket);
                } catch (IOException e) {
                    socket.close();
                    e.printStackTrace();
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
