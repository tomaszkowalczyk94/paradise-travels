package paradiseTravels.service.hotel;

import paradiseTravels.bean.HotelBean;
import paradiseTravels.model.Hotel;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;


@Path("/hotels")
public class HotelService extends EntityService<Hotel, HotelBean> {
}
