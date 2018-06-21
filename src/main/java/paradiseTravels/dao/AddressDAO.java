package paradiseTravels.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import paradiseTravels.model.Address;
import paradiseTravels.bean.HibernateSessionBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddressDAO {

    @Inject
    HibernateSessionBean hibernateSessionBean;

    public Integer getAddressId(Address address){
        Integer id  = ((Address) hibernateSessionBean.getSession().createQuery(
                "FROM Address WHERE address='" + address.getAddress() +
                        "' AND zip='" + address.getZip().toString() +
                        "' AND city='" + address.getCity() +
                        "' AND region='" + address.getRegion() +
                        "' AND country='" + address.getCountry() +
                        "'").uniqueResult()).getId();
        return id;
    }

    public void save(Address address) {
        Session session = hibernateSessionBean.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(address);
        tx1.commit();
        session.close();
    }
}
