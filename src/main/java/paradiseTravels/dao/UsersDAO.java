package paradiseTravels.dao;

import paradiseTravels.model.Users;
import paradiseTravels.servlets.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsersDAO {
    public Users findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Users.class, id);
    }

    public boolean findByLogin(String login) {
        try {
            Users  user = (Users) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Users WHERE login='"+login+"'").uniqueResult();
            return user.getLogin().equals(login);
        }catch (Exception e){
            return false;
        }

    }

    public void save(Users user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(Users user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(Users user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<Users> findAll() {
        List<Users> users = (List<Users>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Users").list();
        return users;
    }



    public Users findByLoginPassword(String login, String password) {
        try {
            Users user = (Users) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Users WHERE login='" + login + "' AND password='" + password + "'").uniqueResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }


}
