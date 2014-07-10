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

    private static final File GEN_FILE = new File("./junit/pondero/model/foundation/gen.xlsx");

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

        final long now = 1404990564189L;
        final Calendar nowCal = Calendar.getInstance();
        nowCal.setTimeInMillis(now);

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
        template.open();
        template.pushModel(source);

        final TestModel model = template.fetchModel();
        testSheet = model.getSheet();

        // System.out.println(testSheet);

        assertNull(testSheet.getRow(0).getString());
        assertEquals("Lorem ipsum...", testSheet.getRow(1).getString());

        assertNull(testSheet.getRow(2).getBoolean());
        assertEquals(false, testSheet.getRow(3).getBoolean());
        assertEquals(true, testSheet.getRow(4).getBoolean());
        assertEquals(false, testSheet.getRow(5).getBoolean());
        assertEquals(true, testSheet.getRow(6).getBoolean());
        assertNull(testSheet.getRow(7).getBoolean());
        assertEquals(false, testSheet.getRow(8).getBoolean());
        assertEquals(true, testSheet.getRow(9).getBoolean());
        assertEquals(false, testSheet.getRow(10).getBoolean());
        assertEquals(true, testSheet.getRow(11).getBoolean());

        assertNull(testSheet.getRow(12).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(13).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(14).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(15).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(16).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45f), testSheet.getRow(17).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(18).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(19).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(20).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(21).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(22).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45f), testSheet.getRow(23).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(24).getDecimal());
        assertEquals(BigDecimal.valueOf(123), testSheet.getRow(25).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(26).getDecimal());
        assertEquals(BigDecimal.valueOf(123.45), testSheet.getRow(27).getDecimal());

        assertNull(testSheet.getRow(28).getDate());
        assertEquals(1404939600000L, testSheet.getRow(29).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(30).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(31).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(32).getDate().getTime());
        assertEquals(1404939600000L, testSheet.getRow(33).getDate().getTime());

        assertNull(testSheet.getRow(34).getDate());
        assertEquals(-2208936328000L, testSheet.getRow(35).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(36).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(37).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(38).getTime().getTime());
        assertEquals(-2208936328000L, testSheet.getRow(39).getTime().getTime());

        assertNull(testSheet.getRow(40).getDate());
        assertEquals(1405001364000L, testSheet.getRow(41).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(42).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(43).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(44).getTimestamp().getTime());
        assertEquals(1405001364000L, testSheet.getRow(45).getTimestamp().getTime());

        assertNull(testSheet.getRow(46).getFormula());
        assertEquals("SUM(1 + 1)", testSheet.getRow(47).getFormula());

        template.close();
    }
}
