package paradiseTravels.service.user;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.primitiveResponse.PrimitiveBooleanResponse;

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
    public PrimitiveBooleanResponse loginIsExist(@PathParam("login") String username) throws Exception{

        PrimitiveBooleanResponse primitiveBooleanResponse = new PrimitiveBooleanResponse();
        primitiveBooleanResponse.setValue(bean.loginIsExist(username));

        return primitiveBooleanResponse;
    }

    @GET
    @Path("/email-is-exist/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public PrimitiveBooleanResponse emailIsExist(@PathParam("email") String email) throws Exception{

        PrimitiveBooleanResponse primitiveBooleanResponse = new PrimitiveBooleanResponse();
        primitiveBooleanResponse.setValue(bean.emailIsExist(email));

        return primitiveBooleanResponse;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll(@Context HttpServletRequest request) throws Exception {
        if(isAdminLogged(request)) {
            return super.getAll();
        }
        else  throw new Exception("You are not logged as admin");
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id ,@Context HttpServletRequest request) throws Exception {
        if(isAdminLogged(request)) {
            super.delete(id);
        }
        else throw new Exception("You are not logged as admin");
    }


    @Override
    @Path("/unsupportedDelete")
    public void delete(int id) throws Exception {
        throw new Exception("Unsupported");
    }

    @Override
    @Path("/unsupportedAll")
    public List<User> getAll() throws Exception {
        throw new Exception("Unsupported");
    }

    private boolean isAdminLogged(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null && user.getRole().equals("admin");
    }
}
