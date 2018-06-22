package paradiseTravels.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import paradiseTravels.bean.HibernateSessionBean;
import paradiseTravels.model.User;

import javax.inject.Inject;
import java.util.List;

abstract public class EntityDao<T> {

    @Inject
    protected HibernateSessionBean hibernateSessionBean;

    public T findById(int id) {
        return (T) hibernateSessionBean.getSession().get(getClassType(), id);
    }

    public void save(T t) {
        hibernateSessionBean.getSession().save(t);
        hibernateSessionBean.getSession().flush();
    }

    public void update(T t) {
        hibernateSessionBean.getSession().update(t);
        hibernateSessionBean.getSession().flush();
    }

    public void delete(T t) {
        hibernateSessionBean.getSession().delete(t);
        hibernateSessionBean.getSession().flush();
    }

    public List<T> findAll() {
        return  hibernateSessionBean.getSession().createQuery("from " + getClassType().getName()).list();
    }


    @SuppressWarnings("unchecked")
    private Class<T> getClassType () {
        return ((Class) ((T) getClass()
                .getGenericSuperclass()).getClass());
    }


}
