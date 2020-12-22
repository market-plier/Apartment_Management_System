package com.netcracker.services.PDFBuilders;


import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.Data;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;


@Data
public abstract class ReportPdfBuilder {
    Date start;
    Date end;
    SimpleDateFormat formatter;
    ByteArrayOutputStream out;


    public ReportPdfBuilder(Date start, Date end) {
        this.start = start;
        this.end = end;
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        out = new ByteArrayOutputStream();
    }

    public ReportPdfBuilder() {
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        out = new ByteArrayOutputStream();
    }

    protected void writeTableHeader(PdfPTable table, String... headers) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(9);
        font.setColor(Color.WHITE);

        for (String header: headers) {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

}
