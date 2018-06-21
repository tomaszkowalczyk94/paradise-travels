package paradiseTravels.bean.user;

import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.User;
import paradiseTravels.bean.user.exception.InvalidCredentialsException;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpSession;
import java.util.List;


@ApplicationScoped
public class UserBean {

    private UsersDAO usersDao = new UsersDAO();

    public UserBean() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public boolean userIsExist(String login){
        if(login!=null && usersDao.findByLogin(login))return true;
        else return false;
    }



}
