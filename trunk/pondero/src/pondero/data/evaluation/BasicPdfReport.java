package pondero.data.evaluation;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public abstract class BasicPdfReport {

    private final PDDocument report;

    public BasicPdfReport() {
        report = new PDDocument();
    }

    public void close() throws IOException {
        report.close();
    }

    public abstract void generate() throws Exception;

    public void save(final File reportFile) throws Exception {
        report.save(reportFile);
    }

    protected PDPage addPage() {
        final PDPage newPage = new PDPage();
        report.addPage(newPage);
        return newPage;
    }

}
