package com.fly.base.process;

import java.util.List;

/**
 * 命令行
 *
 * @author wei_jc
 * @version 0.0.1
 */
public interface IOSCommand {
    /**
     * 获取命令行列表
     *
     * @return 返回命令行列表
     */
    List<String> getCommandLines();

    /**
     * 设置命令行列表
     *
     * @param commandLines 命令行列表
     */
    void setCommandLines(List<String> commandLines);

    /**
     * 设置命令行列表
     *
     * @param commandLines 命令行列表
     */
    void setCommandLines(String... commandLines);
}
