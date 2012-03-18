package com.fly.db.util;

import com.fly.db.DBManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Sql文件装载器，可以从文件中读取sql语句，执行中的SQL语句
 *
 * @author weijiancai
 */
public class SqlFileLoader {
    public static final char DIVIDE_CHAR = ';';
    /**
     * 单行注释列表
     */
    public List<String> singleLineCommentList;
    /**
     * 多行注释Map
     */
    public Map<String, String> multiLineCommentMap;
    
    public String[] sqlStatements;

    public SqlFileLoader(URL resource) {
        try {
            load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(URL resource) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(resource.getFile())));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                // 如果是单行注释，继续
                /*for (String comment : singleLineCommentList) {
                    if (line.startsWith(comment)) {
    
                    }
                }*/
                sb.append(line);
            }
            sqlStatements = sb.toString().split(";");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    public String[] getSqlStatements() {
        return sqlStatements;
    }

    public void setSqlStatements(String[] sqlStatements) {
        this.sqlStatements = sqlStatements;
    }
    
    public void execute() {
        DBManager manager = new DBManager();
        Connection conn = null;
        try {
            conn = DBManager.getDataSource("sys").getConn();
            Statement stmt = conn.createStatement();
            for (String sql : sqlStatements) {
                System.out.println("Execute: 【" + sql + "】");
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
