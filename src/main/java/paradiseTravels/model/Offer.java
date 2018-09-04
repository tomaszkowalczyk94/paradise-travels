package paradiseTravels.model;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="offer")
public class Offer implements EntityIdInterface {
    private static final long serialVersionUID = 3342351L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Hotel hotel;
    private Date dateFrom;
    private Date dateTo;
    private String name;
    private boolean promoted;
    private String description;
    private String shortDescription;


    @DecimalMin("0.0")
    private Float pricePerDayPerPerson;

    @OneToMany(targetEntity = Review.class)
    private List<Review> reviewList;

    public Float getPricePerDayPerPerson() {
        return pricePerDayPerPerson;
    }

    public void setPricePerDayPerPerson(Float pricePerDayPerPerson) {
        this.pricePerDayPerPerson = pricePerDayPerPerson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", name='" + name + '\'' +
                ", promoted=" + promoted +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
