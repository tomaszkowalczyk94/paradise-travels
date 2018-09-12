package paradiseTravels.bean;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import paradiseTravels.bean.invoice.InvoiceBean;
import paradiseTravels.bean.payU.PayuBean;
import paradiseTravels.bean.user.EntityBean;
import paradiseTravels.dao.OfferDAO;
import paradiseTravels.model.*;
import paradiseTravels.service.offer.OfferBuyRequestModel;

import javax.inject.Inject;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *
     * @param offerBuyRequestModel
     * @param user
     * @return zwraca adres strony pod którą klient dokonuje płatności
     * @throws Exception
     */
    public URL reserveAndPay(OfferBuyRequestModel offerBuyRequestModel, User user) throws Exception
    {
        Reservation reservation = reserve(offerBuyRequestModel, user);
        return payuBean.initPayment(reservation);
    }

    private Reservation reserve(OfferBuyRequestModel offerBuyRequestModel, User user) throws Exception {

        float totalPrice = calculateTotalPrice(offerBuyRequestModel);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setDateFrom(offerBuyRequestModel.getFrom());
        reservation.setDateTo(offerBuyRequestModel.getTo());
        reservation.setNumberOfOnePersonBed(offerBuyRequestModel.getNumberOfOnePersonBed());
        reservation.setNumberOfTwoPersonBed(offerBuyRequestModel.getNumberOfTwoPersonBed());
        reservation.setOffer(this.findById(offerBuyRequestModel.getOfferId()));
        reservation.setReservationStatus(ReservationStatus.RESERVED);
        reservation.setPrice(totalPrice);

        for(int id : offerBuyRequestModel.getLocalJourneyIds()) {
            LocalJourney localJourney = localJourneyBean.getEntityDao().findById(id);
            reservation.getLocalJourneyList().add(localJourney);
        }

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
        return  reservation;
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



    public List<Offer> findAllWithFillters(String dateFrom, String dateTo, String location, String priceFrom, String priceTo) throws ParseException {
        Stream<Offer> offerStream = findAll().stream();
        offerStream = dateFromFillter(offerStream,dateFrom);
        offerStream = dateToFillter(offerStream,dateTo);
        offerStream = locationFillter(offerStream,location);
        offerStream = priceFromFillter(offerStream,priceFrom);
        offerStream = priceToFillter(offerStream,priceTo);

        return  offerStream.collect(Collectors.toList());
    }

    private Stream<Offer> priceFromFillter(Stream<Offer> offerStream, String priceFrom) {
        if(priceFrom != null)
        {
            return offerStream.filter(e -> e.getPricePerDayPerPerson() >= Float.valueOf(priceFrom) );
        }
        return offerStream;
    }

    private Stream<Offer> priceToFillter(Stream<Offer> offerStream, String priceTo) {
        if(priceTo != null)
        {
            return offerStream.filter(e -> e.getPricePerDayPerPerson() <= Float.valueOf(priceTo) );
        }
        return offerStream;
    }

    private Stream<Offer> locationFillter(Stream<Offer> offerStream, String location) {
        if(location != null)
        {
           return offerStream.filter(e -> e.getHotel().getAddress().getCountry().equals(location));
        }
        return offerStream;
    }



    private Stream<Offer> dateFromFillter(Stream<Offer> offerStream,String dateFrom) throws ParseException {
        if(dateFrom != null) {
            Date from = simpleDateFormat.parse(dateFrom);
            return offerStream.filter( e -> e.getDateFrom().before(from)
                    && e.getDateFrom().before(from));
        }
        else return offerStream;
    }

    private Stream<Offer> dateToFillter(Stream<Offer> offerStream, String dateTo) throws ParseException {
        if(dateTo != null) {
            Date to = simpleDateFormat.parse(dateTo);
            return offerStream.filter( e -> e.getDateFrom().before(to)
                    && e.getDateTo().after(to));
        }
        else return offerStream;

    }

    private int daysBetween(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }



}
