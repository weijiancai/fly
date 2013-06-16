package com.meteorite.dbtools.idea.common.options.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.ui.DocumentAdapter;
import com.meteorite.dbtools.idea.browser.ui.DBNFormImpl;
import com.meteorite.dbtools.idea.common.options.Configuration;
import com.meteorite.dbtools.idea.common.ui.list.CheckBoxList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public abstract class ConfigurationEditorForm<E extends Configuration> extends DBNFormImpl {
    private ItemListener itemListener;
    private ActionListener actionListener;
    private DocumentListener documentListener;
    private TableModelListener tableModelListener;
    private E configuration;

    public ConfigurationEditorForm(E configuration) {
        this.configuration = configuration;
    }

    public E getConfiguration() {
        return configuration;
    }

    @Override
    public JComponent getComponent() {
        return null;
    }

    public abstract void applyChanges() throws ConfigurationException;
    public abstract void resetChanges();

    protected DocumentListener createDocumentListener() {
        return new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                getConfiguration().setModified(true);
            }
        };
    }

    protected ActionListener createActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getConfiguration().setModified(true);
            }
        };
    }

    protected ItemListener createItemListener() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                getConfiguration().setModified(true);
            }
        };
    }

    protected TableModelListener createTableModelListener() {
        return new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                getConfiguration().setModified(true);
            }
        };
    }

    protected void registerComponent(JComponent... components) {
        for (JComponent component : components) {
            registerComponent(component);
        }
    }

    protected void registerComponent(JComponent component) {
        if (component instanceof AbstractButton) {
            AbstractButton abstractButton = (AbstractButton) component;
            if (actionListener == null) {
                actionListener = createActionListener();
            }
            abstractButton.addActionListener(actionListener);
        } else if (component instanceof CheckBoxList) {
            CheckBoxList checkBoxList = (CheckBoxList) component;
            if (actionListener == null) actionListener = createActionListener();
            checkBoxList.addActionListener(actionListener);
        } else if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            if (documentListener == null) documentListener = createDocumentListener();
            textField.getDocument().addDocumentListener(documentListener);
        } else if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            if (itemListener == null) itemListener = createItemListener();
            comboBox.addItemListener(itemListener);
        } else if (component instanceof JTable) {
            JTable table = (JTable) component;
            if (tableModelListener == null) tableModelListener = createTableModelListener();
            table.getModel().addTableModelListener(tableModelListener);
        } else {
            for (Component childComponent : component.getComponents()) {
                if (childComponent instanceof JComponent) {
                    registerComponent((JComponent) childComponent);
                }
            }
        }
    }

    public void focus() {}

    public void dispose() {
        super.dispose();
        configuration = null;
    }
}
