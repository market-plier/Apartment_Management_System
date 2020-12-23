package com.netcracker.services.PDFBuilders;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import com.netcracker.models.Apartment;
import com.netcracker.models.ApartmentSubBill;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ApartmentsDebtsPdfBuilder extends ReportPdfBuilder {
    
    private List<ApartmentSubBill> apartmentDebtsList;
    private double sumDept;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Apartment apartment;

    public ApartmentsDebtsPdfBuilder(List<ApartmentSubBill> apartmentDebtsList ,Apartment apartment) {
        this.apartment = apartment;
        this.apartmentDebtsList = apartmentDebtsList;
    }
    private void writeTableData(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setSize(10);
        font.setColor(Color.BLACK);

        for (ApartmentSubBill apartmentDebt : apartmentDebtsList) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(apartmentDebt.getSubBillId()),font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(apartmentDebt.getCommunalUtility().getName()),font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(apartmentDebt.getDebt()),font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(apartmentDebt.getBalance()),font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(apartmentDebt.getCommunalUtility().getDurationType()),font)));
            sumDept +=apartmentDebt.getDebt();
        }
    }

    public ByteArrayInputStream exportToPdf()
    {


            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER);
            font.setSize(10);
            font.getBaseFont();
            font.setColor(Color.BLACK);

            Paragraph header = new Paragraph("Apartment Debt", font);
            header.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph debtApartment = new Paragraph("Payer: " + apartment.getFirstName() + " " + apartment.getLastName(), font);
            debtApartment.setAlignment(Paragraph.ALIGN_LEFT);
            Paragraph apartmentSquareMeter = new Paragraph("Square meters: " + apartment.getSquareMetres(), font);
            apartmentSquareMeter.setAlignment(Paragraph.ALIGN_LEFT);
            Paragraph apartmentCountOfPeople = new Paragraph("Count of people: " + apartment.getPeopleCount(), font);
            apartmentCountOfPeople.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(header);
            document.add(debtApartment);
            document.add(apartmentSquareMeter);
            document.add(apartmentCountOfPeople);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{3.0f, 5.0f, 3.5f, 3.5f,3.5f});
            table.setSpacingBefore(10);



            String[] headerArr = {"Bill Id", "Communal Utility", "Debt Sum", "Balance", "Duration Type"};
            writeTableHeader(table, headerArr);
            writeTableData(table);
            document.add(table);
            Paragraph totalAmount = new Paragraph("Total Debt: " + sumDept);
            totalAmount.setSpacingBefore(10f);
            document.add(totalAmount);
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateTime = dateFormatter.format(new java.util.Date());
            Chunk glue = new Chunk(new VerticalPositionMark());
            Phrase ph1 = new Phrase();
            Paragraph para = new Paragraph();
            para.setSpacingBefore(10f);
            ph1.add(new Chunk("Signature of Payment  ________  ", font));
            ph1.add(glue);
            ph1.add(new Chunk(currentDateTime, font));
            para.add(ph1);
            document.add(para);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());

    }


    
}
