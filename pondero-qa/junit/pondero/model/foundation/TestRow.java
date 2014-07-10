package pondero.model.foundation;

import pondero.util.BooleanUtil;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public class TestRow extends PRow {

    protected TestRow(final TestSheet sheet) {
        super(sheet);
    }

    public void setDecimal(final Object value) throws Exception {
        set(TestSheet.ATTR_DECIMAL, NumberUtil.toDecimal(value));
    }

    public void setBoolean(final Object value) throws Exception {
        set(TestSheet.ATTR_BOOLEAN, BooleanUtil.toBoolean(value));
    }

    public void setDate(final Object value) throws Exception {
        set(TestSheet.ATTR_DATE, DateUtil.toDateMillis(value));
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
