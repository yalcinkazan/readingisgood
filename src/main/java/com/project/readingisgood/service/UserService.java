package com.project.readingisgood.service;

import com.project.readingisgood.entity.Users;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<Users> findUserByUsernameAndPassword(String username, String password);

    Optional<Users> findByUsername(String username);
    
}
