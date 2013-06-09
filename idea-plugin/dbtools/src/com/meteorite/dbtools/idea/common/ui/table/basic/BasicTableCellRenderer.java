package com.meteorite.dbtools.idea.common.ui.table.basic;

import com.intellij.ui.ColoredTableCellRenderer;
import com.meteorite.dbtools.idea.common.ui.table.sortable.SortableTable;
import com.meteorite.dbtools.idea.data.editor.color.DataGridTextAttributes;

import javax.swing.*;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class BasicTableCellRenderer extends ColoredTableCellRenderer {
    @Override
    protected void customizeCellRenderer(JTable table, Object value, boolean selected, boolean hasFocus, int row, int column) {
        DataGridTextAttributes configTextAttributes = ((BasicTable)table).getConfigTextAttributes();
        SortableTable sortableTable = (SortableTable)table;
    }
}
