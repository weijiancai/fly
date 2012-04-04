package com.fly.sys;

import com.fly.sys.config.SysInfo;
import com.fly.sys.db.DBManager;

/**
 * @author weijiancai
 */
public class FlySys {
    /**
     * 初始化系统
     */
    public void init() throws Exception {
        // 系统未初始化
        if (!SysInfo.isDbmsInit()) {
            // 1. 初始化DBMS
            DBManager.initDBMS();
            // 2. 导入ClassDef类信息
        }
    }
}
