package paradiseTravels.dao;

import paradiseTravels.model.User;

public class UsersDAO extends EntityDAO<User> {

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
