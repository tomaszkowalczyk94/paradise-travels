package paradiseTravels.service.offer;

import paradiseTravels.bean.AddressBean;
import paradiseTravels.bean.HotelBean;
import paradiseTravels.bean.OfferBean;
import paradiseTravels.bean.ReservationBean;
import paradiseTravels.bean.payU.PayuBean;
import paradiseTravels.model.Offer;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.primitiveResponse.PrimitiveStringResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

@Path("/offers")
public class OfferService extends EntityService<Offer, OfferBean> {

    @Inject
    OfferBean offerBean;

    @Inject
    HotelBean hotelBean;

    @Inject
    AddressBean addressBean;

    @Inject
    ReservationBean reservationBean;

    @Inject
    PayuBean payuBean;

    @POST
    @Path("reserveAndPay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PrimitiveStringResponse reserveAndPay(OfferBuyRequestModel offerBuyRequestModel, @Context HttpServletRequest request) throws Exception{
        User user = (User) request.getSession().getAttribute("user");

        if(user == null) {
            throw new Exception("you must be logged");
        }

        URL paymentRedirectUrl = offerBean.reserveAndPay(offerBuyRequestModel, user);
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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Offer createNew(Offer offer) throws Exception{

        if(offer.getHotel().getAddress().getId() == null) {
            addressBean.add(offer.getHotel().getAddress());
        }

        if(offer.getHotel().getId() == null) {
            hotelBean.add(offer.getHotel());
        }

        bean.add(offer);
        return offer;
    }

}
