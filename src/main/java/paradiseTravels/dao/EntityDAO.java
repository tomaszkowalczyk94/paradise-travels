package paradiseTravels.dao;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import paradiseTravels.bean.HibernateSessionBean;
import paradiseTravels.model.User;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.List;

abstract public class EntityDAO<T> {

    @Inject
    protected HibernateSessionBean hibernateSessionBean;

    public T findById(int id) {
        return (T) hibernateSessionBean.getSession().get(getClassType(), id);
    }

    public void insert(T t) {
        hibernateSessionBean.getSession().beginTransaction();
        hibernateSessionBean.getSession().save(t);
        hibernateSessionBean.getSession().getTransaction().commit();
    }

    public void update(T t) {
        hibernateSessionBean.getSession().beginTransaction();
        hibernateSessionBean.getSession().update(t);
        hibernateSessionBean.getSession().getTransaction().commit();
    }

    public void delete(T t) {
        hibernateSessionBean.getSession().beginTransaction();
        hibernateSessionBean.getSession().delete(t);
        hibernateSessionBean.getSession().getTransaction().commit();
    }

    public List<T> findAll() {
        hibernateSessionBean.getSession().clear();
        Query query = hibernateSessionBean.getSession().createQuery("from " + getClassType().getName()).setCacheMode(CacheMode.IGNORE).setCacheable(false);
        List list = query.list();
        return list;
    }


    @SuppressWarnings("unchecked")
    private Class<T> getClassType () {
        return ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }


}
