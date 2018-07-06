package paradiseTravels.bean;

import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.AddressDAO;
import paradiseTravels.dao.OfferDAO;
import paradiseTravels.model.*;
import paradiseTravels.service.offer.OfferBuyRequestModel;

import javax.inject.Inject;
import java.util.Date;

public class OfferBean extends EntityBean<Offer, OfferDAO> {

    @Inject
    ReservationBean reservationBean;

    public void buy(OfferBuyRequestModel offerBuyRequestModel, User user) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setDateFrom(offerBuyRequestModel.getFrom());
        reservation.setDateTo(offerBuyRequestModel.getTo());
        reservation.setNumberOfOnePersonBed(offerBuyRequestModel.getNumberOfOnePersonBed());
        reservation.setNumberOfTwoPersonBed(offerBuyRequestModel.getNumberOfTwoPersonBed());
        reservation.setOffer(this.findById(offerBuyRequestModel.getOfferId()));
        reservation.setReservationStatus(ReservationStatus.RESERVED);


        int days = daysBetween(reservation.getDateFrom(), reservation.getDateTo());


        float totalPrice = days*
                reservation.getOffer().getPricePerDayPerPerson()*
                offerBuyRequestModel.getNumberOfCustomers();

        reservation.setPrice(totalPrice);
        reservationBean.add(reservation);
    }

    public int daysBetween(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

}
