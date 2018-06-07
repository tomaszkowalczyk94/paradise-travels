package paradiseTravels.dao;

import paradiseTravels.model.User;
import paradiseTravels.servlets.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsersDAO {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public boolean findByLogin(String login) {
        try {
            User  user = (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM User WHERE login='"+login+"'").uniqueResult();
            return user.getLogin().equals(login);
        }catch (Exception e){
            return false;
        }

    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }



    public User findByLoginPassword(String login, String password) {
        try {
            User user = (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM User WHERE login like'" + login + "' AND password like '" + password + "'").uniqueResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }


}
