package pl.piotrszczech.planerpodrozy.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String country;
    private String city;
    private String dateOfDeparture;
    private String costOfDeparture;
    private String howManyPpl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addedTime = new Date();

    @Override
    public String toString() {
        return "Post{" +
                "Id=" + Id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", dateOfDeparture='" + dateOfDeparture + '\'' +
                ", costOfDeparture='" + costOfDeparture + '\'' +
                ", howManyPpl='" + howManyPpl + '\'' +
                ", addedTime=" + addedTime +
                '}';
    }

    public Post(String country, String city, String dateOfDeparture, String costOfDeparture, String howManyPpl) {
        this.country = country;
        this.city = city;
        this.dateOfDeparture = dateOfDeparture;
        this.costOfDeparture = costOfDeparture;
        this.howManyPpl = howManyPpl;
    }
}
