package paradiseTravels.services.user;

import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.User;
import paradiseTravels.services.user.exception.InvalidCredentialsException;

import javax.servlet.http.HttpSession;
import java.util.List;


public class UsersService {

    private UsersDAO usersDao = new UsersDAO();

    public UsersService() {
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

    public void loginUser(HttpSession session, String login, String password) throws InvalidCredentialsException {
        User user = usersDao.findByLoginPassword(login,password);

        if(user == null) {
            throw new InvalidCredentialsException();
        }
        session.setAttribute("user", user);
    }

}
