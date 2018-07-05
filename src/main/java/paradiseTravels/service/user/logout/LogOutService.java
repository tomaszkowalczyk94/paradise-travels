package paradiseTravels.service.user.logout;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.exception.UserNotLoggedException;
import paradiseTravels.service.pojoResponse.PojoBooleanResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("logout")
public class LogOutService {

    @Inject
    AuthBean authBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PojoBooleanResponse doPost(@Context HttpServletRequest request) throws UserNotLoggedException {
        authBean.logout(request.getSession());
        return new PojoBooleanResponse(true);
    }

}
