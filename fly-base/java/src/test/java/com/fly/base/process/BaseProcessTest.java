package com.fly.base.process;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BaseProcessTest {
    @Test
    public void testCmdProcess() throws IOException {
        String tasklist = "tasklist";
        String cmd = "cmd";
        String dir = "dir";
        String notepad = "notepad";
        OSCommand command = new OSCommand(cmd);
        BaseProcess process = new BaseProcess(command);
        process.start();


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        bw.write("dir\n");
        bw.flush();
        bw.close();
        System.out.println("InputStream.......................");
        for (String str : process.getOutputLines()) {
            System.out.println(str);
        }

        process.start();
        bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        bw.write("cd ..\n");
        bw.write("dir\n");
        bw.flush();
        bw.close();

        System.out.println("InputStream.......................");
        for (String str : process.getOutputLines()) {
            System.out.println(str);
        }

        System.out.println("Error.......................");
        for (String str : process.getErrorLines()) {
            System.out.println(str);
        }
        process.waitFor();

        for (Map.Entry<String, String> entry : process.getEnvironment().entrySet()) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }

        System.out.println("===================================");
        System.out.println(process.getDirectory().getAbsolutePath());

    }
}
