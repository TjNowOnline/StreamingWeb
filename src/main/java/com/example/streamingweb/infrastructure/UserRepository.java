package com.example.streamingweb.infrastructure;

import com.example.streamingweb.domain.User;

public interface UserRepository {
    User findByEmail(String email);
}