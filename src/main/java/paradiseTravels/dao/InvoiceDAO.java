package paradiseTravels.dao;

import paradiseTravels.model.Invoice;

public class InvoiceDAO extends EntityDAO<Invoice> {

    public Invoice findByReservationId(Integer id)
    {
        return (Invoice) hibernateSessionBean.getSession().createQuery("From Invoice Where reservation_id ="+ id).uniqueResult();
    }

}
