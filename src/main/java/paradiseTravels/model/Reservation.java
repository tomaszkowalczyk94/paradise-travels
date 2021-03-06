package paradiseTravels.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="reservation")
public class Reservation implements EntityIdInterface {
    private static final long serialVersionUID = 3342351L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Room room;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Offer offer;

    @ManyToMany
    private List<LocalJourney> localJourneyList = new ArrayList<>();

    @Min(0)
    private Integer numberOfTwoPersonBed;
    @Min(0)
    private Integer numberOfOnePersonBed;

    private Date dateFrom;
    private Date dateTo;

    private String payuOrderId;//do sprawdzania czy platnosc przeszla

   /*cena całosciowa */
    private Float priceTotal;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public Integer getId() {
        return id;
    }

    public int getPepoleCount()
    {
        return numberOfOnePersonBed+numberOfTwoPersonBed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public float getPrice() {
        return priceTotal;
    }

    public void setPrice(float price) {
        this.priceTotal = price;
    }

    public Integer getNumberOfTwoPersonBed() {
        return numberOfTwoPersonBed;
    }

    public void setNumberOfTwoPersonBed(Integer numberOfTwoPersonBed) {
        this.numberOfTwoPersonBed = numberOfTwoPersonBed;
    }

    public Integer getNumberOfOnePersonBed() {
        return numberOfOnePersonBed;
    }

    public void setNumberOfOnePersonBed(Integer numberOfOnePersonBed) {
        this.numberOfOnePersonBed = numberOfOnePersonBed;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room +
                ", user=" + user +
                ", offer=" + offer +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", price=" + priceTotal +
                ", reservationStatus=" + reservationStatus +
                '}';
    }


    public String getPayuOrderId() {
        return payuOrderId;
    }

    public void setPayuOrderId(String payuOrderId) {
        this.payuOrderId = payuOrderId;
    }

    public List<LocalJourney> getLocalJourneyList() {
        return localJourneyList;
    }

    public void setLocalJourneyList(List<LocalJourney> localJourneyList) {
        this.localJourneyList = localJourneyList;
    }
}
