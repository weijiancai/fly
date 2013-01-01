package com.fly.base.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MultiJabberClient {
    static final int MAX_THREADS = 40;

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
        while (true) {
            if (JabberClientThread.threadCount() < MAX_THREADS) {
                new JabberClientThread(addr);
            }

            Thread.currentThread().sleep(100);
        }
    }
}
