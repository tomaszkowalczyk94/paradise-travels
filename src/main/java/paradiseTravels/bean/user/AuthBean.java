package paradiseTravels.bean.user;

import paradiseTravels.bean.user.exception.InvalidCredentialsException;
import paradiseTravels.bean.user.exception.UserAlreadyLoggedException;
import paradiseTravels.bean.user.exception.UserNotLoggedException;
import paradiseTravels.dao.UsersDAO;
import paradiseTravels.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@RequestScoped
public class AuthBean {

    @Inject
    UsersDAO usersDAO;

    public void loginUser(HttpSession session, String login, String password) throws InvalidCredentialsException,UserAlreadyLoggedException {
        User user = usersDAO.findByLoginPassword(login, password);

        if(user == null) {
            throw new InvalidCredentialsException();
        }
        if(isLogged(session))
        {
            throw new UserAlreadyLoggedException();
        }

        session.setAttribute("user", user);

    }

    public void logout(HttpSession session) throws UserNotLoggedException {
        if(!isLogged(session)) {
            throw new UserNotLoggedException();
        }

        session.removeAttribute("user");
    }


    public User getLogged(HttpSession session) {
        User user = (User)session.getAttribute("user");

        if(user != null) {
            user = usersDAO.findById(user.getId());
            session.setAttribute("user", user);
        }

        return user;
    }
    private boolean isLogged(HttpSession session)
    {
        User loggedUser = (User) session.getAttribute("user");
        if(loggedUser == null) return false;
        else return true;
    }




}
