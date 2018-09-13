package paradiseTravels.bean.invoice;

import com.itextpdf.text.DocumentException;
import paradiseTravels.bean.pdf.PdfWriterBean;
import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.InvoiceDAO;
import paradiseTravels.model.Invoice;
import paradiseTravels.model.ReservationStatus;

import javax.inject.Inject;
import java.io.OutputStream;

public class InvoiceBean extends EntityBean<Invoice,InvoiceDAO> {

    @Inject
    PdfWriterBean pdfWriterBean;

    public Invoice findById(Integer id)
    {
        return getEntityDao().findById(id);
    }

    public Invoice findByReservationId(Integer id)
    {
        return getEntityDao().findByReservationId(id);
    }

    public void printInvoiceById(OutputStream outputStream, Integer id) throws DocumentException  {
        Invoice invoice = findById(id);
        if(isPaid(invoice))
        {
            pdfWriterBean.toPdf(outputStream,invoice);
        }
        else {
            PdfWriterBean.getErrorPdf(outputStream,"Invoice Is Unpaid");
        }
    }



    public void printInvoiceByReservationId(OutputStream outputStream, Integer id) throws DocumentException {
        Invoice invoice = findByReservationId(id);
        if(isPaid(invoice))
        {
            pdfWriterBean.toPdf(outputStream,invoice);
        }
        else {
            PdfWriterBean.getErrorPdf(outputStream,"Invoice Is Unpaid");
        }
    }

    private boolean isPaid(Invoice invoice) {
        return invoice.getReservation().getReservationStatus().equals(ReservationStatus.PAID);
    }

}
