package paradiseTravels.service.user.logout;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.exception.UserNotLoggedException;
import paradiseTravels.service.primitiveResponse.PrimitiveBooleanResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("logout")
public class LogOutService {

    @Inject
    AuthBean authBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PrimitiveBooleanResponse doPost(@Context HttpServletRequest request) throws UserNotLoggedException {
        authBean.logout(request.getSession());
        return new PrimitiveBooleanResponse(true);
    }

}
