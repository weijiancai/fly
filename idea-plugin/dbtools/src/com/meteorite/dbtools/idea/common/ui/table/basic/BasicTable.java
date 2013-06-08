package com.meteorite.dbtools.idea.common.ui.table.basic;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.colors.EditorColorsListener;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.common.ui.table.DBNTable;
import com.meteorite.dbtools.idea.data.editor.color.DataGridTextAttributes;

import javax.swing.table.TableModel;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class BasicTable extends DBNTable implements EditorColorsListener, Disposable {
    protected DataGridTextAttributes configTextAttributes = new DataGridTextAttributes();
    protected BasicTableCellRenderer cellRenderer;

    public BasicTable(Project project, TableModel tableModel) {
        super(project, tableModel, true);
    }

    @Override
    public void globalSchemeChange(EditorColorsScheme scheme) {
    }

    @Override
    public void dispose() {
    }
}
