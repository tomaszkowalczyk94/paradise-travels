package paradiseTravels.service.review;

import paradiseTravels.bean.ReviewBean;
import paradiseTravels.model.Review;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;

@Path("/reviews")
public class ReviewService extends EntityService<Review, ReviewBean> {
}
