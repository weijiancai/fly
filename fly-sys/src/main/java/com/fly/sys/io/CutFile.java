package com.fly.sys.io;

import java.io.*;

/**
 * @author weijiancai
 * @since 1.0.0
 */
public class CutFile {
    public void read(String file) throws IOException {
        File bigFile = new File(file);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(bigFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(bis), 5 * 1024 * 1024);
        PrintWriter pw = new PrintWriter(new FileWriter(new File(bigFile.getParentFile(), bigFile.getName() + "_bak")));
        String line;
        int row = 1;
        while ((line = br.readLine()) != null) {
            System.out.println(row++);
            pw.write(line);
        }
        br.close();
        pw.close();
    }
}
