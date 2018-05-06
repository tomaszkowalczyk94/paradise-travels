package paradiseTravels.services;

import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.Users;

import java.util.List;


public class UsersService {

    private UsersDAO usersDao = new UsersDAO();

    public UsersService() {
    }

    public Users findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(Users user) {
        usersDao.save(user);
    }

    public void deleteUser(Users user) {
        usersDao.delete(user);
    }

    public void updateUser(Users user) {
        usersDao.update(user);
    }

    public List<Users> findAllUsers() {
        return usersDao.findAll();
    }

    public boolean userIsExist(String login){
        if(login!=null && usersDao.findByLogin(login))return true;
        else return false;
    }

    public Users findByLoginPassword(String login, String password){
        return usersDao.findByLoginPassword(login,password);
    }


}
