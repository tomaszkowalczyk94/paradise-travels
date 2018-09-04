package paradiseTravels.service.offer;

import com.mashape.unirest.http.exceptions.UnirestException;
import paradiseTravels.bean.OfferBean;
import paradiseTravels.bean.PayuBean;
import paradiseTravels.model.Offer;
import paradiseTravels.model.Reservation;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.pojoResponse.PojoBooleanResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

@Path("/offers")
public class OfferService extends EntityService<Offer, OfferBean> {

    @Inject
    OfferBean offerBean;

    @POST
    @Path("buy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PojoBooleanResponse buy(OfferBuyRequestModel offerBuyRequestModel, @Context HttpServletRequest request) throws Exception{
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        request.getSession();
        offerBean.buy(offerBuyRequestModel, (User)request.getSession().getAttribute("user"));
        return new PojoBooleanResponse(true);
    }

    //offers/08-03-2019/12-03-2019
    @GET
    @Path("{dateFrom}/{dateTo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> findWithFillters(@PathParam("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return offerBean.findAllWithFillters(simpleDateFormat.parse( dateFrom),simpleDateFormat.parse(dateTo));
    }

    @Inject
    PayuBean payuBean;

    @GET
    @Path("test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void test() throws ParseException, UnirestException {
        payuBean.initPayment(new Reservation());
    }
}
