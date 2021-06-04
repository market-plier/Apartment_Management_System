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
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ManagerSubBillDebtsPdfBuilder extends ReportPdfBuilder{
    private Map<ManagerSubBill,Double> managerSubBillMap;
    private double sumDebts;

    public ManagerSubBillDebtsPdfBuilder(Map<ManagerSubBill,Double> managerSubBillMap) {
        super();
        this.managerSubBillMap = managerSubBillMap;
    }

    private void writeTableData(PdfPTable table) {


        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.setColor(Color.BLACK);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMaximumFractionDigits(3);
        managerSubBillMap.forEach((k,v)->
        {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getName()), font)));
            table.addCell(new PdfPCell(new Phrase(decimalFormat.format(v), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getDurationType()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getStatus()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(k.getCommunalUtility().getDeadline()), font)));
            sumDebts+=v;
        });

    }

    public ByteArrayInputStream exportToPdf() throws IOException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.getBaseFont();
        font.setColor(Color.BLACK);


        Paragraph p = new Paragraph("Debt of Manager Bills", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{ 3.5f,3.5f, 4.0f, 3.5f, 3.5f, 3.5f});
        table.setSpacingBefore(10);

        String[] headerArr = {"Комунальна послуга","Debt",  "Duration type", "Status", "Dead line", "Calc name"};
        writeTableHeader(table, headerArr);
        writeTableData(table);
        Paragraph debt = new Paragraph();
        debt.setSpacingBefore(10f);
        debt.add("Total Debt: " + sumDebts);
        document.add(table);
        document.add(debt);
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
