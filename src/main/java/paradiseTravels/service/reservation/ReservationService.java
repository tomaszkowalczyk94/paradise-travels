package paradiseTravels.service.reservation;

import paradiseTravels.bean.ReservationBean;
import paradiseTravels.model.Reservation;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.pojoResponse.PojoBooleanResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/reservations")
public class ReservationService extends EntityService<Reservation, ReservationBean> {


}
