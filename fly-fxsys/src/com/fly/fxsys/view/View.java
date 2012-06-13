package com.fly.fxsys.view;

import java.util.Map;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public interface View {
    void initUI();

    void initUIData(Map<String, Object> data);
}
