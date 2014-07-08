package pondero.model._;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import pondero.util.StringUtil;

@Deprecated
public abstract class Record {

    public List<String> getColumnNames() {
        final List<String> columns = new ArrayList<String>();
        for (final Field field : getClass().getDeclaredFields()) {
            if (field.getAnnotation(Column.class) != null) {
                columns.add(StringUtil.camel2Constant(field.getName()));
            }
        }
        return columns;
    }

    public String getSheetName() {
        final Sheet sheet = this.getClass().getAnnotation(Sheet.class);
        return sheet.name();
    }

    public abstract String toCsv();

}
