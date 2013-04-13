package com.meteorite.framework;

/**
 * 对象身份标识，确定一个对象的身份特征，由id，name，desc组成
 *
 * @author wei_jc
 * @version 0.0.1
 */
public interface OID {
    /**
     * 设置id
     *
     * @param id 对象ID
     * @since 0.0.1
     */
    void setId(String id);

    /**
     * 获取对象ID
     *
     * @return 返回对象ID
     * @since 0.0.1
     */
    String getId();

    /**
     * 设置name
     *
     * @param name
     * @since 0.0.1
     */
    void setName(String name);

    /**
     * 获取name
     *
     * @return 返回name
     * @since 0.0.1
     */
    String getName();

    /**
     * 设置描述
     *
     * @param desc 描述信息
     * @since 0.0.1
     */
    void setDesc(String desc);

    /**
     * 获取描述信息
     *
     * @return 返回描述信息
     * @since 0.0.1
     */
    String getDesc();
}
