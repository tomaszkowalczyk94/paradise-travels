package paradiseTravels.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="hotel")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Hotel implements Serializable, EntityIdInterface {

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


    @Min(0)
    private Integer numberOfTwoPersonBed;

    @Min(0)
    private Integer numberOfOnePersonBed;

    /* maksymalna liczba miejsc w hotelu*/
    public Integer getMaxNumberOfCustomers()
    {
        if(numberOfOnePersonBed == null || numberOfTwoPersonBed == null) {
            return 0;
        }
        return numberOfOnePersonBed+numberOfTwoPersonBed;
    }

    /*@OneToMany(targetEntity = Room.class)
    private List<Room> roomList;*/

    public List<LocalJourney> getLocalJourneyList() {
        return localJourneyList;
    }

    public void setLocalJourneyList(List<LocalJourney> localJourneyList) {
        this.localJourneyList = localJourneyList;
    }

    @OneToMany(mappedBy="hotel")
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

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", address=" + address +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stars=" + stars +
                '}';
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

}
