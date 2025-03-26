package com.example.streamingweb.infrastructure;

import com.example.streamingweb.domain.Movie;
import com.example.streamingweb.domain.Rating;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        String sql = "SELECT * FROM movies";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Movie movie = new Movie();
            movie.setId(rs.getLong("id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setDescription(rs.getString("description"));
            movie.setAverageRating(rs.getDouble("average_rating"));
            return movie;
        });
    }

    @Override
    public Movie findById(Long id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Movie movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setAverageRating(rs.getDouble("average_rating"));
                return movie;
            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void saveRating(Rating rating) {
        String sql = "INSERT INTO ratings (user_id, movie_id, rating) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, rating.getUserId(), rating.getMovieId(), rating.getRating());
    }

    @Override
    public List<Rating> getRatingsForMovie(Long movieId) {
        String sql = "SELECT * FROM ratings WHERE movie_id = ?";
        return jdbcTemplate.query(sql, new Object[]{movieId}, (rs, rowNum) -> {
            Rating rating = new Rating();
            rating.setId(rs.getLong("id"));
            rating.setUserId(rs.getLong("user_id"));
            rating.setMovieId(rs.getLong("movie_id"));
            rating.setRating(rs.getInt("rating"));
            return rating;
        });
    }

    @Override
    public void updateAverageRating(Long movieId, double averageRating) {
        String sql = "UPDATE movies SET average_rating = ? WHERE id = ?";
        jdbcTemplate.update(sql, averageRating, movieId);
    }
}