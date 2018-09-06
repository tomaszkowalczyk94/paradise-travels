package paradiseTravels.service.offer;

import com.mashape.unirest.http.JsonNode;
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
        offerBean.reserve(offerBuyRequestModel, (User)request.getSession().getAttribute("user"));
        return new PojoBooleanResponse(true);
    }


    @POST
    @Path("buyAndPay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonNode buyAndPay(OfferBuyRequestModel offerBuyRequestModel, @Context HttpServletRequest request) throws Exception{
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        request.getSession();
        return offerBean.reserveAndPay(offerBuyRequestModel, (User)request.getSession().getAttribute("user")).getBody();
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

    @Inject
    PayuBean payuBean;

    @GET
    @Path("test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String test() throws ParseException, UnirestException {
        return payuBean.initPaymentTest(new Reservation()).getBody().toString();
    }
}
