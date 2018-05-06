package paradiseTravels.services;

import paradiseTravels.dao.AddressDAO;
import paradiseTravels.model.Address;

public class AddressService {
    private AddressDAO addressDAO = new AddressDAO();

    public AddressService(){

    }

    public Integer findAddressId(Address address){
        return addressDAO.getAddressId(address);
    }

    public void saveAddress(Address address) {
        addressDAO.save(address);
    }
}
