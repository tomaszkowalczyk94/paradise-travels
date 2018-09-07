package paradiseTravels.service.reservation;

import paradiseTravels.bean.ReservationBean;
import paradiseTravels.model.Reservation;
import paradiseTravels.service.EntityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/reservations")
public class ReservationService extends EntityService<Reservation, ReservationBean> {

    @Inject
    ReservationBean reservationBean;

    @POST
    @Path("payu/notify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int notify(@Context HttpServletRequest request) throws Exception{
        String extOrderId = request.getParameter("orderId");
        String status = request.getParameter("status");
        if(status.equals("COMPLETED")){
        reservationBean.markAsPaid(Integer.parseInt(extOrderId));
        }
        return 200;
    }
}
