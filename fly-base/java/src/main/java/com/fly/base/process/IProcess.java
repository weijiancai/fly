package com.fly.base.process;

import com.fly.base.service.IService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 进程接口信息
 *
 * @author wei_jc
 * @version 0.0.1
 */
public interface IProcess {
    /**
     * 杀掉进程
     */
    void kill();

    /**
     * 启动进程
     */
    void start() throws IOException;

    /**
     * 导致当前线程等待，如有必要，一直要等到由该Process对象表示的进程已经终止。
     */
    void waitFor();

    /**
     * 获得进程状态
     *
     * @return 返回进程状态
     */
    ProcessStatus getStatus();

    /**
     * 获取进程的输出信息
     *
     * @return 返回进程的输出信息
     */
    OutputStream getOutputStream();

    /**
     * 获取进程的错误信息
     *
     * @return 返回进程的错误信息
     */
    List<String> getErrorLines();

    /**
     * 获取进程的输入流
     *
     * @return 返回进程的输入流
     */
    List<String> getOutputLines();

    /**
     * 获取此进程的操作系统程序和参数。
     *
     * @return 返回此进程的操作系统程序和参数。
     */
    IOSCommand getCommand();

    /**
     * 设置此进程的操作系统程序和参数。
     */
    void setCommand(IOSCommand command);

    /**
     * 获取此进程的工作目录
     *
     * @return 返回此进程的工作目录
     */
    File getDirectory();

    /**
     * 设置此进程的工作目录
     *
     * @param directory 工作目录
     */
    void setDirectory(File directory);

    /**
     * 获取此进程环境的字符串映射视图
     *
     * @return 返回此进程环境的字符串映射视图
     */
    Map<String, String> getEnvironment();

    /**
     * 通知进程是否合并标准错误和标准输出
     *
     * @return 返回进程是否合并标准错误和标准输出
     */
    boolean isRedirectErrorStream();

    /**
     * 设置进程是否合并标准错误和标准输出
     *
     * @param isRedirectErrorStream 是否合并
     */
    void setRedirectErrorStream(boolean isRedirectErrorStream);
}
