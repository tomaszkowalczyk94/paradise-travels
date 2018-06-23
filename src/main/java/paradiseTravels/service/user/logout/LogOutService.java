package paradiseTravels.service.user.logout;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.exception.UserNotLoggedException;

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

    public class LogOutResult {
        boolean result = true;

        public LogOutResult(boolean result) {
            this.result = result;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    @Inject
    AuthBean authBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LogOutResult doPost(@Context HttpServletRequest request) throws UserNotLoggedException {
        authBean.logout(request.getSession());
        return new LogOutResult(true);
    }

}
