package com.meteorite.dbtools.idea.common;

import com.intellij.openapi.util.IconLoader;
import gnu.trove.THashMap;

import javax.swing.*;
import java.util.Map;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public class Icons {
    private static final Map<String, Icon> REGISTERED_ICONS = new THashMap<String, Icon>();

    public static final Icon WINDOW_DATABASE_BROWSER = load("/img/window/DatabaseBrowser.png");

    public static final Icon BROWSER_BACK = load("/img/action/BrowserBack.png");

    public static final Icon ACTION_SETTINGS = load("/img/action/Properties.png");

    public static final Icon DATABASE_NAVIGATOR = load("/img/project/DatabaseNavigator.png");

    public static final Icon DBO_ATTRIBUTE = load("/img/object/Attribute.png");
    public static final Icon DBO_ATTRIBUTES = load("/img/object/Attributes.png");
    public static final Icon DBO_ARGUMENT = load("/img/object/Argument.png");
    public static final Icon DBO_ARGUMENTS = load("/img/object/Arguments.png");
    public static final Icon DBO_CLUSTER = load("/img/object/Cluster.png");
    public static final Icon DBO_CLUSTERS = load("/img/object/Clusters.png");
    public static final Icon DBO_COLUMN = load("/img/object/Column.png");
    public static final Icon DBO_COLUMNS = load("/img/object/Columns.png");
    public static final Icon DBO_CONSTRAINT = load("/img/object/Constraint.png");
    public static final Icon DBO_CONSTRAINT_DISABLED = load("/img/object/ConstraintDisabled.png");
    public static final Icon DBO_CONSTRAINTS = load("/img/object/Constraints.png");
    public static final Icon DBO_DATABASE_LINK = load("/img/object/DatabaseLink.png");
    public static final Icon DBO_DATABASE_LINKS = load("/img/object/DatabaseLinks.png");
    public static final Icon DBO_DIMENSION = load("/img/object/Dimension.png");
    public static final Icon DBO_DIMENSIONS = load("/img/object/Dimensions.png");
    public static final Icon DBO_FUNCTION = load("/img/object/Function.png");
    public static final Icon DBO_FUNCTION_DEBUG = load("/img/object/FunctionDebug.png");
    public static final Icon DBO_FUNCTION_ERR = load("/img/object/FunctionErr.png");
    public static final Icon DBO_FUNCTIONS = load("/img/object/Functions.png");
    public static final Icon DBO_INDEX = load("/img/object/Index.png");
    public static final Icon DBO_INDEX_DISABLED = load("/img/object/IndexDisabled.png");
    public static final Icon DBO_INDEXES = load("/img/object/Indexes.png");
    public static final Icon DBO_MATERIALIZED_VIEW = load("/img/object/MaterializedView.png");
    public static final Icon DBO_MATERIALIZED_VIEWS = load("/img/object/MaterializedViews.png");
    public static final Icon DBO_NESTED_TABLE = load("/img/object/NestedTable.png");
    public static final Icon DBO_NESTED_TABLES = load("/img/object/NestedTables.png");
    public static final Icon DBO_PACKAGE = load("/img/object/Package.png");
    public static final Icon DBO_PACKAGE_ERR = load("/img/object/PackageErr.png");
    public static final Icon DBO_PACKAGE_DEBUG = load("/img/object/PackageDebug.png");
    public static final Icon DBO_PACKAGES = load("/img/object/Packages.png");
    public static final Icon DBO_PACKAGE_SPEC = load("DBO_PACKAGE_SPEC", "/img/object/PackageSpec.png");
    public static final Icon DBO_PACKAGE_BODY = load("DBO_PACKAGE_BODY", "/img/object/PackageBody.png");
    public static final Icon DBO_PROCEDURE = load("/img/v0/object/Procedure.png");
    public static final Icon DBO_PROCEDURE_ERR = load("/img/object/ProcedureErr.png");
    public static final Icon DBO_PROCEDURE_DEBUG = load("/img/object/ProcedureDebug.png");
    public static final Icon DBO_PROCEDURES = load("/img/object/Procedures.png");
    public static final Icon DBO_PRIVILEGE = load("/img/object/Privilege.png");
    public static final Icon DBO_PRIVILEGES = load("/img/object/Privileges.png");
    public static final Icon DBO_ROLE = load("/img/object/Role.png");
    public static final Icon DBO_ROLES = load("/img/object/Roles.png");
    public static final Icon DBO_SCHEMA = load("/img/object/Schema.png");
    public static final Icon DBO_SCHEMAS = load("/img/object/Schemas.png");
    public static final Icon DBO_SEQUENCE = load("/img/object/Sequence.png");
    public static final Icon DBO_SEQUENCES = load("/img/object/Sequences.png");
    public static final Icon DBO_SYNONYM = load("/img/object/Synonym.png");
    public static final Icon DBO_SYNONYM_ERR = load("/img/object/SynonymErr.png");
    public static final Icon DBO_SYNONYMS = load("/img/object/Synonyms.png");
    public static final Icon DBO_TABLE = load("/img/object/Table.png");
    public static final Icon DBO_TABLES = load("/img/object/Tables.png");
    public static final Icon DBO_TRIGGER = load("/img/object/Trigger.png");
    public static final Icon DBO_TRIGGER_ERR = load("/img/object/TriggerErr.png");
    public static final Icon DBO_TRIGGER_DEBUG = load("/img/object/TriggerDebug.png");
    public static final Icon DBO_TRIGGER_ERR_DISABLED = load("/img/object/TriggerErrDisabled.png");
    public static final Icon DBO_TRIGGER_DISABLED = load("/img/object/TriggerDisabled.png");
    public static final Icon DBO_TRIGGER_DISABLED_DEBUG = load("/img/object/TriggerDisabledDebug.png");
    public static final Icon DBO_TRIGGERS = load("/img/object/Triggers.png");
    public static final Icon DBO_TYPE = load("/img/object/Type.png");
    public static final Icon DBO_TYPE_COLLECTION = load("/img/object/TypeCollection.png");
    public static final Icon DBO_TYPE_COLLECTION_ERR = load("/img/object/TypeCollectionErr.png");
    public static final Icon DBO_TYPE_ERR = load("/img/object/TypeErr.png");
    public static final Icon DBO_TYPE_DEBUG = load("/img/object/TypeDebug.png");
    public static final Icon DBO_TYPES = load("/img/object/Types.png");
    public static final Icon DBO_USER = load("/img/object/User.png");
    public static final Icon DBO_USER_EXPIRED = load("/img/object/UserExpired.png");
    public static final Icon DBO_USER_LOCKED = load("/img/object/UserLocked.png");
    public static final Icon DBO_USER_EXPIRED_LOCKED = load("/img/object/UserExpiredLocked.png");
    public static final Icon DBO_USERS = load("/img/object/Users.png");
    public static final Icon DBO_VIEW = load("/img/object/View.png");
    public static final Icon DBO_VIEW_SYNONYM = load("/img/object/ViewSynonym.png");
    public static final Icon DBO_VIEWS = load("/img/object/Views.png");

    private static Icon load(String path) {
        return IconLoader.getIcon(path);
    }

    private static Icon load(String key, String path) {
        Icon icon = load(path);
        REGISTERED_ICONS.put(key, icon);
        return icon;
    }
}
