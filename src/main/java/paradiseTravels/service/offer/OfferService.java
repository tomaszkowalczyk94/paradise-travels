package paradiseTravels.service.offer;

import paradiseTravels.bean.OfferBean;
import paradiseTravels.bean.user.UserBean;
import paradiseTravels.model.Offer;
import paradiseTravels.model.User;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;

@Path("/offers")
public class OfferService extends EntityService<Offer, OfferBean> {

}
