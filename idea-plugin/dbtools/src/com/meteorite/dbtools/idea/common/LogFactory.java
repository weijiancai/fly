package com.meteorite.dbtools.idea.common;

import com.intellij.openapi.diagnostic.Logger;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class LogFactory {
    public static Logger createLogger() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        for (int i = 0; i < stackTraceElements.length; i++) {
            if (stackTraceElements[i].getMethodName().equals("createLogger")) {
                stackTraceElement = i + 1 < stackTraceElements.length ? stackTraceElements[i + 1] : stackTraceElements[i];
                break;
            }
        }

        String className = stackTraceElement.getClassName();
        return Logger.getInstance(className);
    }
}
