package paradiseTravels.bean;

import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.ReservationDAO;
import paradiseTravels.model.Reservation;
import paradiseTravels.model.ReservationStatus;

import javax.inject.Inject;

public class ReservationBean extends EntityBean<Reservation, ReservationDAO> {

    public void markAsPaid(Integer reservationID) throws Exception {
        Reservation reservation = findById(reservationID);
        reservation.setReservationStatus(ReservationStatus.PAID);
        update(reservation);
    }

    public void markAsCanceled(Integer reservationID) throws Exception {
        Reservation reservation = findById(reservationID);
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        update(reservation);
    }
}
