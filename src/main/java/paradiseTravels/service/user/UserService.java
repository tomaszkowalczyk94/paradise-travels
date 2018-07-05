package paradiseTravels.service.user;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.pojoResponse.PojoBooleanResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserService extends EntityService<User, UserBean>
{

    @Inject
    AuthBean authBean;

    public User createNew(User entity) throws Exception {
        throw new BadRequestException();
    }

    @GET
    @Path("/logged")
    @Produces(MediaType.APPLICATION_JSON)
    public User loggedUser(@Context HttpServletRequest request) {
        return authBean.getLogged(request.getSession());
    }


    @GET
    @Path("/login-is-exist/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public PojoBooleanResponse loginIsExist(@PathParam("login") String username) throws Exception{

        PojoBooleanResponse pojoBooleanResponse = new PojoBooleanResponse();
        pojoBooleanResponse.setValue(bean.loginIsExist(username));

        return pojoBooleanResponse;
    }

    @GET
    @Path("/email-is-exist/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public PojoBooleanResponse emailIsExist(@PathParam("email") String email) throws Exception{

        PojoBooleanResponse pojoBooleanResponse = new PojoBooleanResponse();
        pojoBooleanResponse.setValue(bean.emailIsExist(email));

        return pojoBooleanResponse;
    }
}
