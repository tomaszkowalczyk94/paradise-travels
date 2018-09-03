package paradiseTravels.bean;

import paradiseTravels.bean.invoice.InvoiceBean;
import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.OfferDAO;
import paradiseTravels.model.*;
import paradiseTravels.service.offer.OfferBuyRequestModel;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OfferBean extends EntityBean<Offer, OfferDAO> {

    @Inject
    ReservationBean reservationBean;
    @Inject
    InvoiceBean invoiceBean;

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

        float dutyRate = (float) 1.23;
        float totalPrice = days*
                reservation.getOffer().getPricePerDayPerPerson()*
                offerBuyRequestModel.getNumberOfCustomers();
        float priceNetto = totalPrice/dutyRate;

        reservation.setPrice(totalPrice);
        reservationBean.add(reservation);
        LocalDateTime ldt = LocalDateTime.now();
        Invoice invoice = new Invoice();
        invoice.setDateOfPurchase(new Date());
        invoice.setDateInvoice(new Date());
        invoice.setDutyRate(dutyRate);
        invoice.setPriceTotal(totalPrice);
        invoice.setPriceNetto(priceNetto);
        invoice.setReservation(reservation);
        invoiceBean.add(invoice);

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
