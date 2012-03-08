<#-- @ftlvariable name="classInfo" type="com.wjc.autoproject.ClassInfo" -->
package ${classInfo.packageName};

/**
 * ${classInfo.classDesc}
 */
public class ${classInfo.className} implements java.io.Serializable {
    private static final long serialVersionUID = ${classInfo.serialVersionUID};

<#list classInfo.fieldList as field>
    /** ${field.comment} */
    private ${field.type} ${field.name};
</#list>

    /** 包名 */
    private String packageName;
    /** 类名 */
    private String className;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
