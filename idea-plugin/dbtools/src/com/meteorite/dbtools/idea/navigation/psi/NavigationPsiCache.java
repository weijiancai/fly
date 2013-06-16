package com.meteorite.dbtools.idea.navigation.psi;

import com.intellij.openapi.Disposable;
import com.meteorite.dbtools.idea.connection.ConnectionHandler;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class NavigationPsiCache implements Disposable {

    public NavigationPsiCache(ConnectionHandler connectionHandler) {
//        connectionPsiDirectory = new DBConnectionPsiDirectory(connectionHandler);
    }

    @Override
    public void dispose() {
    }
}
