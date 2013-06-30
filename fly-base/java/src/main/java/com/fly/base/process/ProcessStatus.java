package com.fly.base.process;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum ProcessStatus {
    /**
     * 已创建
     */
    CREATED,
    /**
     * 未启动
     */
    NOT_START,
    /**
     * 正在运行
     */
    RUNNING,
    /**
     * 正在结束进程
     */
    KILLING,
    /**
     * 正常退出
     */
    EXIT,
    /**
     * 当前线程在等待时被另一线程中断，则停止等待
     */
    INTERRUPTED,
    /**
     * 未知状态
     */
    UNKNOWN
}
