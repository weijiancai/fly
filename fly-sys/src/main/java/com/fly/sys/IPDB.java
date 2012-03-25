package com.fly.sys;

import java.util.Map;

/**
 * 持久化数据库接口
 *
 * @author weijiancai
 */
public interface IPDB {
    /**
     * 获得持久化数据Map
     *
     * @return 返回持久化数据Map
     */
    Map<String, Map<String, Object>> getPDBMap();
}
