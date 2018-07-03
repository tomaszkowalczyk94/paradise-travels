package paradiseTravels.service.user;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserService extends EntityService<User, UserBean>
{

    @Inject
    AuthBean authBean;

    public User createNew(User user) throws Exception {
        throw new BadRequestException();
    }

    @GET
    @Path("/logged")
    @Produces(MediaType.APPLICATION_JSON)
    public User loggedUser(@Context HttpServletRequest request) {
        return authBean.getLogged(request.getSession());
    }

}
