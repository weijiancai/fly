package com.meteorite.dbtools.idea.object.common;

import com.meteorite.dbtools.idea.common.Icons;
import com.meteorite.dbtools.idea.common.content.DynamicContentType;
import com.meteorite.dbtools.idea.editor.DBContentType;
import com.meteorite.dbtools.idea.object.DatabaseObjectTypeId;
import gnu.trove.THashMap;
import gnu.trove.THashSet;

import javax.swing.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wei_jc
 * @version 0.0.1
 */
public enum DBObjectType implements DynamicContentType {
    ARGUMENT(DatabaseObjectTypeId.ARGUMENT, "argument", "arguments", Icons.DBO_ARGUMENT, Icons.DBO_ARGUMENTS, false),
    CATEGORY(DatabaseObjectTypeId.CATEGORY, "category", "categories", null, null, false),
    CHARSET(DatabaseObjectTypeId.CHARSET, "charset", "charsets", null, null, false),
    CLUSTER(DatabaseObjectTypeId.CLUSTER, "cluster", "clusters", Icons.DBO_CLUSTER, Icons.DBO_CLUSTERS, false),
    COLUMN(DatabaseObjectTypeId.COLUMN, "column", "columns", Icons.DBO_COLUMN, Icons.DBO_COLUMNS, false),
    CONSTRAINT(DatabaseObjectTypeId.CONSTRAINT, "constraint", "constraints", Icons.DBO_CONSTRAINT, Icons.DBO_CONSTRAINT_DISABLED, Icons.DBO_CONSTRAINTS, false),
    DATABASE(DatabaseObjectTypeId.DATABASE, "database", "databases", null, null, false),
    DATASET(DatabaseObjectTypeId.DATASET, "dataset", "datasets", null, null, true),
    DBLINK(DatabaseObjectTypeId.DBLINK, "dblink", "database links", Icons.DBO_DATABASE_LINK, Icons.DBO_DATABASE_LINKS, false),
    DIMENSION(DatabaseObjectTypeId.DIMENSION, "dimension", "dimensions", Icons.DBO_DIMENSION, Icons.DBO_DIMENSIONS, false),
    DIMENSION_ATTRIBUTE(DatabaseObjectTypeId.DIMENSION_ATTRIBUTE, "dimension attribute", "dimension attributes", null, null, false),
    DIMENSION_HIERARCHY(DatabaseObjectTypeId.DIMENSION_HIERARCHY, "dimension hierarchy", "dimension hierarchies", null, null, false),
    DIMENSION_LEVEL(DatabaseObjectTypeId.DIMENSION_LEVEL, "dimension level", "dimension levels", null, null, false),
    DISKGROUP(DatabaseObjectTypeId.DISKGROUP, "diskgroup", "diskgroups", null, null, false),
    DOMAIN(DatabaseObjectTypeId.DOMAIN, "domain", "domains", null, null, false),
    FUNCTION(DatabaseObjectTypeId.FUNCTION, "function", "functions", Icons.DBO_FUNCTION, Icons.DBO_FUNCTIONS, false),
    GRANTED_ROLE(DatabaseObjectTypeId.GRANTED_ROLE, "granted role", "granted roles", Icons.DBO_ROLE, Icons.DBO_ROLES, false),
    GRANTED_PRIVILEGE(DatabaseObjectTypeId.GRANTED_PRIVILEGE, "granted privilege", "granted privileges", Icons.DBO_PRIVILEGE, Icons.DBO_PRIVILEGES, false),
    INDEX(DatabaseObjectTypeId.INDEX, "index", "indexes", Icons.DBO_INDEX, Icons.DBO_INDEX_DISABLED, Icons.DBO_INDEXES, false),
    INDEXTYPE(DatabaseObjectTypeId.INDEXTYPE, "indextype", "indextypes", null, null, false),
    JAVA_OBJECT(DatabaseObjectTypeId.JAVA_OBJECT, "java object", "java objects", null, null, false),
    LOB(DatabaseObjectTypeId.LOB, "lob", "lobs", null, null, false),
    MATERIALIZED_VIEW(DatabaseObjectTypeId.MATERIALIZED_VIEW, "materialized view", "materialized views", Icons.DBO_MATERIALIZED_VIEW, Icons.DBO_MATERIALIZED_VIEWS, false),
    METHOD(DatabaseObjectTypeId.METHOD, "method", "methods", null, null, true),
    MODEL(DatabaseObjectTypeId.MODEL, "model", "models", null, null, false),
    NESTED_TABLE(DatabaseObjectTypeId.NESTED_TABLE, "nested table", "nested tables", Icons.DBO_NESTED_TABLE, Icons.DBO_NESTED_TABLES, false),
    NESTED_TABLE_COLUMN(DatabaseObjectTypeId.NESTED_TABLE_COLUMN, "nested table column", "nested table columns", null, null, false),
    OPERATOR(DatabaseObjectTypeId.OPERATOR, "operator", "operators", null, null, false),
    OUTLINE(DatabaseObjectTypeId.OUTLINE, "outline", "outlines", null, null, false),
    PACKAGE(DatabaseObjectTypeId.PACKAGE, "package", "packages", Icons.DBO_PACKAGE, Icons.DBO_PACKAGES, false),
    PACKAGE_BODY(DatabaseObjectTypeId.PACKAGE_BODY, "package body", "package bodies", Icons.DBO_PACKAGE, Icons.DBO_PACKAGES, false),
    PACKAGE_FUNCTION(DatabaseObjectTypeId.PACKAGE_FUNCTION, "package function", "functions", Icons.DBO_FUNCTION, Icons.DBO_FUNCTIONS, false),
    PACKAGE_PROCEDURE(DatabaseObjectTypeId.PACKAGE_PROCEDURE, "package procedure", "procedures", Icons.DBO_PROCEDURE, Icons.DBO_PROCEDURES, false),
    PACKAGE_TYPE(DatabaseObjectTypeId.PACKAGE_TYPE, "package type", "types", Icons.DBO_TYPE, Icons.DBO_TYPES, false),
    PARTITION(DatabaseObjectTypeId.PARTITION, "partition", "partitions", null, null, false),
    PRIVILEGE(DatabaseObjectTypeId.PRIVILEGE, "privilege", "privileges", Icons.DBO_PRIVILEGE, Icons.DBO_PRIVILEGES, false),
    PROCEDURE(DatabaseObjectTypeId.PROCEDURE, "procedure", "procedures", Icons.DBO_PROCEDURE, Icons.DBO_PROCEDURES, false),
    PROFILE(DatabaseObjectTypeId.PROFILE, "profile", "profiles", null, null, false),
    ROLLBACK_SEGMENT(DatabaseObjectTypeId.ROLLBACK_SEGMENT, "rollback segment", "rollback segments", null, null, false),
    ROLE(DatabaseObjectTypeId.ROLE, "role", "roles", Icons.DBO_ROLE, Icons.DBO_ROLES, false),
    SCHEMA(DatabaseObjectTypeId.SCHEMA, "schema", "schemas", Icons.DBO_SCHEMA, Icons.DBO_SCHEMAS, false),
    SEQUENCE(DatabaseObjectTypeId.SEQUENCE, "sequence", "sequences", Icons.DBO_SEQUENCE, Icons.DBO_SEQUENCES, false),
    SUBPARTITION(DatabaseObjectTypeId.SUBPARTITION, "subpartition", "subpartitions", null, null, false),
    SYNONYM(DatabaseObjectTypeId.SYNONYM, "synonym", "synonyms", Icons.DBO_SYNONYM, Icons.DBO_SYNONYMS, false),
    TABLE(DatabaseObjectTypeId.TABLE, "table", "tables", Icons.DBO_TABLE, Icons.DBO_TABLES, false),
    TABLESPACE(DatabaseObjectTypeId.TABLESPACE, "tablespace", "tablespaces", null, null, false),
    TRIGGER(DatabaseObjectTypeId.TRIGGER, "trigger", "triggers", Icons.DBO_TRIGGER, Icons.DBO_TRIGGER_DISABLED, Icons.DBO_TRIGGERS, false),
    TYPE(DatabaseObjectTypeId.TYPE, "type", "types", Icons.DBO_TYPE, Icons.DBO_TYPES, false),
    TYPE_ATTRIBUTE(DatabaseObjectTypeId.TYPE_ATTRIBUTE, "type attribute", "attributes", Icons.DBO_ATTRIBUTE, Icons.DBO_ATTRIBUTES, false),
    TYPE_FUNCTION(DatabaseObjectTypeId.TYPE_FUNCTION, "type function", "functions", Icons.DBO_FUNCTION, Icons.DBO_FUNCTIONS, false),
    TYPE_PROCEDURE(DatabaseObjectTypeId.TYPE_PROCEDURE, "type procedure", "procedures", Icons.DBO_PROCEDURE, Icons.DBO_PROCEDURES, false),
    USER(DatabaseObjectTypeId.USER, "user", "users", Icons.DBO_USER, Icons.DBO_USERS, false),
    VARRAY(DatabaseObjectTypeId.VARRAY, "varray", "varrays", null, null, false),
    VARRAY_TYPE(DatabaseObjectTypeId.VARRAY_TYPE, "varray type", "varray types", null, null, false),
    VIEW(DatabaseObjectTypeId.VIEW, "view", "views", Icons.DBO_VIEW, Icons.DBO_VIEWS, false),

    CURSOR(DatabaseObjectTypeId.CURSOR, "cursor", "cursors", null, null, false),
    PARAMETER(DatabaseObjectTypeId.PARAMETER, "parameter", "parameters", null, null, false),
    EXCEPTION(DatabaseObjectTypeId.EXCEPTION, "exception", "exceptions", null, null, false),
    SAVEPOINT(DatabaseObjectTypeId.SAVEPOINT, "savepoint", "savepoints", null, null, false),
    LABEL(DatabaseObjectTypeId.LABEL, "label", "labels", null, null, false),

    UNKNOWN(DatabaseObjectTypeId.UNKNOWN, "unknown", null, null, null, true),
    NONE(DatabaseObjectTypeId.NONE, "none", null, null, null, true),
    ANY(DatabaseObjectTypeId.ANY, "any", null, null, null, true);

    private DatabaseObjectTypeId typeId;
    private String name;
    private String listName;
    private String presentableListName;
    private Icon icon;
    private Icon disabledIcon;
    private Icon listIcon;
    private boolean generic;

    private DBObjectType genericType;
    private Set<DBObjectType> parents = new THashSet<DBObjectType>();
    private Set<DBObjectType> genericParents = new THashSet<DBObjectType>();
    private Set<DBObjectType> children = new THashSet<DBObjectType>();
    private Set<DBObjectType> inheritingTypes = new THashSet<DBObjectType>();
    private Set<DBObjectType> familyTypes;
    private Set<DBObjectType> thisAsSet = new THashSet<DBObjectType>();

    private Map<DBContentType, Icon> icons;

    DBObjectType(DatabaseObjectTypeId typeId, String name, String listName, Icon icon, Icon disabledIcon, Icon listIcon, boolean generic) {
        this(typeId, name, listName, icon, listIcon, generic);
        this.disabledIcon = disabledIcon;
    }

    DBObjectType(DatabaseObjectTypeId typeId, String name, String listName, Icon icon, Icon listIcon, boolean generic) {
        this.typeId = typeId;
        this.name = name;
        this.listName = listName;
        this.icon = icon;
        this.listIcon = listIcon;
        this.generic = generic;
        this.presentableListName = listName == null ? null :
                Character.toUpperCase(listName.charAt(0)) + listName.substring(1).replace('_', ' ');
        thisAsSet.add(this);
    }

    public boolean isSchemaObject() {
        return parents.contains(SCHEMA);
    }

    public void addIcon(DBContentType contentType, Icon icon) {
        if (icons == null) {
            icons = new THashMap<DBContentType, Icon>();
        }
        icons.put(contentType, icon);
    }

    public Set<DBObjectType> getThisAsSet() {
        return thisAsSet;
    }

    public DatabaseObjectTypeId getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public String getListName() {
        return listName;
    }

    public String getPresentableListName() {
        return presentableListName;
    }

    public Icon getIcon() {
        return icon;
    }

    public Icon getIcon(DBContentType contentType) {
        Icon icon = null;
        if (icons != null) {
            icon = icons.get(contentType);
        }
        return icon == null ?  this.icon : icon;
    }

    public Icon getDisabledIcon() {
        return disabledIcon != null ? disabledIcon : icon;
    }

    public Icon getListIcon() {
        return listIcon;
    }

    public boolean isGeneric() {
        return generic;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public Set<DBObjectType> getParents() {
        return parents;
    }

    public Set<DBObjectType> getGenericParents() {
        return genericParents;
    }

    public Set<DBObjectType> getChildren() {
        return children;
    }

    public Set<DBObjectType> getInheritingTypes() {
        return inheritingTypes;
    }

    public void addInheritingType(DBObjectType objectType) {
        inheritingTypes.add(objectType);
    }

    public Set<DBObjectType> getFamilyTypes() {
        if (familyTypes == null) {
            familyTypes = new HashSet<DBObjectType>();
            familyTypes.addAll(inheritingTypes);
            familyTypes.add(this);
        }
        return familyTypes;
    }

    public DBObjectType getGenericType() {
        if (genericType == null) return this;

        DBObjectType objectType = genericType;
        while (true) {
            if (objectType.genericType == null) return objectType;
            objectType = objectType.genericType;
        }
    }

    public boolean isInheriting(DBObjectType objectType) {
        return objectType.getInheritingTypes().contains(this);
    }

    public void addParent(DBObjectType parent) {
        parents.add(parent);
        genericParents.add(parent.getGenericType());
        parent.children.add(this);
    }



    public void setGenericType(DBObjectType genericType) {
        this.genericType = genericType;
        genericType.inheritingTypes.add(this);
    }

    public String toString() {
        return getName();
    }

    public boolean isParentOf(DBObjectType objectType) {
        return objectType.getParents().contains(this) || objectType.getGenericParents().contains(this);
    }

    public boolean isChildOf(DBObjectType objectType) {
        return objectType.getChildren().contains(this);
    }

    public boolean hasChild(DBObjectType objectType) {
        for (DBObjectType childObjectType : getChildren()) {
            if (childObjectType.matches(objectType)) {
                return true;
            }
        }
        return false;
    }


    public static DBObjectType getObjectType(DatabaseObjectTypeId typeId) {
        for (DBObjectType objectType: values()) {
            if (objectType.getTypeId() == typeId) {
                return objectType;
            }
        }
        System.out.println("ERROR - [UNKNOWN] undefined object type: " + typeId);
        return UNKNOWN;
    }

    public static DBObjectType getObjectType(String typeName) {
        typeName = typeName.replace('_', ' ');
        for (DBObjectType objectType: values()) {
            if (objectType.getName().equalsIgnoreCase(typeName)) {
                return objectType;
            }
        }
        System.out.println("ERROR - [UNKNOWN] undefined object type: " + typeName);
        return UNKNOWN;
    }

    public boolean matches(DBObjectType objectType) {
        DBObjectType thisObjectType = this;
        while (thisObjectType != null) {
            if (thisObjectType == objectType) return true;
            thisObjectType = thisObjectType.genericType;
        }

        DBObjectType thatObjectType = objectType.genericType;
        while (thatObjectType != null) {
            if (thatObjectType == this) return true;
            thatObjectType = thatObjectType.genericType;
        }

        return objectType == DBObjectType.ANY;

    }

    static {
        TABLE.setGenericType(DATASET);
        VIEW.setGenericType(DATASET);
        MATERIALIZED_VIEW.setGenericType(DATASET);
        PROCEDURE.setGenericType(METHOD);
        FUNCTION.setGenericType(METHOD);
        TYPE_PROCEDURE.setGenericType(PROCEDURE);
        TYPE_FUNCTION.setGenericType(FUNCTION);
        PACKAGE_PROCEDURE.setGenericType(PROCEDURE);
        PACKAGE_FUNCTION.setGenericType(FUNCTION);

        GRANTED_PRIVILEGE.setGenericType(PRIVILEGE);
        GRANTED_ROLE.setGenericType(ROLE);

        ARGUMENT.addParent(FUNCTION);
        ARGUMENT.addParent(PROCEDURE);
        ARGUMENT.addParent(METHOD);
        ARGUMENT.addParent(PACKAGE_FUNCTION);
        ARGUMENT.addParent(PACKAGE_PROCEDURE);
        CLUSTER.addParent(SCHEMA);
        COLUMN.addParent(DATASET);
        COLUMN.addParent(TABLE);
        COLUMN.addParent(VIEW);
        COLUMN.addParent(MATERIALIZED_VIEW);
        CONSTRAINT.addParent(DATASET);
        CONSTRAINT.addParent(TABLE);
        CONSTRAINT.addParent(VIEW);
        CONSTRAINT.addParent(MATERIALIZED_VIEW);
        DATASET.addParent(SCHEMA);
        DBLINK.addParent(SCHEMA);
        DIMENSION.addParent(SCHEMA);
        FUNCTION.addParent(SCHEMA);
        FUNCTION.addParent(PACKAGE);
        DIMENSION_ATTRIBUTE.addParent(DIMENSION);
        DIMENSION_HIERARCHY.addParent(DIMENSION);
        DIMENSION_LEVEL.addParent(DIMENSION);
        INDEX.addParent(SCHEMA);
        MATERIALIZED_VIEW.addParent(SCHEMA);
        NESTED_TABLE.addParent(TABLE);
        NESTED_TABLE_COLUMN.addParent(NESTED_TABLE);
        PACKAGE.addParent(SCHEMA);
        PACKAGE_BODY.addParent(SCHEMA);
        PACKAGE_FUNCTION.addParent(PACKAGE);
        PACKAGE_PROCEDURE.addParent(PACKAGE);
        PACKAGE_TYPE.addParent(PACKAGE);
        PROCEDURE.addParent(SCHEMA);
        PROCEDURE.addParent(PACKAGE);
        METHOD.addParent(SCHEMA);
        METHOD.addParent(PACKAGE);
        SEQUENCE.addParent(SCHEMA);
        SYNONYM.addParent(SCHEMA);
        TABLE.addParent(SCHEMA);
        TRIGGER.addParent(DATASET);
        TRIGGER.addParent(TABLE);
        TRIGGER.addParent(VIEW);
        TRIGGER.addParent(MATERIALIZED_VIEW);
        TYPE.addParent(SCHEMA);
        TYPE_FUNCTION.addParent(TYPE);
        TYPE_PROCEDURE.addParent(TYPE);
        VIEW.addParent(SCHEMA);

        PACKAGE.addIcon(DBContentType.CODE_SPEC, Icons.DBO_PACKAGE_SPEC);
        PACKAGE.addIcon(DBContentType.CODE_BODY, Icons.DBO_PACKAGE_BODY);
    }
}
