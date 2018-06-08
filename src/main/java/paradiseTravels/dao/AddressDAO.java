package paradiseTravels.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import paradiseTravels.model.Address;
import paradiseTravels.servlets.HibernateSessionFactoryUtil;

public class AddressDAO {

    public Integer getAddressId(Address address){
        Integer id  = ((Address) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "FROM Address WHERE address='" + address.getAddress() +
                        "' AND zip='" + address.getPostalCode().toString() +
                        "' AND city='" + address.getCity() +
                        "' AND region='" + address.getRegion() +
                        "' AND country='" + address.getCountry() +
                        "'").uniqueResult()).getId();
        return id;
    }

    public void save(Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(address);
        tx1.commit();
        session.close();
    }
}
