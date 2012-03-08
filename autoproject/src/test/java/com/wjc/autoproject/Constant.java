package com.wjc.autoproject;

/**
 * @author weijiancai
 */
public class Constant {
    public static final String PROJECT_NAME = "project_name";
    public static final String BASE_DIR = "base_dir";
    public static final String BASE_PACKAGE = "base_package";
    public static final String USE_MAVEN = "use_maven";

    // XPATH
    public static final String AP_PROJECT_NAME = "//autoproject/@name";
    public static final String AP_BASE_DIR = "//autoproject/@baseDir";
    public static final String AP_USE_MAVEN = "//autoproject/@useMaven";
    public static final String AP_BASE_PACKAGE = "//autoproject/@basePackage";

    public static final String POM_GROUP_ID = "//pom/groupId";
    public static final String POM_ARTIFACT_ID = "//pom/artifactId";
    public static final String POM_VERSION = "//pom/version";
    public static final String POM_PACKAGING = "//pom/packaging";

    public static final String POS_NODE = "//pos";
    public static final String PO_LIST = "//pos/po";
}
