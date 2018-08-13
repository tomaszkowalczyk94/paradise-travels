package paradiseTravels.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table( name = "invoice")
public class Invoice implements Serializable,  EntityIdInterface{

    private static final long serialVersionUID = 1231L;

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateInvoice;
    private Date dateOfPurchase;

    @OneToOne
    private Reservation reservation;

    //private String sellerInfo;
    @DecimalMin("0.0")
    private Float priceNetto;
    @DecimalMin("1.0")
    private Float dutyRate; // standard 1.23
    @DecimalMin("0.0")
    private Float priceTotal;








    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    public Date getDateInvoice() {
        return dateInvoice;
    }

    public void setDateInvoice(Date dateInvoice) {
        this.dateInvoice = dateInvoice;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Float getPriceNetto() {
        return priceNetto;
    }

    public void setPriceNetto(Float priceNetto) {
        this.priceNetto = priceNetto;
    }

    public Float getDutyRate() {
        return dutyRate;
    }

    public void setDutyRate(Float dutyRate) {
        this.dutyRate = dutyRate;
    }

    public Float getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Float priceTotal) {
        this.priceTotal = priceTotal;
    }
}
