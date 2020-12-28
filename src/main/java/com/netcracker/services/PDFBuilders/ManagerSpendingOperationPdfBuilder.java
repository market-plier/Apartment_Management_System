package com.netcracker.services.PDFBuilders;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.pdf.draw.LineSeparator;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import com.netcracker.models.ManagerSpendingOperation;
import com.lowagie.text.*;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class ManagerSpendingOperationPdfBuilder extends ReportPdfBuilder {


    private final List<ManagerSpendingOperation> managerSpendingOperations;

    public ManagerSpendingOperationPdfBuilder(Date start, Date end, List<ManagerSpendingOperation> managerSpendingOperations) {
        super(start, end);
        this.managerSpendingOperations = managerSpendingOperations;
    }


    private void writeTableData(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.setColor(Color.BLACK);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMaximumFractionDigits(3);


        for (ManagerSpendingOperation operation : managerSpendingOperations) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(operation.getOperationId()), font)));
            table.addCell(new PdfPCell(new Phrase(decimalFormat.format(operation.getSum()), font)));
            table.addCell(new PdfPCell(new Phrase(operation.getManagerSubBill().getCommunalUtility().getName(), font)));
            table.addCell(new PdfPCell(new Phrase(operation.getDescription(), font)));
            table.addCell(new PdfPCell(new Phrase(operation.getManagerSubBill().getCommunalUtility().getDurationType().name(), font)));
            table.addCell(new PdfPCell(new Phrase(operation.getManagerSubBill().getCommunalUtility().getStatus().name(), font)));
            table.addCell(new PdfPCell(new Phrase(formatter.format(operation.getCreatedAt()), FontFactory.getFont(FontFactory.COURIER, 7))));
        }
    }

    public ByteArrayInputStream exportToPdf(){

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.getBaseFont();
        font.setColor(Color.BLACK);


        Paragraph p = new Paragraph("Manager Spending Operation between: " + start + " and " + end, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.0f, 3.0f, 3.5f, 3.5f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        String[] headerArr = {"ID", "Sum", "Communal utility", "Description", "Duration type", "Status", "Created at"};
        writeTableHeader(table, headerArr);
        writeTableData(table);
        document.add(table);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new java.util.Date());
        Chunk glue = new Chunk(new VerticalPositionMark());
        Phrase ph1 = new Phrase();
        Paragraph para = new Paragraph();
        para.setSpacingBefore(10f);
        ph1.add(new Chunk("Signature of Manager  ________  ", font));
        ph1.add(glue);
        ph1.add(new Chunk(currentDateTime, font));
        para.add(ph1);
        document.add(para);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());

    }


}
