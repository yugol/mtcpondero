package pondero.data.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import pondero.util.BooleanUtil;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public class TestRow extends PRow {

    protected TestRow(final TestSheet sheet) {
        super(sheet);
    }

    public Boolean getBoolean() {
        return getBoolean(TestSheet.ATTR_BOOLEAN);
    }

    public Date getDate() {
        return getDate(TestSheet.ATTR_DATE);
    }

    public BigDecimal getDecimal() {
        return getDecimal(TestSheet.ATTR_DECIMAL);
    }

    public String getFormula() {
        return getString(TestSheet.ATTR_FORMULA);
    }

    public BigDecimal getInt() {
        return getDecimal(TestSheet.ATTR_INT);
    }

    public String getString() {
        return getString(TestSheet.ATTR_STRING);
    }

    public Time getTime() {
        return getTime(TestSheet.ATTR_TIME);
    }

    public Timestamp getTimestamp() {
        return getTimestamp(TestSheet.ATTR_TIMESTAMP);
    }

    public void setBoolean(final Object value) throws Exception {
        set(TestSheet.ATTR_BOOLEAN, BooleanUtil.toBoolean(value));
    }

    public void setDate(final Object value) throws Exception {
        set(TestSheet.ATTR_DATE, DateUtil.toDateMillis(value));
    }

    public void setDecimal(final Object value) throws Exception {
        set(TestSheet.ATTR_DECIMAL, NumberUtil.toDecimal(value));
    }

    public void setFormula(final String value) throws Exception {
        set(TestSheet.ATTR_FORMULA, value);
    }

    public void setInt(final Object value) throws Exception {
        set(TestSheet.ATTR_INT, NumberUtil.toDecimal(value));
    }

    public void setString(final Object value) throws Exception {
        set(TestSheet.ATTR_STRING, StringUtil.toCellString(value));
    }

    public void setTime(final Object value) throws Exception {
        set(TestSheet.ATTR_TIME, DateUtil.toTimeMillis(value));
    }

    public void setTimestamp(final Object value) throws Exception {
        set(TestSheet.ATTR_TIMESTAMP, DateUtil.toTimestampMillis(value));
    }

}
