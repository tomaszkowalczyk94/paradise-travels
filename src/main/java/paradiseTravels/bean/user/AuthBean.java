package paradiseTravels.bean.user;

import paradiseTravels.bean.user.exception.InvalidCredentialsException;
import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@RequestScoped
public class AuthBean {

//    @Inject
//    UsersDAO usersDAO;

    public void loginUser(HttpSession session, String login, String password) throws InvalidCredentialsException {
//        User user = usersDAO.findByLoginPassword(login, password);
//
//        if(user == null) {
//            throw new InvalidCredentialsException();
//        }
//        session.setAttribute("user", user);
    }
}
