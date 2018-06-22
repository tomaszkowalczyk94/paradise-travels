package paradiseTravels.service;

import paradiseTravels.bean.HibernateSessionBean;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloWorldService {

    @Inject
    HibernateSessionBean hibernateSessionBean;

    public static class HelloWorldResponse {
        private String message = "hello world";
        private boolean hibernateSession;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean getHibernateSession() {
            return hibernateSession;
        }

        public void setHibernateSession(boolean hibernateSession) {
            this.hibernateSession = hibernateSession;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldResponse doGet() {
        HelloWorldResponse helloWorldResponse = new HelloWorldResponse();

        helloWorldResponse.setHibernateSession(hibernateSessionBean.getSession().isOpen());
        return helloWorldResponse;
    }

}
