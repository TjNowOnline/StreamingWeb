package com.example.streamingweb;

import com.example.streamingweb.application.UserService;
import com.example.streamingweb.application.MovieService;
import com.example.streamingweb.infrastructure.UserRepository;
import com.example.streamingweb.infrastructure.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamingWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamingWebApplication.class, args);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public MovieService movieService(MovieRepository movieRepository) {
        return new MovieService(movieRepository);
    }
}