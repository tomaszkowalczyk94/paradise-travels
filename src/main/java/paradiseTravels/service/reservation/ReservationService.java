package paradiseTravels.service.reservation;

import com.google.gson.Gson;
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
import javax.ws.rs.core.Response;

@Path("/reservations")
public class ReservationService extends EntityService<Reservation, ReservationBean> {

    @Inject
    ReservationBean reservationBean;


    public static class PayUNorifyModel {
        PayUOrderModel order;

        public PayUOrderModel getOrder() {
            return order;
        }

        public void setOrder(PayUOrderModel order) {
            this.order = order;
        }
    }

    public static class PayUOrderModel {
        String status;
        String orderId;
        String extOrderId;


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getExtOrderId() {
            return extOrderId;
        }

        public void setExtOrderId(String extOrderId) {
            this.extOrderId = extOrderId;
        }
    }

    @POST
    @Path("payu/notify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response notify(String output) throws Exception{

        Gson gson = new Gson();

        PayUNorifyModel payUNorifyModel = gson.fromJson(output, PayUNorifyModel.class);

        if(payUNorifyModel.getOrder().getStatus().equals("COMPLETED")){
            reservationBean.markAsPaid(Integer.parseInt(payUNorifyModel.getOrder().extOrderId));
        }

        if(payUNorifyModel.getOrder().getStatus().equals("CANCELED")){
            reservationBean.markAsCanceled(Integer.parseInt(payUNorifyModel.getOrder().extOrderId));
        }

        return Response.ok().build();
    }
}
