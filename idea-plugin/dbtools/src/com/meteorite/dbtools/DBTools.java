package com.meteorite.dbtools;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class DBTools implements ApplicationComponent {
    public DBTools() {
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "DBTools";
    }
}
