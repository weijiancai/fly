package com.meteorite.dbtools.idea.common.dispose;

import com.intellij.openapi.Disposable;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class DisposeUtil {
    public static void dispose(Disposable disposable) {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
