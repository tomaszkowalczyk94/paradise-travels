package paradiseTravels.service.invoice;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import paradiseTravels.bean.invoice.InvoiceBean;
import paradiseTravels.model.Invoice;
import paradiseTravels.service.EntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Path("/invoices")

public class InvoiceService extends EntityService<Invoice,InvoiceBean> {

    @Inject
    private InvoiceBean invoiceBean;

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
