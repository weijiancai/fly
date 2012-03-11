package com.fly.db;

import com.fly.common.util.FileUtil;
import com.fly.common.XML;
import com.fly.db.util.DataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库管理器
 *
 * @author weijiancai
 */
public class DBManager {
    private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

    public static void loadDataSource() {
        // 从classpath读取数据源配置文件datasource.xml
        try {
            XML xml = new XML(FileUtil.getFileFromClassPath("/datasource.xml"));
            dataSourceMap = xml.toMap("datasource", "name", DataSource.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DBManager() {

    }

    public static DataSource getDataSource(String dsName) {
        return dataSourceMap.get(dsName);
    }
}
