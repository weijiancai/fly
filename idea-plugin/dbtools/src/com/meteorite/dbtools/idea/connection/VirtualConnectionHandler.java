package com.meteorite.dbtools.idea.connection;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.io.socketConnection.ConnectionStatus;
import com.meteorite.dbtools.idea.browser.model.BrowserTreeNode;
import com.meteorite.dbtools.idea.common.environment.EnvironmentType;
import com.meteorite.dbtools.idea.common.filter.Filter;
import com.meteorite.dbtools.idea.connection.config.ConnectionSettings;
import com.meteorite.dbtools.idea.connection.transaction.UncommittedChangeBundle;
import com.meteorite.dbtools.idea.database.DatabaseInterfaceProvider;
import com.meteorite.dbtools.idea.language.common.DBLanguage;
import com.meteorite.dbtools.idea.language.common.DBLanguageDialect;
import com.meteorite.dbtools.idea.navigation.psi.NavigationPsiCache;
import com.meteorite.dbtools.idea.object.DBSchema;
import com.meteorite.dbtools.idea.object.common.DBObjectBundle;
import com.meteorite.dbtools.idea.vfs.SQLConsoleFile;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
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

    public VirtualConnectionHandler(String s, String s1, DatabaseType oracle, Project project) {

    }

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

    @Override
    public UncommittedChangeBundle getUncommittedChanges() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public int getIdleMinutes() {
        return 0;
    }

    public Project getProject() {return project;}

    @Override
    public Module getModule() {
        return null;
    }

    @Override
    public Connection getPoolConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getPoolConnection(DBSchema schema) throws SQLException {
        return null;
    }

    @Override
    public Connection getStandaloneConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getStandaloneConnection(DBSchema schema) throws SQLException {
        return null;
    }

    @Override
    public void freePoolConnection(Connection connection) {
    }

    @Override
    public void closePoolConnection(Connection connection) {
    }

    @Override
    public ConnectionSettings getSettings() {
        return null;
    }

    @Override
    public ConnectionStatus getConnectionStatus() {
        return null;
    }

    @Override
    public ConnectionBundle getConnectionBundle() {
        return null;
    }

    @Override
    public ConnectionInfo getConnectionInfo() throws SQLException {
        return null;
    }

    @Override
    public ConnectionPool getConnectionPool() {
        return null;
    }

    @Override
    public DatabaseInterfaceProvider getInterfaceProvider() {
        return null;
    }

    @Override
    public DBObjectBundle getObjectBundle() {
        return null;
    }

    @Override
    public DBSchema getUserSchema() {
        return null;
    }

    @Override
    public SQLConsoleFile getSQLConsoleFile() {
        return null;
    }

    @Override
    public boolean isValid(boolean check) {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isVirtual() {
        return false;
    }

    @Override
    public boolean isAutoCommit() {
        return false;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
    }

    @Override
    public void disconnect() throws SQLException {
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPresentableText() {
        return null;
    }

    @Override
    public String getQualifiedName() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public void notifyChanges(VirtualFile virtualFile) {
    }

    @Override
    public void resetChanges() {
    }

    @Override
    public boolean hasUncommittedChanges() {
        return false;
    }

    @Override
    public void commit() throws SQLException {
    }

    @Override
    public void rollback() throws SQLException {
    }

    @Override
    public void ping(boolean check) {
    }

    @Override
    public DBLanguageDialect getLanguageDialect(DBLanguage language) {
        return null;
    }

    public boolean isActive() {
        return true;
    }
}
