package com.netcracker.services.PDFBuilders;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import com.netcracker.models.ManagerSubBill;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ManagerSubBillDebtsPdfBuilder extends ReportPdfBuilder{
    private Map<ManagerSubBill,Double> managerSubBillMap;

    public ManagerSubBillDebtsPdfBuilder(Map<ManagerSubBill,Double> managerSubBillMap) {
        super();
        this.managerSubBillMap = managerSubBillMap;
    }

    private void writeTableData(PdfPTable table) {


        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.setColor(Color.BLACK);
        managerSubBillMap.forEach((k,v)->
        {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getSubBillId()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getName()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(v), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getDurationType()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getStatus()), font)));

        });

    }

    public ByteArrayInputStream exportToPdf(){

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.getBaseFont();
        font.setColor(Color.BLACK);


        Paragraph p = new Paragraph("Dept of Manager Bills", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{ 3.5f, 4.0f, 3.5f, 3.5f});
        table.setSpacingBefore(10);

        String[] headerArr = {"Communal utility","Debt",  "Duration type", "Status"};
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