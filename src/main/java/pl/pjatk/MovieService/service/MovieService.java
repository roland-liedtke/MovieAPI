package pl.pjatk.MovieService.service;

import org.springframework.stereotype.Service;
import pl.pjatk.MovieService.exception.MovieNotFoundException;
import pl.pjatk.MovieService.model.Movie;
import pl.pjatk.MovieService.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addNewMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);
    }

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        movieRepository.findById(id)
                .map(movie -> {
                    movie.setName(updatedMovie.getName());
                    movie.setCategory(updatedMovie.getCategory());
                    return movieRepository.save(movie);
                });
                return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public Movie setIsAvailable(Integer id) {
        movieRepository.findById(id)
                .map(movie -> {
                    movie.setAvailable(true);
                    return movieRepository.save(movie);
                }).orElseThrow(MovieNotFoundException::new);
        return movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);
    }
}
