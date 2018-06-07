package paradiseTravels.servlets.servlet.login;

import paradiseTravels.services.user.AuthBean;
import paradiseTravels.services.user.UserBean;
import paradiseTravels.services.user.exception.InvalidCredentialsException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("login")
public class LoginServlet{

    private UserBean userBean = new UserBean();

    public static class LoginResult {
        private boolean result;
        private String msg;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    @Inject
    AuthBean authBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(LoginRequest loginRequest, @Context HttpServletRequest request) throws ServletException, IOException {

        LoginResult loginResult = new LoginResult();

        authBean.getUsersDAO().findAll();
        try {
            userBean.loginUser(request.getSession(),loginRequest.login, loginRequest.password );
            loginResult.setResult(true);
            loginResult.setMsg("zalogowano");

            return Response.status(200).entity(loginResult).build();
        } catch (InvalidCredentialsException e) {
            loginResult.setResult(false);
            loginResult.setMsg("Hasło lub login niepoprawne");

            return Response.status(400).entity(loginResult).build();
        }
    }
}
