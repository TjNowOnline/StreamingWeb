package com.example.streamingweb.application;

import com.example.streamingweb.domain.Movie;
import com.example.streamingweb.domain.Rating;
import com.example.streamingweb.infrastructure.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAll();
    }

    public List<Movie> searchMovies(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllMovies();
        }
        String lowerQuery = query.toLowerCase();
        return movieRepository.getAll().stream()
                .filter(movie ->
                        movie.getTitle().toLowerCase().contains(lowerQuery) ||
                                movie.getGenre().toLowerCase().contains(lowerQuery) ||
                                movie.getDescription().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public String playMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId);
        if (movie != null) {
            return "Now playing: " + movie.getTitle();
        }
        return "Movie not found";
    }

    public void submitRating(Long userId, Long movieId, int rating) {
        Rating newRating = new Rating(userId, movieId, rating);
        movieRepository.saveRating(newRating);
        updateAverageRating(movieId);
    }

    private void updateAverageRating(Long movieId) {
        List<Rating> ratings = movieRepository.getRatingsForMovie(movieId);
        if (!ratings.isEmpty()) {
            double average = ratings.stream()
                    .mapToInt(Rating::getRating)
                    .average()
                    .orElse(0.0);
            movieRepository.updateAverageRating(movieId, average);
        }
    }
}