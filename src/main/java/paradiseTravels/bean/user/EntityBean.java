package paradiseTravels.bean.user;

import paradiseTravels.dao.EntityDao;

import javax.inject.Inject;
import java.util.List;

abstract public class EntityBean<T, K extends EntityDao<T>>  {

    @Inject
    K entityDao;

    public T findById(int id) {
        return entityDao.findById(id);
    }

    public void save(T t) {
        entityDao.save(t);
    }

    public void update(T t) {
        entityDao.update(t);
    }

    public void delete(T t) {
        entityDao.delete(t);
    }

    public List<T> findAll() {
        return entityDao.findAll();
    }

    public K getEntityDao() {
        return entityDao;
    }
}
