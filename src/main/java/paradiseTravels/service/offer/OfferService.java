package paradiseTravels.service.offer;

import paradiseTravels.bean.OfferBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.Offer;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;
import paradiseTravels.service.pojoResponse.PojoBooleanResponse;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Enumeration;

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
}
