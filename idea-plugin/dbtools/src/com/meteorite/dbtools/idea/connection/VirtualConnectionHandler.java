package com.meteorite.dbtools.idea.connection;

import com.intellij.openapi.project.Project;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import com.meteorite.dbtools.idea.common.filter.Filter;
import com.meteorite.dbtools.idea.database.DatabaseInterfaceProvider;
import com.meteorite.dbtools.idea.navigation.psi.NavigationPsiCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class VirtualConnectionHandler implements ConnectionHandler {
    private String id;
    private String name;
    private DatabaseType databaseType;
    private Project project;
    private DatabaseInterfaceProvider interfaceProvider;
    private Map<String, String> properties = new HashMap<String, String>();
    private NavigationPsiCache psiCache = new NavigationPsiCache(this);

    public DatabaseType getDatabaseType() {return databaseType;}

    public Filter<BrowserTreeNode> getObjectFilter() {
        return null;
    }

    @Override
    public NavigationPsiCache getPsiCache() {
        return psiCache;
    }

    @Override
    public EnvironmentType getEnvironmentType() {
        return null;
    }

    public DBLanguageDialect getLanguageDialect(DBLanguage language) {
        return getInterfaceProvider().getLanguageDialect(language);
    }

    public Project getProject() {return project;}

    public boolean isActive() {
        return true;
    }
}
