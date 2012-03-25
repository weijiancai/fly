package com.fly.sys;

/**
 * 持久化XML文件接口
 *
 * @author weijiancai
 */
public interface IPXML {
    /**
     * 获取XML文件路径
     *
     * @return 返回XML文件路径
     */
    String getFilePath();

    /**
     * 获取XPath
     *
     * @return 返回XPath
     */
    String getXPath();

    /**
     * 是否同步数据，如果是，则将数据的变动写入到xml文件中
     *
     * @return true 需要同步数据， false不需要
     */
    boolean isSync();
}
