package pondero.model.foundation;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pondero.Context;
import pondero.model.drivers.excel.templates.TestTemplate;
import pondero.util.DateUtil;

public class PTypesTest {

    private static final File GEN_FILE = new File("./junit/pondero/model/foundation/gen.xlsx");
    private static final File MAN_FILE = new File("./junit/pondero/model/foundation/man.xlsx");

    @BeforeClass
    public static void initContext() throws Exception {
        Context.initForTesting();
    }

    @AfterClass
    public static void cleanup() throws Exception {
        GEN_FILE.deleteOnExit();
    }

    @Test
    public void testWrite() throws Exception {
        final TestModel model = new TestModel(GEN_FILE.getName());

        model.getSheet().addRow().setString(null);
        model.getSheet().addRow().setString("Lorem ipsum...");

        model.getSheet().addRow().setBoolean(null);
        model.getSheet().addRow().setBoolean(false);
        model.getSheet().addRow().setBoolean(true);
        model.getSheet().addRow().setBoolean(Boolean.FALSE);
        model.getSheet().addRow().setBoolean(Boolean.TRUE);
        model.getSheet().addRow().setBoolean("");
        model.getSheet().addRow().setBoolean("false");
        model.getSheet().addRow().setBoolean("true");
        model.getSheet().addRow().setBoolean(0);
        model.getSheet().addRow().setBoolean(1);

        model.getSheet().addRow().setDecimal(null);
        model.getSheet().addRow().setDecimal((byte) 123.45);
        model.getSheet().addRow().setDecimal((short) 123.45);
        model.getSheet().addRow().setDecimal((int) 123.45);
        model.getSheet().addRow().setDecimal((long) 123.45);
        model.getSheet().addRow().setDecimal((float) 123.45);
        model.getSheet().addRow().setDecimal(123.45);
        model.getSheet().addRow().setDecimal(Byte.valueOf((byte) 123));
        model.getSheet().addRow().setDecimal(Short.valueOf((short) 123.45));
        model.getSheet().addRow().setDecimal(Integer.valueOf(123));
        model.getSheet().addRow().setDecimal(Long.valueOf(123));
        model.getSheet().addRow().setDecimal(Float.valueOf(123.45f));
        model.getSheet().addRow().setDecimal(Double.valueOf(123.45));
        model.getSheet().addRow().setDecimal(BigInteger.valueOf(123));
        model.getSheet().addRow().setDecimal(BigDecimal.valueOf(123.45));
        model.getSheet().addRow().setDecimal("123.45");

        model.getSheet().addRow().setDate(null);
        model.getSheet().addRow().setDate(System.currentTimeMillis());
        model.getSheet().addRow().setDate(new Date());
        model.getSheet().addRow().setDate(new java.sql.Date(System.currentTimeMillis()));
        model.getSheet().addRow().setDate(Calendar.getInstance());
        model.getSheet().addRow().setDate(DateUtil.toIsoDate(System.currentTimeMillis()));

        model.getSheet().addRow().setTime(null);
        model.getSheet().addRow().setTime(System.currentTimeMillis());
        model.getSheet().addRow().setTime(new Date());
        model.getSheet().addRow().setTime(new java.sql.Time(System.currentTimeMillis()));
        model.getSheet().addRow().setTime(Calendar.getInstance());
        model.getSheet().addRow().setTime(DateUtil.toIsoTime(System.currentTimeMillis()));

        System.out.println(model.getSheet());

        final TestTemplate template = new TestTemplate(GEN_FILE);
        template.open();
        template.pushModel(model);
        template.close();
    }
}
