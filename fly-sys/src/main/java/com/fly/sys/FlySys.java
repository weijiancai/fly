package com.fly.sys;

import com.fly.db.DBManager;

/**
 * @author weijiancai
 */
public class FlySys {
    /**
     * 初始化系统
     */
    public void init() {
        // 1. 装载数据源
        DBManager.loadDataSource();
    }
}
