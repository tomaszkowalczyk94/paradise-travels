package paradiseTravels.service.invoice;

import com.itextpdf.text.DocumentException;
import paradiseTravels.bean.invoice.InvoiceBean;
import paradiseTravels.bean.pdf.PdfWriterBean;
import paradiseTravels.model.Invoice;
import paradiseTravels.service.EntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;

@Path("/invoices")

public class InvoiceService extends EntityService<Invoice,InvoiceBean> {

    @Inject
    private InvoiceBean invoiceBean;

    @Inject
    private PdfWriterBean pdfWriterBean;

    @GET
    @Path("/{id}/pdf")
    @Produces("application/pdf")
    public StreamingOutput generate(@PathParam("id") Integer id) {
        return output -> {
            try {
                invoiceBean.printInvoiceById(output,id);
            } catch (DocumentException e) {
                throw new IOException("error generating PDF", e);
            }
        };
    }

    @GET
    @Path("/reservation/{id}/pdf")
    @Produces("application/pdf")
    public StreamingOutput printByReservationId(@PathParam("id") Integer id) {
        return output -> {
            try {
                invoiceBean.printInvoiceByReservationId(output,id);
            } catch (DocumentException e) {
                throw new IOException("error generating PDF", e);
            }
        };
    }

}
