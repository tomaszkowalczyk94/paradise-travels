package paradiseTravels.service.room;

import paradiseTravels.bean.RoomBean;
import paradiseTravels.model.Room;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;

@Path("/rooms")
public class RoomService extends EntityService<Room, RoomBean> {
}
