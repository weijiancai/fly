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

/**
 * @author wei_jc
 * @version 0.0.1
 */
public interface ConnectionHandler {
    Project getProject();
    Module getModule();
    Connection getPoolConnection() throws SQLException;
    Connection getPoolConnection(DBSchema schema) throws SQLException;
    Connection getStandaloneConnection() throws SQLException;
    Connection getStandaloneConnection(DBSchema schema) throws SQLException;
    void freePoolConnection(Connection connection);
    void closePoolConnection(Connection connection);
    ConnectionSettings getSettings();
    ConnectionStatus getConnectionStatus();
    ConnectionBundle getConnectionBundle();
    ConnectionInfo getConnectionInfo() throws SQLException;
    ConnectionPool getConnectionPool();
    DatabaseInterfaceProvider getInterfaceProvider();
    DBObjectBundle getObjectBundle();
    DBSchema getUserSchema();
    SQLConsoleFile getSQLConsoleFile();

    boolean isValid(boolean check);
    boolean isValid();
    boolean isVirtual();
    boolean isAutoCommit();
    void setAutoCommit(boolean autoCommit) throws SQLException;
    void disconnect() throws SQLException;

    String getId();
    String getUserName();
    String getPresentableText();
    String getQualifiedName();
    String getName();
    String getDescription();
    Icon getIcon();

    void notifyChanges(VirtualFile virtualFile);
    void resetChanges();
    boolean hasUncommittedChanges();
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void ping(boolean check);

    DBLanguageDialect getLanguageDialect(DBLanguage language);
    boolean isActive();

    DatabaseType getDatabaseType();

    Filter<BrowserTreeNode> getObjectFilter();
    NavigationPsiCache getPsiCache();

    EnvironmentType getEnvironmentType();
    UncommittedChangeBundle getUncommittedChanges();
    boolean isConnected();
    boolean isDisposed();
    int getIdleMinutes();
}
