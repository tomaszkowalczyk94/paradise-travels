package paradiseTravels.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloWorldService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet() {
        return "hello world";
    }

}
