package paradiseTravels.service.user;

import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserService
{
    @Inject
    UserBean userBean;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers(){
        return userBean.findAll();
    }

    @GET
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userid") int userid){
        return userBean.findById(userid);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createNew(User user){
        userBean.add(user);
        return user;
    }

    @PUT
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user, @PathParam("userid") int userid){
        user.setId(userid);
        userBean.update(user);
        return user;
    }

    @DELETE
    @Path("/{userid}")
    public void delete(@PathParam("userid") int userid) {
        User user = userBean.findById(userid);
        userBean.delete(user);
    }
}
