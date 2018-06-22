package paradiseTravels.bean.user;

import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.User;
import javax.inject.Inject;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;


public class UserBean extends EntityBean<User, UsersDAO> {


    public boolean isExist(String login){
        if(login!=null && getEntityDao().findByLogin(login))return true;
        else return false;
    }

    public int getCountOfUsers() {
        return getEntityDao().findAll().size();
    }



}
