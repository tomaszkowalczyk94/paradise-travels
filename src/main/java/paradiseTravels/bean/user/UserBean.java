package paradiseTravels.bean.user;

import paradiseTravels.bean.user.exception.InvalidCredentialsException;
import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.Address;
import paradiseTravels.model.User;


public class UserBean extends EntityBean<User, UsersDAO> {


    public boolean loginIsExist(String login){
        if(login!=null && getEntityDao().findByLogin(login))return true;
        else return false;
    }

    public boolean emailIsExist(String email){
        if(email!=null && getEntityDao().findByEmail(email))return true;
        else return false;
    }


    public int getCountOfUsers() {
        return getEntityDao().findAll().size();
    }

    @Override
    public void add(User user) throws Exception {
        if(loginIsExist(user.getLogin())) {
            throw new InvalidCredentialsException();
        }


        if(user.getAddress() == null) {
            user.setAddress(new Address());
        }

        super.add(user);
    }
}
