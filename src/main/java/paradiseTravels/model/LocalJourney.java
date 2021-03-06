package paradiseTravels.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="local_journey")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class LocalJourney implements Serializable, EntityIdInterface{

    private static final long serialVersionUID = 143215L;

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Float price;
    private Integer durationTimeMin;
    private String languageGuide;

    @ManyToOne
    private Hotel hotel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDurationTimeMin() {
        return durationTimeMin;
    }

    public void setDurationTimeMin(Integer durationTimeMin) {
        this.durationTimeMin = durationTimeMin;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLanguageGuide() {
        return languageGuide;
    }

    public void setLanguageGuide(String languageGuide) {
        this.languageGuide = languageGuide;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotelList(Hotel hotel) {
        this.hotel = hotel;
    }
}

