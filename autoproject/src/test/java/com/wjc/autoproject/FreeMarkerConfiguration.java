package com.wjc.autoproject;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

/**
 * @author weijiancai
 * @version 1.0
 */
public class FreeMarkerConfiguration extends Configuration {
    private static final FreeMarkerConfiguration instance = new FreeMarkerConfiguration();

    public FreeMarkerConfiguration() {
        super();
        this.setTemplateLoader(new ClassTemplateLoader(getClass(), "/tpl"));
        
    }

    public static FreeMarkerConfiguration getInstance() {
        return instance;
    }
}
