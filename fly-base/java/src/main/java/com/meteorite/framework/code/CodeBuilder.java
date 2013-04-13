package com.meteorite.framework.code;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author wei_jc
 * @version 0.0.1
 */
public class CodeBuilder {
    // 内存计数器map
    private static Map<String, Integer> memCount = new HashMap<String, Integer>();
    private static CodeBuilder instance;
    private static StringBuilder sb;

    private CodeBuilder() {
        sb = new StringBuilder();
    }

    public static CodeBuilder getInstance() {
        if (instance == null) {
            instance = new CodeBuilder();
        }
        sb = new StringBuilder();

        return instance;
    }

    /**
     * 添加固定的字符串值
     *
     * @param value
     * @return
     */
    public CodeBuilder addFixValue(String value) {
        sb.append(value);
        return instance;
    }
}
