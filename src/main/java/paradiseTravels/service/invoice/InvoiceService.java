package paradiseTravels.service.invoice;

import paradiseTravels.bean.invoice.InvoiceBean;
import paradiseTravels.model.Invoice;
import paradiseTravels.service.EntityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/invoices")
public class InvoiceService extends EntityService<Invoice,InvoiceBean> {

}
