package pl.pjatk.MovieService.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Movie Identifier - Integer ", example = "1")
    private Integer id;

    @Schema(description = "Movie Name - String ", example = "Titanic")
    private String name;
    @Enumerated(EnumType.STRING)
    @Schema(description = "Movie Category - CategoryEnum ", example = "Drama")
    private Category category;
    @NonNull
    @Schema(description = "Movie Availability ", example = "true")
    private boolean isAvailable;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
