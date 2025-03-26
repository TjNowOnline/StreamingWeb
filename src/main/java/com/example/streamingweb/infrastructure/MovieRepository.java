package com.example.streamingweb.infrastructure;

import com.example.streamingweb.domain.Movie;
import com.example.streamingweb.domain.Rating;

import java.util.List;

public interface MovieRepository {
    List<Movie> getAll();
    Movie findById(Long id);
    void saveRating(Rating rating);
    List<Rating> getRatingsForMovie(Long movieId);
    void updateAverageRating(Long movieId, double averageRating);
}