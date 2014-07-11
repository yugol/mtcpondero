package pondero.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pondero.Context;
import pondero.data.drivers.excel.templates.TestTemplate;
import pondero.util.DateUtil;

public class PTypesTest {

    private static final File GEN_FILE = new File("./junit/pondero/data/model/gen.xlsx");

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
        final long now = 1404990564189L;
        final Calendar nowCal = Calendar.getInstance();
        nowCal.setTimeInMillis(now);

        final TestModel source = new TestModel(GEN_FILE.getName());
        TestSheet testSheet = source.getSheet();

        testSheet.addRow().setString(null);
        testSheet.addRow().setString("Lorem ipsum...");

        testSheet.addRow().setBoolean(null);
        testSheet.addRow().setBoolean(false);
        testSheet.addRow().setBoolean(true);
        testSheet.addRow().setBoolean(Boolean.FALSE);
        testSheet.addRow().setBoolean(Boolean.TRUE);
        testSheet.addRow().setBoolean("");
        testSheet.addRow().setBoolean("false");
        testSheet.addRow().setBoolean("true");
        testSheet.addRow().setBoolean(0);
        testSheet.addRow().setBoolean(1);

        testSheet.addRow().setInt(null);
        testSheet.addRow().setInt(now);

        testSheet.addRow().setDecimal(null);
        testSheet.addRow().setDecimal((byte) 123.45);
        testSheet.addRow().setDecimal((short) 123.45);
        testSheet.addRow().setDecimal((int) 123.45);
        testSheet.addRow().setDecimal((long) 123.45);
        testSheet.addRow().setDecimal((float) 123.45);
        testSheet.addRow().setDecimal(123.45);
        testSheet.addRow().setDecimal(Byte.valueOf((byte) 123));
        testSheet.addRow().setDecimal(Short.valueOf((short) 123.45));
        testSheet.addRow().setDecimal(Integer.valueOf(123));
        testSheet.addRow().setDecimal(Long.valueOf(123));
        testSheet.addRow().setDecimal(Float.valueOf(123.45f));
        testSheet.addRow().setDecimal(Double.valueOf(123.45));
        testSheet.addRow().setDecimal(BigInteger.valueOf(123));
        testSheet.addRow().setDecimal(BigDecimal.valueOf(123.45));
        testSheet.addRow().setDecimal("123.45");

        testSheet.addRow().setDate(null);
        testSheet.addRow().setDate(now);
        testSheet.addRow().setDate(new Date(now));
        testSheet.addRow().setDate(new java.sql.Date(now));
        testSheet.addRow().setDate(nowCal);
        testSheet.addRow().setDate(DateUtil.toIsoDate(now));

        testSheet.addRow().setTime(null);
        testSheet.addRow().setTime(now);
        testSheet.addRow().setTime(new Date(now));
        testSheet.addRow().setTime(new java.sql.Time(now));
        testSheet.addRow().setTime(nowCal);
        testSheet.addRow().setTime(DateUtil.toIsoTime(now));

        testSheet.addRow().setTimestamp(null);
        testSheet.addRow().setTimestamp(now);
        testSheet.addRow().setTimestamp(new Date(now));
        testSheet.addRow().setTimestamp(new java.sql.Timestamp(now));
        testSheet.addRow().setTimestamp(nowCal);
        testSheet.addRow().setTimestamp(DateUtil.toIsoTimestamp(now));

        testSheet.addRow().setFormula(null);
        testSheet.addRow().setFormula("SUM(1 + 1)");

        // System.out.println(testSheet);

        final TestTemplate template = new TestTemplate(GEN_FILE);
        template.pushModel(source);
        final TestModel model = template.fetchModel();
        testSheet = model.getSheet();
        template.close();

        // System.out.println(testSheet);

        int rowIdx = 0;
        assertNull(testSheet.getRow(rowIdx++).getString());
        assertEquals("Lorem ipsum...", testSheet.getRow(rowIdx++).getString());

        assertNull(testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(false, testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(true, testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(false, testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(true, testSheet.getRow(rowIdx++).getBoolean());
        assertNull(testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(false, testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(true, testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(false, testSheet.getRow(rowIdx++).getBoolean());
        assertEquals(true, testSheet.getRow(rowIdx++).getBoolean());

        assertNull(testSheet.getRow(rowIdx++).getInt());
        assertEquals(BigDecimal.valueOf(1404990564189L), testSheet.getRow(rowIdx++).getInt());

        assertNull(testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45f), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45f), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(rowIdx++).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(rowIdx++).getDecimal());

        assertNull(testSheet.getRow(rowIdx++).getDate());
        assertEquals(1404939600000L, testSheet.getRow(rowIdx++).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(rowIdx++).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(rowIdx++).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(rowIdx++).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(rowIdx++).getDate().getTime());

        assertNull(testSheet.getRow(rowIdx++).getDate());
        assertEquals(-2208936328000L, testSheet.getRow(rowIdx++).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(rowIdx++).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(rowIdx++).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(rowIdx++).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(rowIdx++).getTime().getTime());

        assertNull(testSheet.getRow(rowIdx++).getDate());
        assertEquals(1405001364000L, testSheet.getRow(rowIdx++).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(rowIdx++).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(rowIdx++).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(rowIdx++).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(rowIdx++).getTimestamp().getTime());

        assertNull(testSheet.getRow(rowIdx++).getFormula());
        assertEquals("SUM(1 + 1)", testSheet.getRow(rowIdx++).getFormula());
    }

}
