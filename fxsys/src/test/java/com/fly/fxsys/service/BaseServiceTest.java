package com.fly.fxsys.service;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Test;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class BaseServiceTest {
    private StringProperty title = new SimpleStringProperty();
    private StringProperty message = new SimpleStringProperty();

    @Test
    public void testBaseService() {
        IService service = new BaseService();

    }
}
