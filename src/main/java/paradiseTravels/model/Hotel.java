package paradiseTravels.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="hotel")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 3132L;
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //hotel ma jeden adres
    @OneToOne
    private Address address;
    private String name;
    private String description;
    @DecimalMin("0.0")
    @DecimalMax("5.5")
    private Float stars;
    @OneToMany(targetEntity = Review.class)
    private List<Review> reviewList;

    public List<LocalJourney> getLocalJourneyList() {
        return localJourneyList;
    }

    public void setLocalJourneyList(List<LocalJourney> localJourneyList) {
        this.localJourneyList = localJourneyList;
    }

    @OneToMany(targetEntity = LocalJourney.class)
    private List<LocalJourney> localJourneyList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", address=" + address +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stars=" + stars +
                ", reviewList=" + reviewList +
                '}';
    }
}