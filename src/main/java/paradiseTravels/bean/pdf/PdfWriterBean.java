package paradiseTravels.bean.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import paradiseTravels.model.Invoice;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PdfWriterBean {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Font h1 = FontFactory.getFont(FontFactory.COURIER,20, BaseColor.BLACK);
    Font h2 = FontFactory.getFont(FontFactory.COURIER,16, BaseColor.BLACK);
    Font h3 = FontFactory.getFont(FontFactory.COURIER,12, BaseColor.BLACK);
    LineSeparator ls = new LineSeparator();

    public void toPdf(OutputStream outputStream, Invoice invoice) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.addTitle(String.valueOf(invoice.getId()));
        prepareInvoicePdfInformation(document,invoice);
        document.close();
    }


    private void prepareInvoicePdfInformation(Document document,Invoice invoice) throws DocumentException {
        List<Chunk> chunkList = new ArrayList<>();
        h1.setStyle(Font.UNDERLINE);
        chunkList.add(new Chunk("Faktura\n",h1));
        chunkList.add(new Chunk(ls));
        chunkList.add(new Chunk("Nabywca: "+getBuyerCredentials(invoice)+"\n",h2));
        chunkList.add(new Chunk("Sprzedawca: Paradise Travels s.p z o.o \n\n",h2));
        chunkList.add(new Chunk("Data wystawienia: "+ sdf.format(invoice.getDateInvoice()) ,h3));
        chunkList.add(new Chunk("Data zakupu: "+ sdf.format(invoice.getDateOfPurchase()) ,h3));
        chunkList.add(new Chunk(ls));
        chunkList.addAll(getReservationCredentials(invoice));
        chunkList.add(new Chunk(ls));
        chunkList.add(new Chunk("Stawka vat: " +( invoice.getDutyRate()*100 - 100) + "%",h2));
        chunkList.add(new Chunk("Razem\nSuma netto: "+ String.format("%.2fPLN", invoice.getPriceNetto()) +"\nSuma Vat:"+String.format("%.2fPLN", invoice.getPriceTotal() - invoice.getPriceNetto()) + "\nSuma Brutto:"+String.format("%.2fPLN", invoice.getPriceTotal()),h2));

        addToDocument(document,chunkList);

    }

    private void addToDocument(Document document, List<Chunk> chunkList) throws DocumentException {
        for (Chunk chunk :chunkList) {
            if(chunk.equals(chunkList.get(0))){
                Paragraph p = new Paragraph(chunk);
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                continue;
            }
            if(chunk.equals(chunkList.get(4))|| chunk.equals(chunkList.get(5))){
                Paragraph p = new Paragraph(chunk);
                p.setAlignment(Element.ALIGN_RIGHT);
                document.add(p);
                continue;
            }
            document.add(new Paragraph(chunk));
        }

    }

    private List<Chunk> getReservationCredentials(Invoice invoice) {

        List<Chunk> chunkList = new ArrayList<>();
        chunkList.add(new Chunk( " Nazwa wycieczki: " +invoice.getReservation().getOffer().getName(),h2));
        chunkList.add(new Chunk( " Od :"+ sdf.format(invoice.getReservation().getOffer().getDateFrom()),h2));
        chunkList.add(new Chunk( " Do :"+ sdf.format(invoice.getReservation().getOffer().getDateTo()),h2));
        chunkList.add(new Chunk( " Osob :"+ invoice.getReservation().getPepoleCount(),h2));
        chunkList.add(new Chunk( " Cena :"+ invoice.getReservation().getPrice(),h2));
        return chunkList;

    }

    private String getBuyerCredentials(Invoice invoice)
    {
        return invoice.getReservation().getUser().getFirstName() +" "+ invoice.getReservation().getUser().getLastName();
    }

}
