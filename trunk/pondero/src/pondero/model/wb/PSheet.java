package pondero.model.wb;

import java.util.ArrayList;
import java.util.List;

public class PSheet {

    private final PWorkbook     workbook;
    private final String        name;
    private final List<PColumn> columns = new ArrayList<PColumn>();
    private final List<PRow>    rows    = new ArrayList<PRow>();

    public PSheet(final PWorkbook workbook, final String name) {
        this.workbook = workbook;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PWorkbook getWorkbook() {
        return workbook;
    }

}
