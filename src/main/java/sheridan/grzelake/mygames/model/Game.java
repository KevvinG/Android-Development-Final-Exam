package sheridan.grzelake.mygames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "games")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Game extends ResourceSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long identify;

    @NotBlank
    private String title;

    @NotNull
    @Pattern(regexp = "[1-5]")
    private String rating;

    public Long getIdentify() {
        return identify;
    }

    public void setIdentify(Long identify) {
        this.identify = identify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return Integer.parseInt(rating);
    }

    public void setRating(Integer rating) {
        this.rating = rating.toString();
    }

}