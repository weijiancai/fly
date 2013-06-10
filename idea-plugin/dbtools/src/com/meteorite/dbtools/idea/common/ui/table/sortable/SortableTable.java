package com.meteorite.dbtools.idea.common.ui.table.sortable;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.common.LogFactory;
import com.meteorite.dbtools.idea.common.ui.table.basic.BasicTable;
import com.meteorite.dbtools.idea.data.model.sortable.SortableTableModel;

import javax.swing.table.TableModel;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class SortableTable extends BasicTable {
    protected Logger logger = LogFactory.createLogger();

    public SortableTable(SortableTableModel dataModel, boolean enableSpeedSearch) {
        super(project, tableModel);
        this.logger = logger;
    }
}
