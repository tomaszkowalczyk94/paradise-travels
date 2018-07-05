package paradiseTravels.service.address;

import paradiseTravels.bean.AddressBean;
import paradiseTravels.model.Address;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;

@Path("/addresses")
public class AddressService  extends EntityService<Address, AddressBean> {
}
