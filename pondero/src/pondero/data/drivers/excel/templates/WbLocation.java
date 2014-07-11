package pondero.data.drivers.excel.templates;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

public class WbLocation {

    private Sheet sheet  = null;
    private int   rowIdx = 0;
    private short colIdx = 0;

    public WbLocation() {
    }

    public WbLocation(final Sheet sheet, final int colIdx, final int rowIdx) {
        this.sheet = sheet;
        this.rowIdx = rowIdx;
        this.colIdx = (short) colIdx;
    }

    public WbLocation(final Workbook wb, final String name) {
        final Name namedCell = wb.getNameAt(wb.getNameIndex(name));
        final AreaReference aref = new AreaReference(namedCell.getRefersToFormula());
        final CellReference cellReference = aref.getFirstCell();
        sheet = wb.getSheet(cellReference.getSheetName());
        rowIdx = cellReference.getRow();
        colIdx = cellReference.getCol();
    }

    public short getColIdx() {
        return colIdx;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setColIdx(final int colIdx) {
        this.colIdx = (short) colIdx;
    }

    public void setRowIdx(final int rowIdx) {
        this.rowIdx = rowIdx;
    }

    public void setSheet(final Sheet sheet) {
        this.sheet = sheet;
    }

}
