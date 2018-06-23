package paradiseTravels.service.user;

import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import javax.ws.rs.*;

@Path("/users")
public class UserService extends EntityService<User, UserBean>
{

}
