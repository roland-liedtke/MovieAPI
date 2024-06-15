package pl.pjatk.MovieService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.MovieService.model.Movie;
import pl.pjatk.MovieService.service.MovieService;

import java.util.List;

@RestController
@Tag(name = "MovieAPI", description = "the movie API with documentation")
public class MovieController {


    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @Operation(summary = "List all movies from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie was returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie list not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getMovieList());
    }



    @Operation(summary = "Gets movie by id from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie was returned"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }



    @Operation(summary = "Add new movie to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie was added to database"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/movies")
    public ResponseEntity<Movie> addNewMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addNewMovie(movie));
    }



    @Operation(summary = "Update movie from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie was updated"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        return ResponseEntity.ok(movieService.updateMovie(id, updatedMovie));
    }




    @Operation(summary = "Delete movie from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie was deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }




    @Operation(summary = "Set movie available for rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie availability was set"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/movies/setAvailable/{id}")
    public ResponseEntity<Movie> setAvailable(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.setIsAvailable(id));
    }
}
