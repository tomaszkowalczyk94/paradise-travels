package paradiseTravels.service.reservation;

import paradiseTravels.bean.ReservationBean;
import paradiseTravels.model.Reservation;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;

@Path("/reservations")
public class ReservationService extends EntityService<Reservation, ReservationBean> {
}
