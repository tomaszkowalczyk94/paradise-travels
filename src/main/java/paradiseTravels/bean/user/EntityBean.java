package paradiseTravels.bean.user;

import paradiseTravels.bean.user.exception.InvalidCredentialsException;
import paradiseTravels.dao.EntityDAO;

import javax.inject.Inject;
import java.util.List;

abstract public class EntityBean<T, K extends EntityDAO<T>> {

    @Inject
    K entityDao;

    public T findById(int id) throws Exception {
        return entityDao.findById(id);
    }

    public void add(T t) throws Exception {
        entityDao.insert(t);
    }

    public void update(T t) throws Exception {
        entityDao.update(t);
    }

    public void delete(T t) throws Exception {
        entityDao.delete(t);
    }

    public List<T> findAll() {
        return entityDao.findAll();
    }

    public K getEntityDao() {
        return entityDao;
    }
}
