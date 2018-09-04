package paradiseTravels.bean;

import paradiseTravels.bean.invoice.InvoiceBean;
import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.OfferDAO;
import paradiseTravels.model.*;
import paradiseTravels.service.offer.OfferBuyRequestModel;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OfferBean extends EntityBean<Offer, OfferDAO> {

    @Inject
    ReservationBean reservationBean;

    @Inject
    InvoiceBean invoiceBean;

    @Inject
    LocalJourneyBean localJourneyBean;

    @Inject
    PayuBean payuBean;

    public final static float DUTY_RATE = 1.23f;

    public void buy(OfferBuyRequestModel offerBuyRequestModel, User user) throws Exception {

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setDateFrom(offerBuyRequestModel.getFrom());
        reservation.setDateTo(offerBuyRequestModel.getTo());
        reservation.setNumberOfOnePersonBed(offerBuyRequestModel.getNumberOfOnePersonBed());
        reservation.setNumberOfTwoPersonBed(offerBuyRequestModel.getNumberOfTwoPersonBed());
        reservation.setOffer(this.findById(offerBuyRequestModel.getOfferId()));
        reservation.setReservationStatus(ReservationStatus.RESERVED);

        float totalPrice = calculateTotalPrice(offerBuyRequestModel);

        reservation.setPrice(totalPrice);
        reservationBean.add(reservation);

        Invoice invoice = new Invoice();
        invoice.setDateOfPurchase(new Date());
        invoice.setDateInvoice(new Date());
        invoice.setDutyRate(DUTY_RATE);
        invoice.setPriceTotal(totalPrice);
        invoice.setPriceNetto(totalPrice/DUTY_RATE);
        invoice.setReservation(reservation);

        invoiceBean.add(invoice);

        payuBean.initPayment(reservation);

    }

    private float calculateTotalPrice(OfferBuyRequestModel offerBuyRequestModel) throws Exception {

        int days = daysBetween(offerBuyRequestModel.getFrom(), offerBuyRequestModel.getTo()) + 1;

        Offer offer = this.findById(offerBuyRequestModel.getOfferId());

        float totalPrice = days*
                offer.getPricePerDayPerPerson()*
                offerBuyRequestModel.getNumberOfCustomers();

        for(int id : offerBuyRequestModel.getLocalJourneyIds()) {
            totalPrice += localJourneyBean.getEntityDao().findById(id).getPrice() * offerBuyRequestModel.getNumberOfCustomers();
        }

        return totalPrice;
    }



    public List<Offer> findAllWithFillters(Date dateFrom,Date dateTo)
    {
        Stream<Offer> offerStream = findAll().stream();
        offerStream = dateFromFillter(offerStream,dateFrom);
        offerStream = dateToFillter(offerStream,dateTo);

        return  offerStream.collect(Collectors.toList());
    }



    private Stream<Offer> dateFromFillter(Stream<Offer> offerStream,Date dateFrom)
    {
        if(dateFrom != null) {
            return offerStream.filter( e -> e.getDateFrom().before(dateFrom)
                    && e.getDateFrom().before(dateFrom));
        }
        else return offerStream;
    }

    private Stream<Offer> dateToFillter(Stream<Offer> offerStream, Date dateTo) {
        if(dateTo != null) {
            return offerStream.filter( e -> e.getDateFrom().before(dateTo)
                    && e.getDateTo().after(dateTo));
        }
        else return offerStream;

    }

    private int daysBetween(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }



}
