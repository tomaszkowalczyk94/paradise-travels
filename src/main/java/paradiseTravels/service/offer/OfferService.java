package paradiseTravels.service.offer;

import paradiseTravels.bean.OfferBean;
import paradiseTravels.model.Offer;
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
import java.util.Date;
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


    //offers/search?dateFrom=08-03-2019&dateTo=12-03-2019
    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offer> findWithFillters( @Context HttpServletRequest request) throws ParseException {

        String dateTo =  request.getParameter("dateTo");
        String dateFrom = request.getParameter("dateFrom");


        return offerBean.findAllWithFillters(dateFrom,dateTo);
    }

}
