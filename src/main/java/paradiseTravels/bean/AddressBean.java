package paradiseTravels.bean;

import paradiseTravels.dao.AddressDAO;
import paradiseTravels.model.Address;

public class AddressBean {
    private AddressDAO addressDAO = new AddressDAO();

    public AddressBean(){

    }

    public Integer findAddressId(Address address){
        return addressDAO.getAddressId(address);
    }

    public void saveAddress(Address address) {
        addressDAO.save(address);
    }
}
