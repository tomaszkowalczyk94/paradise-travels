package paradiseTravels.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="room")
public class Room implements Serializable, EntityIdInterface {
    private static final long serialVersionUID = 31232L;
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer numberOfTwoPersonBed;
    private Integer numberOfOnePersonBed;
    @ManyToOne
    private Hotel hotel;
    private Float price;
    @ElementCollection(targetClass = Facility.class)
    @CollectionTable(name="room_facility",joinColumns = {@JoinColumn(name="id")})
    @Enumerated(EnumType.STRING)
    private List<Facility> facilityList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Facility> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<Facility> facilityList) {
        this.facilityList = facilityList;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", numberOfTwoPersonBed=" + numberOfTwoPersonBed +
                ", numberOfOnePersonBed=" + numberOfOnePersonBed +
                ", hotel=" + hotel +
                ", price=" + price +
                ", facilityList=" + facilityList +
                '}';
    }
}
