package paradiseTravels.service.user.register;

import paradiseTravels.bean.user.AuthBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.bean.user.exception.InvalidCredentialsException;
import paradiseTravels.model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("register")
public class RegisterService {

    public static class RegisterResult{
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
    UserBean userBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(@Valid User user, @Context HttpServletRequest request) throws ServletException,IOException {
        RegisterResult registerResult = new RegisterResult();

        try{
            userBean.add(user);
            registerResult.setResult(true);
            registerResult.setMsg("Rejestracja pomyślna");
            return Response.status(200).entity(registerResult).build();


        }catch (InvalidCredentialsException e)
        {
            registerResult.setResult( false);
            registerResult.setMsg("Podany użytkownik istnieje");
            return Response.status(400).entity(registerResult).build();
        } catch (Exception e) {
            registerResult.setMsg("Wystapil blad! + " + e.getMessage());
            return Response.status(400).entity(registerResult).build();
        }


    }


}
