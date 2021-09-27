package platform.codingnomads.co.springtest.testingjsonresponsecontent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(generator = "recipe_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minutesToMake;

    @Column(nullable = false)
    private Integer difficultyRating;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "recipeId",
            nullable = false,
            foreignKey = @ForeignKey

    )
    private Collection<Ingredient> ingredients = new ArrayList<>();


    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "recipeId",
            nullable = false,
            foreignKey = @ForeignKey
    )
    private Collection<Step> steps = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "recipeId",
            nullable = false,
            foreignKey = @ForeignKey
    )
    private Collection<Review> reviews;

    @Transient
    @JsonIgnore
    private URI locationURI;

    public void setDifficultyRating(int difficultyRating) {

        if(difficultyRating < 0 || difficultyRating > 10) {
            throw new IllegalStateException("difficulty rating must be between 0 and 10");
        }

        this.difficultyRating = difficultyRating;
    }

    @PrePersist
    public void validate() throws IllegalStateException {
        if(ingredients.size() == 0) {
            throw new IllegalStateException("You have to have at least one ingredient for you recipe!");
        }else if(steps.size() == 0) {
            throw new IllegalStateException("You have to include at least one step for your recipe!");
        }
    }

    public void generateLocationURI() {
        try {
            locationURI = new URI(
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/recipes/")
                            .path(String.valueOf(id))
                            .toUriString());
        }catch (URISyntaxException e) {
            //this should be checked in testing. Exception should stop here
        }
    }
}

