package paradiseTravels.service.user;

import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import javax.ws.rs.*;
import java.util.List;

@Path("/users")
public class UserService extends EntityService<User, UserBean>
{
    public User createNew(User user) throws Exception {
        throw new BadRequestException();
    }

}
