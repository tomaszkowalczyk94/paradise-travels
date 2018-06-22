package paradiseTravels.dao;

import paradiseTravels.model.User;
import paradiseTravels.bean.HibernateSessionBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

public class UsersDAO extends EntityDao<User>{

    public User findById(int id) {
        return hibernateSessionBean.getSession().get(User.class, id);
    }

    public boolean findByLogin(String login) {
        try {
            User  user = (User) hibernateSessionBean.getSession().createQuery("FROM User WHERE login='"+login+"'").uniqueResult();
            return user.getLogin().equals(login);
        }catch (Exception e){
            return false;
        }

    }

    public User findByLoginPassword(String login, String password) {
        try {
            User user = (User) hibernateSessionBean.getSession().createQuery("FROM User WHERE login like'" + login + "' AND password like '" + password + "'").uniqueResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }


}
