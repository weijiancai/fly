package com.meteorite.dbtools.idea.common.options;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.meteorite.dbtools.idea.common.options.ui.ConfigurationEditorForm;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public abstract class Configuration<T extends ConfigurationEditorForm> extends ConfigurationUtil implements SearchableConfigurable, PersistentConfiguration {
    private T configurationEditorForm;
    private boolean isModified;

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return null;
    }

    @NotNull
    @Override
    public String getId() {
        return null;
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        configurationEditorForm = createConfigurationEditor();
        return configurationEditorForm == null ? null : configurationEditorForm.getComponent();
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configurationEditorForm != null && !configurationEditorForm.isDisposed()) {
            configurationEditorForm.applyChanges();
        }
        isModified = false;
    }

    @Override
    public void reset() {
        if (configurationEditorForm != null && !configurationEditorForm.isDisposed()) {
            configurationEditorForm.resetChanges();
        }
        isModified = false;
    }

    @Override
    public void disposeUIResources() {
        if (configurationEditorForm != null) {
            configurationEditorForm.dispose();
            configurationEditorForm = null;
        }
    }

    public Icon getIcon() {
        return null;
    }

    protected abstract T createConfigurationEditor();

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public boolean isModified() {
        return isModified;
    }

    public String getConfigElementName() {
        return null;
    }

    @Nullable
    public final T getSettingsEditor() {
        return configurationEditorForm;
    }
}
