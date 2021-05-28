package platform.codingnomads.co.springtest.testingjsonresponsecontent.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int rating;

    private String description;


    public void setRating(int rating) {
        if(rating <= 0 || rating > 10) {
            throw new IllegalStateException("Rating must be between 0 and 10");
        }

        this.rating = rating;
    }
}
