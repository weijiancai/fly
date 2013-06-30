package com.fly.base.process;

import com.fly.base.util.IOUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BaseProcess implements IProcess {
    private ProcessBuilder builder;
    private Process process;
    private ProcessStatus status;
    private IOSCommand command;

    public BaseProcess(IOSCommand command) {
        this.command = command;
        builder = new ProcessBuilder(command.getCommandLines());
        status = ProcessStatus.NOT_START;
    }

    @Override
    public void kill() {
        process.destroy();
    }

    @Override
    public void start() throws IOException {
        process = builder.start();
        status = ProcessStatus.RUNNING;
    }

    @Override
    public void waitFor() {
        try {
            int i = process.waitFor();
            if(i == 0) {
                status = ProcessStatus.EXIT;
            } else {
                status = ProcessStatus.UNKNOWN;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            status = ProcessStatus.INTERRUPTED;
        }
    }

    @Override
    public ProcessStatus getStatus() {
        return status;
    }

    @Override
    public OutputStream getOutputStream() {
        return process.getOutputStream();
    }

    @Override
    public List<String> getErrorLines() {
        try {
            return IOUtil.read(process.getErrorStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    @Override
    public List<String> getOutputLines() {
        try {
            return IOUtil.read(process.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    @Override
    public IOSCommand getCommand() {
        return command;
    }

    @Override
    public void setCommand(IOSCommand command) {
        this.command = command;
    }

    @Override
    public File getDirectory() {
        return builder.directory();
    }

    @Override
    public void setDirectory(File directory) {
        builder.directory(directory);
    }

    @Override
    public Map<String, String> getEnvironment() {
        return builder.environment();
    }

    @Override
    public boolean isRedirectErrorStream() {
        return builder.redirectErrorStream();
    }

    @Override
    public void setRedirectErrorStream(boolean isRedirectErrorStream) {
        builder.redirectErrorStream(isRedirectErrorStream);
    }
}
