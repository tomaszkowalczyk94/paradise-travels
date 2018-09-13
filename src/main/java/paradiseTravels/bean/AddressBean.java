package paradiseTravels.bean;

import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.AddressDAO;
import paradiseTravels.model.Address;

public class AddressBean extends EntityBean<Address, AddressDAO> {
    public AddressBean(){

    }

    public Integer findAddressId(Address address){
        return getEntityDao().getAddressId(address);
    }

    public void saveAddress(Address address) {
        getEntityDao().save(address);
    }
}
