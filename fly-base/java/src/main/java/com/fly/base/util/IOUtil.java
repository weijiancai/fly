package com.fly.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class IOUtil {
    public static List<String> read(InputStream is) throws IOException {
        List<String> result = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        String line;
        while ((line = br.readLine()) != null) {
            result.add(line);
        }
        return result;
    }
}
