package com.meteorite.dbtools.idea.common.ui.list;

import javax.swing.*;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface Selectable {
    Icon getIcon();
    String getName();
    String getError();
    boolean isSelected();
    boolean isMasterSelected();
    void setSelected(boolean enabled);
}
