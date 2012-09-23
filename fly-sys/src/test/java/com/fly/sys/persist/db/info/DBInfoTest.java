package com.fly.sys.persist.db.info;

import com.fly.sys.db.info.impl.DBInfoImpl;
import com.fly.sys.util.JAXBUtil;
import org.junit.Test;

import java.io.File;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class DBInfoTest {
    @Test
    public void testDBInfo() throws Exception {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/sys";
        String userName = "root";
        String password = "7758521";
        DBInfoImpl dbInfo = new DBInfoImpl(driverName, url, userName, password);
//        DAO<DBConnectionInfo> dao = DaoFactory.getJAXBDao("ConnectionInfo.xml", DBConnectionInfo.class);
//        dao.save(connectionInfo);
        JAXBUtil.marshal(dbInfo, new File("ConnectionInfo.xml"), DBInfoImpl.class);
    }
}
