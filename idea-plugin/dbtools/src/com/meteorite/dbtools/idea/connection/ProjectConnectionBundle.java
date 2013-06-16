package com.meteorite.dbtools.idea.connection;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class ProjectConnectionBundle extends ConnectionBundle implements ProjectComponent {
    private List<ConnectionHandler> virtualConnections = new ArrayList<ConnectionHandler>();

    private ProjectConnectionBundle(Project project) {
        super(project);

        virtualConnections.add(new VirtualConnectionHandler(
                "virtual-oracle-connection",
                "Virtual - Oracle 10.1",
                DatabaseType.ORACLE,
                project));

        virtualConnections.add(new VirtualConnectionHandler(
                "virtual-mysql-connection",
                "Virtual - MySQL 5.0",
                DatabaseType.MYSQL,
                project));

        virtualConnections.add(new VirtualConnectionHandler(
                "virtual-iso92-sql-connection",
                "Virtual - ISO-92 SQL",
                DatabaseType.UNKNOWN,
                project));
    }

}
