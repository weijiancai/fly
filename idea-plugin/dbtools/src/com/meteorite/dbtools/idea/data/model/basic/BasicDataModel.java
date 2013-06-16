package com.meteorite.dbtools.idea.data.model.basic;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.data.model.DataModel;
import com.meteorite.dbtools.idea.data.model.DataModelRow;

import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class BasicDataModel<T extends DataModelRow> implements DataModel<T> {
    private Project project;

    public BasicDataModel(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    @Override
    public void dispose() {
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object getElementAt(int index) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}
