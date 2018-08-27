package paradiseTravels.bean.invoice;

import com.itextpdf.text.DocumentException;
import paradiseTravels.bean.pdf.PdfWriterBean;
import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.InvoiceDAO;
import paradiseTravels.model.Invoice;

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

    public void printInvoiceById(OutputStream outputStream, Integer id) throws DocumentException {
        pdfWriterBean.toPdf(outputStream,findById(id));
    }

    public void printInvoiceByReservationId(OutputStream outputStream, Integer id) throws DocumentException {
        pdfWriterBean.toPdf(outputStream,findByReservationId(id));
    }

}
