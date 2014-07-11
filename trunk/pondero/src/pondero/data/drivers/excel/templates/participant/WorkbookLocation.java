package pondero.data.drivers.excel.templates.participant;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

public class WorkbookLocation {

    private Sheet sheet  = null;
    private int   rowIdx = 0;
    private short colIdx = 0;

    public WorkbookLocation() {
    }

    public WorkbookLocation(final Workbook wb, final String name) {
        final Name namedCell = wb.getNameAt(wb.getNameIndex(name));
        final AreaReference aref = new AreaReference(namedCell.getRefersToFormula());
        final CellReference cellReference = aref.getFirstCell();
        sheet = wb.getSheet(cellReference.getSheetName());
        rowIdx = cellReference.getRow();
        colIdx = cellReference.getCol();
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(final Sheet sheet) {
        this.sheet = sheet;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public void setRowIdx(final int rowIdx) {
        this.rowIdx = rowIdx;
    }

    public short getColIdx() {
        return colIdx;
    }

    public void setColIdx(final short colIdx) {
        this.colIdx = colIdx;
    }

}
