package com.fly.base.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class JabberClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private static int counter = 0;
    private int id = counter++;
    private static int threadCount = 0;

    public static int threadCount() {
        return threadCount;
    }

    public JabberClientThread(InetAddress address) {
        System.out.println("Making client " + id);
        threadCount++;
        try {
            socket = new Socket(address, MultiJaberServer.PORT);
        } catch (IOException e) {
            System.err.println("Socket failed");
            e.printStackTrace();
        }

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            start();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                System.err.println("Socket not closed");
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                out.println("Client " + id + ": " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } catch (IOException e) {
            System.err.println("IO Exception");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
                e.printStackTrace();
            }
            threadCount--;
        }
    }
}
