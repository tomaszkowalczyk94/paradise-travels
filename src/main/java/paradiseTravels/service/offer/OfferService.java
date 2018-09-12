package paradiseTravels.service.offer;

import paradiseTravels.bean.OfferBean;
import paradiseTravels.bean.payU.PayuBean;
import paradiseTravels.bean.ReservationBean;
import paradiseTravels.model.Offer;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.primitiveResponse.PrimitiveBooleanResponse;
import paradiseTravels.service.primitiveResponse.PrimitiveStringResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;

@Path("/offers")
public class OfferService extends EntityService<Offer, OfferBean> {

    @Inject
    OfferBean offerBean;

    @Inject
    ReservationBean reservationBean;

    @Inject
    PayuBean payuBean;

    @POST
    @Path("reserveAndPay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PrimitiveStringResponse reserveAndPay(OfferBuyRequestModel offerBuyRequestModel, @Context HttpServletRequest request) throws Exception{
        URL paymentRedirectUrl = offerBean.reserveAndPay(offerBuyRequestModel, (User) request.getSession().getAttribute("user"));
        return new PrimitiveStringResponse(paymentRedirectUrl.toExternalForm());
    }

    //offers/search?dateFrom=08-03-2019&dateTo=12-03-2019&location=Poland&priceFrom=100&priceTo=1000
    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> findWithFillters( @Context HttpServletRequest request) throws ParseException {

        String dateTo =  request.getParameter("dateTo");
        String dateFrom = request.getParameter("dateFrom");
        String location = request.getParameter("location");
        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");


        return offerBean.findAllWithFillters(dateFrom,dateTo,location,priceFrom,priceTo);
    }

}
