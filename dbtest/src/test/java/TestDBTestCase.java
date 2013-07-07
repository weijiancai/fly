import com.fly.dbtest.ColumnInfo;
import com.fly.dbtest.DBTestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class TestDBTestCase extends DBTestCase {
    @Test public void testExistCatalog() {
        hasCatalog("SYS");
        hasCatalog("sys");
        hasCatalog("Sys");
    }

    @Test public void testNotExistCatalog() {
        hasCatalog("abcd");
    }

    @Test public void testExistTable() {
        hasTable("bz");
    }

    @Test public void testNotExistTable() {
        hasTable("abc");
    }

    @Test public void testExistView() {
        hasView("v_kcb");
    }

    @Test public void testNotExistView() {
        hasView("v_abcd");
    }

    @Test public void testExistColumn() {
        hasColumn("bz", "bzdm");
        Object size = getColumnInfo("bz", "bzdm", ColumnInfo.COLUMN_SIZE);
        assertThat(Integer.parseInt(size.toString()), equalTo(32));
    }

    @Test public void testNotExistColumn() {
        hasColumn("bz", "abck");
    }

    @Test public void testPrintMetaData() {
        printMetaData();
    }
}
