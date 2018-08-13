package paradiseTravels.bean.invoice;

import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.InvoiceDAO;
import paradiseTravels.model.Invoice;

public class InvoiceBean extends EntityBean<Invoice,InvoiceDAO> {

    public Invoice findById(Integer id)
    {
        return getEntityDao().findById(id);
    }
}
