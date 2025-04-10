package com.example.streamingweb.domain;

public class Movie {
    private Long id;
    private String title;
    private String genre;
    private String description;
    private double averageRating;

    public Movie() {}

    public Movie(String title, String genre, String description, double averageRating) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.averageRating = averageRating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }
}